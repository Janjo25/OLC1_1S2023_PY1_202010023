package main.java.tree;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class TransitionHandler {
    int stateNumber;

    Set<String> hashSetHeaders = new HashSet<>();

    ArrayList<ArrayList<Object>> arrayListTransitions;

    @SuppressWarnings({"unchecked", "GrazieInspection"})
    public TransitionHandler(NodeHandler root, ArrayList<NodeHandler> leavesAL, ArrayList<ArrayList<Object>> FollowAl) {
        /*AL = ArrayList*/
        /*1. Bucle que será empleado para la creación y verificación de los estados de transición.
         * Este iterará hasta que no se agregue un nuevo elemento a la lista.
         * 2. Estos son los valores que se colocan en la columna "valores" del constructor de la tabla de transiciones.
         * 3. Método para obtener los "siguientes" de cada hoja de la tabla de siguientes.
         * Estos son los valores que se colocan en la columna "siguientes" del constructor de la tabla de transiciones.
         * 4. Booleano que se usará para indicar si es la primera vez que aparece un conjunto de "siguientes".
         * 5. Cadena que es utilizada para guardar el nombre del estado al que pertenecen los "siguientes" duplicados.
         * 6. Bucle que será empleado para comparar los "siguientes" entre dos "ArrayList".
         * Esto se hace para ver si es la primera vez que aparece un conjunto de "siguientes".
         * Si es la primera vez que aparece, entonces es posible crear un nuevo estado.
         * Si no es la primera vez que aparece, solamente se señalará que es posible la transición a ese estado.
         * 7. Condicional que revisará si el estado actual es de aceptación.
         * 8. Condicional que verificará si se ha llegado al nodo que contiene el símbolo de aceptación.
         * El nodo que contenga el símbolo de aceptación no será tomado en cuenta.
         * 9. Bucle que se usará para comprobar que los lexemas no tengan transiciones duplicadas en el estado.*/
        ArrayList<Object> arrayListInitialState = new ArrayList<>();

        arrayListInitialState.add("S-0");  // Creación del estado de transición inicial.
        arrayListInitialState.add(root.firstPosAL);  // Los "siguientes" del nodo raíz.
        arrayListInitialState.add(new ArrayList<>());  // Esta "ArrayList" se usará para almacenar la transición.
        arrayListInitialState.add(false);  // Booleano que se volverá verdadero si es un estado de aceptación.

        this.stateNumber = 1;

        this.arrayListTransitions = new ArrayList<>();  // Se inicializa la "ArrayList" principal.

        this.arrayListTransitions.add(arrayListInitialState);

        for (int i = 0; i < this.arrayListTransitions.size(); i++) {  // 1.
            ArrayList<Object> arrayListState = this.arrayListTransitions.get(i);  // Estado de transición más reciente.

            ArrayList<Integer> arrayListConstructorValues = (ArrayList<Integer>) arrayListState.get(1);  // 2.

            for (int leafNumber : arrayListConstructorValues) {
                NodeGetter nodeGetter = new NodeGetter();

                Object foo = nodeGetter.getNodeFollowPos(leafNumber, FollowAl).clone();  // 3.

                ArrayList<Object> followPosConstructorAL = (ArrayList<Object>) foo;

                boolean isDuplicateConstructorFollowPos = false;  // 4.

                String duplicateConstructorFollowPosState = null;  // 5.

                for (ArrayList<Object> arrayListTransition : this.arrayListTransitions) {  // 6.
                    if (arrayListTransition.get(1).equals(followPosConstructorAL.get(1))) {
                        isDuplicateConstructorFollowPos = true;

                        duplicateConstructorFollowPosState = arrayListTransition.get(0).toString();

                        break;
                    }
                }

                if (nodeGetter.getAcceptanceNode(leafNumber, leavesAL)) {  // 7.
                    arrayListState.set(3, true);
                }

                if (!isDuplicateConstructorFollowPos) {
                    if (followPosConstructorAL.get(0) == null) {  // 8.
                        continue;
                    }

                    ArrayList<Object> newState = new ArrayList<>();  // Creación de un nuevo estado de transición.

                    newState.add("S-" + stateNumber);
                    newState.add(followPosConstructorAL.get(1));
                    newState.add(new ArrayList<>());
                    newState.add(false);

                    hashSetHeaders.add(followPosConstructorAL.get(0).toString());

                    TransitionBuilder builder = new TransitionBuilder(
                            arrayListState.get(0).toString(),
                            followPosConstructorAL.get(0).toString(),
                            newState.get(0).toString()
                    );

                    ((ArrayList<Object>) arrayListState.get(2)).add(builder);

                    stateNumber += 1;

                    this.arrayListTransitions.add(newState);
                } else {
                    boolean isDuplicateTransition = false;

                    String[] bar = {arrayListState.get(0).toString(), followPosConstructorAL.get(0).toString()};

                    for (TransitionBuilder builder : (ArrayList<TransitionBuilder>) arrayListState.get(2)) {  // 9.
                        if (builder.duplicateChecker(bar[0], bar[1])) {
                            isDuplicateTransition = true;

                            break;
                        }
                    }

                    if (!isDuplicateTransition) {
                        hashSetHeaders.add(followPosConstructorAL.get(0).toString());

                        TransitionBuilder builder = new TransitionBuilder(
                                arrayListState.get(0).toString(),
                                followPosConstructorAL.get(0).toString(),
                                duplicateConstructorFollowPosState
                        );

                        ((ArrayList<Object>) arrayListState.get(2)).add(builder);
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void printTransitions() {
        for (ArrayList<Object> state : arrayListTransitions) {
            StringBuilder stringBuilder = new StringBuilder("[");

            for (TransitionBuilder builder : (ArrayList<TransitionBuilder>) state.get(2)) {
                stringBuilder.append(builder.toString()).append(", ");
            }

            stringBuilder.append("]");

            stringBuilder = new StringBuilder(stringBuilder.toString().replace(", ]", "]"));

            String[] foo = {state.get(0).toString(), state.get(1).toString(), state.get(3).toString()};

            System.out.println(foo[0] + " | " + foo[1] + " | " + stringBuilder + " | " + foo[2]);
        }
    }

    @SuppressWarnings("unchecked")
    public void createTransitionsTable() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("digraph {");
        stringBuilder.append("TransitionsTable [");
        stringBuilder.append("label = <<table>");

        stringBuilder.append("<tr>");
        stringBuilder.append("<td rowspan=\"2\" colspan=\"2\">Estado</td>");
        stringBuilder.append("<td colspan=\"").append(hashSetHeaders.size()).append("\">Σ</td>");
        stringBuilder.append("</tr>");

        stringBuilder.append("<tr>");

        for (String header : hashSetHeaders) {
            stringBuilder.append("<td>").append(header).append("</td>");
        }

        stringBuilder.append("</tr>");

        for (ArrayList<Object> state : arrayListTransitions) {
            stringBuilder.append("<tr>");

            stringBuilder.append("<td colspan=\"2\">").append(state.get(0)).append("</td>");

            for (String lexeme : hashSetHeaders) {
                stringBuilder.append("<td>");

                ArrayList<TransitionBuilder> foo = (ArrayList<TransitionBuilder>) state.get(2);

                stringBuilder.append(TransitionBuilder.transitionChecker(lexeme, foo));

                stringBuilder.append("</td>");
            }

            stringBuilder.append("</tr>");
        }

        stringBuilder.append("</table>>");
        stringBuilder.append(",shape = none];");
        stringBuilder.append("}");

        int fileNumber = 1;

        File file = new File("data/Transitions/Transitions.png");

        while (file.exists()) {
            file = new File("data/Transitions/Transitions " + "(" + fileNumber + ")" + ".png");

            fileNumber++;
        }

        try {
            Graphviz.fromString(stringBuilder.toString()).render(Format.PNG).toFile(file);
        } catch (IOException fileIOException) {
            System.err.println("Se produjo un error al intentar guardar la imagen.");
        }
    }

    @SuppressWarnings({"unchecked", "SpellCheckingInspection"})
    public void createDFA() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("digraph {");
        stringBuilder.append("rankdir = LR;");
        stringBuilder.append("node [shape = circle];");

        for (ArrayList<Object> state : arrayListTransitions) {
            for (TransitionBuilder transition : (ArrayList<TransitionBuilder>) state.get(2)) {
                String foo = transition.destinationState;

                if ((Boolean) state.get(3)) {
                    stringBuilder.append("\"").append(foo).append("\"").append(" [shape = doublecircle];");
                }

                stringBuilder.append("\"").append(transition.initialState).append("\"");

                stringBuilder.append(" -> ");

                stringBuilder.append("\"").append(foo).append("\"");

                stringBuilder.append(" [label = \"").append(transition.lexeme).append("\"];");
            }
        }

        stringBuilder.append("}");

        int fileNumber = 1;

        File file = new File("data/DFAs/DFA.png");

        while (file.exists()) {
            file = new File("data/DFAs/DFA " + "(" + fileNumber + ")" + ".png");

            fileNumber++;
        }

        try {
            Graphviz.fromString(stringBuilder.toString()).render(Format.PNG).toFile(file);
        } catch (IOException fileIOException) {
            System.err.println("Se produjo un error al intentar guardar la imagen.");
        }
    }
}
