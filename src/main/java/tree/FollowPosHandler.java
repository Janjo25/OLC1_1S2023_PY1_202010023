package main.java.tree;

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
}
