package main.java.gui;

import main.java.analyzers.lexical.LexicalAnalysis;
import main.java.analyzers.syntactic.SyntacticAnalysis;
import main.java.tree.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        /*1. Expresión regular para separar la entrada en líneas individuales.
         * 2. Se usará un "lookbehind positivo" para no eliminar el punto y coma al usar "split".
         * 3. Con una expresión regular se obtendrá lo que esta después de la flecha, pero antes del punto y coma.
         * 4. Se eliminarán todos los espacios de la expresión regular.*/
        analyzeInputButton.addActionListener(e -> System.out.println("Generar autómata"));

        generateAutomatonButton.addActionListener(e -> {
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

            String[] inputTextAreaLines = inputTextAreaText.split("\\r?\\n");  // 1.

            for (String line : inputTextAreaLines) {
                line = line.split("(?<=;)")[0];  // 2.

                Pattern pattern = Pattern.compile("(?<=->\\s).*?(?=;\\s*$)");  // 3.

                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    String regExp = "." + matcher.group() + "#";

                    if (regExp.contains("\"") || regExp.contains("{") || regExp.contains("}")) {
                        String bar = regExp.replaceAll("(?<!\\{|\"|\\w)\\s+|\\s+(?!}|\"|\\w)", "");  // 4.

                        ArrayList<NodeHandler> arrayListLeaves = new ArrayList<>();

                        ArrayList<ArrayList<Object>> arrayListFollowPos = new ArrayList<>();

                        NodeInitializer nodeInitializer = new NodeInitializer(bar, arrayListLeaves, arrayListFollowPos);

                        NodeHandler rootNode = nodeInitializer.getRootNode();

                        TreeVisualizer treeVisualizer = new TreeVisualizer();

                        rootNode.calculateNFL(treeVisualizer);

                        treeVisualizer.createAST(true, null);

                        rootNode.calculateFollowPos();

                        FollowPosHandler followPosHandler = new FollowPosHandler();

                        arrayListFollowPos.sort(Comparator.comparingInt(foo -> (int) foo.get(1)));

                        followPosHandler.printFollowPos(arrayListFollowPos);

                        followPosHandler.createFollowPosTable(arrayListFollowPos);

                        TransitionHandler transitionHandler = new TransitionHandler(
                                rootNode,
                                arrayListLeaves,
                                arrayListFollowPos
                        );

                        transitionHandler.printTransitions();

                        transitionHandler.createTransitionsTable();

                        transitionHandler.createDFA();
                    }
                }
            }

            outputTextArea.setText("No se encontraron errores en la entrada.");
        });

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
