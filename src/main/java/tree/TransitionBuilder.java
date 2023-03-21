package main.java.tree;

import java.util.ArrayList;

public final class TransitionBuilder {
    String initialState;
    String lexeme;
    String destinationState;

    public TransitionBuilder(String initialState, String lexeme, String destinationState) {
        this.initialState = initialState;
        this.lexeme = lexeme;
        this.destinationState = destinationState;
    }

    public static String transitionChecker(String lexeme, ArrayList<TransitionBuilder> transitions) {
        /*Método que será usado para retornar los lexemas en orden para que sean insertados en la tabla.
         * Si el lexema existe en la transición entonces se retorna su estado destino. Si no existe se retorna "-".
         * Ya que no se utilizarán variables de instancia en la clase o "this", se define el método como "static".*/
        for (TransitionBuilder transition : transitions) {
            if (lexeme.equals(transition.lexeme)) return transition.destinationState;
        }

        return "-";
    }

    public boolean duplicateChecker(String initialState, String lexeme) {
        return this.initialState.equals(initialState) && this.lexeme.equals(lexeme);
    }

    @Override
    public String toString() {
        return this.initialState + " -> " + this.lexeme + " -> " + this.destinationState;
    }
}
