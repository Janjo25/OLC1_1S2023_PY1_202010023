package main.java.gui;

import main.java.analyzers.lexical.LexicalAnalysis;
import main.java.analyzers.syntactic.SyntacticAnalysis;

import javax.swing.*;
import java.io.*;

public class Interface extends JFrame {
    private String selectedFilePath = "";  // Variable global para recordar el último archivo con el que se interactuó.

    private JButton analyzeInputButton;
    private JButton generateAutomatonButton;
    private JButton openButton;
    private JButton saveAsButton;
    private JButton saveButton;
    private JPanel interfacePanel;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;

    public Interface() {
        this.setContentPane(interfacePanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.pack();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        //noinspection SpellCheckingInspection
        this.setTitle("EXREGAN");

        panel();

        buttons();
    }

    private void panel() {
        interfacePanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 15, 20));
    }

    private void buttons() {
        analyzeInputButton.addActionListener(e -> {
            String inputTextAreaText = inputTextArea.getText();

            LexicalAnalysis scanner = new LexicalAnalysis(new BufferedReader(new StringReader(inputTextAreaText)));

            //noinspection deprecation
            SyntacticAnalysis parser = new SyntacticAnalysis(scanner);

            try {
                parser.parse();
            } catch (Exception exceptionParse) {
                outputTextArea.setText("Se encontraron errores en la entrada.");

                return;
            }

            outputTextArea.setText("No se encontraron errores en la entrada.");
        });

        generateAutomatonButton.addActionListener(e -> System.out.println("Generar autómata"));

        openButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            int flagFileChooser = fileChooser.showOpenDialog(interfacePanel);

            if (flagFileChooser == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                selectedFilePath = selectedFile.getPath();

                try {
                    FileReader fileReader = new FileReader(selectedFile);

                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    StringBuilder stringBuilder = new StringBuilder();

                    String selectedFileLine;

                    while ((selectedFileLine = bufferedReader.readLine()) != null) {
                        stringBuilder.append(selectedFileLine);

                        stringBuilder.append('\n');
                    }

                    fileReader.close();

                    bufferedReader.close();

                    inputTextArea.setText(stringBuilder.toString());
                } catch (IOException fileReaderIOException) {
                    outputTextArea.setText("Ocurrió un error al intentar leer el archivo.");

                    return;
                }

                outputTextArea.setText("El archivo fue leído con éxito.");
            }
        });

        saveAsButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setDialogTitle("Save as");
            fileChooser.setApproveButtonText("Save");

            int flagFileChooser = fileChooser.showOpenDialog(interfacePanel);

            if (flagFileChooser == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                selectedFilePath = selectedFile.getPath();

                try {
                    FileWriter fileWriter = new FileWriter(selectedFile, false);

                    fileWriter.write(inputTextArea.getText());

                    fileWriter.close();
                } catch (IOException fileWriterIOException) {
                    outputTextArea.setText("Ocurrió un error al intentar guardar el archivo.");

                    return;
                }

                String[] foo = selectedFilePath.split("/");

                outputTextArea.setText("El archivo " + '"' + foo[foo.length - 1] + '"' + " fue guardado con éxito.");
            }
        });

        saveButton.addActionListener(e -> {
            try {
                /*El segundo argumento de "fileWriter" se usa para sobreescribir archivos.
                 * Si este es falso, cuando se encuentre un archivo con el mismo nombre, lo sobrescribirá.*/
                FileWriter fileWriter = new FileWriter(selectedFilePath, false);

                fileWriter.write(inputTextArea.getText());

                fileWriter.close();
            } catch (IOException fileWriterIOException) {
                outputTextArea.setText("Se debe abrir un archivo primero para poder guardar la entrada.");

                return;
            }

            String[] foo = selectedFilePath.split("/");

            outputTextArea.setText("El archivo " + '"' + foo[foo.length - 1] + '"' + " fue guardado con éxito.");
        });
    }
}
