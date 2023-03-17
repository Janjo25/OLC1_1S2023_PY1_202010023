package main.java.tree;

import java.util.ArrayList;

public final class TransitionHandler {
    int stateNumber;

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
}
