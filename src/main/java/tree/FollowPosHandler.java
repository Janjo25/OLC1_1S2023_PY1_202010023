package main.java.tree;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class FollowPosHandler {
    @SuppressWarnings("unchecked")
    public void groupFollowPos(
            String lexeme,
            int leafNumber,
            ArrayList<Integer> arrayListFirstPos,
            ArrayList<ArrayList<Object>> arrayListFollowPos
    ) {
        /*El argumento "ArrayList<ArrayList<Object>>" indica que se guardará un "ArrayList" dentro de otra.
         * La "ArrayList" de adentro es de tipo "Object", ya que esta guardará variables de diferente tipo.
         * 1. En el primer "for" se indica que la variable "element" es de tipo "ArrayList<Object>".
         * Esto se hace para acceder a las acciones de un "ArrayList".
         * 2. En el segundo "for" se indica que la variable "followPos" es de tipo "Integer".
         * Esto se hizo así porque se sabe que los contenidos de esta "ArrayList" siempre serán enteros.
         * 3. Debido a que se está intentando convertir un objeto a otro tipo de variable, nos arroja una advertencia.
         * No es posible evitar este tipo de advertencia, por lo que debe de ser ignorada.*/
        for (ArrayList<Object> element : arrayListFollowPos) {  // 1.
            if (lexeme == element.get(0) && leafNumber == (int) element.get(1)) {
                for (Integer followPos : arrayListFirstPos) {  // 2.
                    if (!((ArrayList<Integer>) element.get(2)).contains(followPos)) {  // 3.
                        ((ArrayList<Integer>) element.get(2)).add(followPos);
                    }
                }

                return;
            }
        }

        ArrayList<Object> arrayListLeafFollowPos = new ArrayList<>();

        arrayListLeafFollowPos.add(lexeme);
        arrayListLeafFollowPos.add(leafNumber);
        arrayListLeafFollowPos.add(arrayListFirstPos);

        arrayListFollowPos.add(arrayListLeafFollowPos);
    }

    public void printFollowPos(ArrayList<ArrayList<Object>> arrayListFollowPos) {
        for (ArrayList<Object> nodeFollowPos : arrayListFollowPos) {
            System.out.println(nodeFollowPos.get(0) + " | " + nodeFollowPos.get(1) + " | " + nodeFollowPos.get(2));
        }
    }

    public void createFollowPosTable(ArrayList<ArrayList<Object>> arrayListFollowPos) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("digraph {");
        stringBuilder.append("FollowPos [");
        stringBuilder.append("label = <<table>");
        stringBuilder.append("<tr><td>Valor</td><td>Hoja</td><td>Siguientes</td></tr>");

        for (ArrayList<Object> nodeFollowPos : arrayListFollowPos) {
            String foo = nodeFollowPos.get(0).toString();
            String bar = nodeFollowPos.get(1).toString();
            String baz = nodeFollowPos.get(2).toString();

            String qux = "</td><td>";
            String waldo = "</td></tr>";

            stringBuilder.append("<tr><td>").append(foo).append(qux).append(bar).append(qux).append(baz).append(waldo);
        }

        stringBuilder.append("</table>>");
        stringBuilder.append(",shape = none];");
        stringBuilder.append("}");

        int fileNumber = 1;

        File file = new File("data/FollowPos/FollowPos.png");

        while (file.exists()) {
            file = new File("data/FollowPos/FollowPos " + "(" + fileNumber + ")" + ".png");

            fileNumber++;
        }

        try {
            Graphviz.fromString(stringBuilder.toString()).render(Format.PNG).toFile(file);
        } catch (IOException fileIOException) {
            System.err.println("Se produjo un error al intentar guardar la imagen.");
        }
    }
}
