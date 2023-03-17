package main.java.tree;

public final class TransitionBuilder {
    private final String initialState;
    private final String lexeme;
    private final String destinationState;

    public TransitionBuilder(String initialState, String lexeme, String destinationState) {
        this.initialState = initialState;
        this.lexeme = lexeme;
        this.destinationState = destinationState;
    }

    public boolean duplicateChecker(String initialState, String lexeme) {
        return this.initialState.equals(initialState) && this.lexeme.equals(lexeme);
    }

    @Override
    public String toString() {
        return this.initialState + " -> " + this.lexeme + " -> " + this.destinationState;
    }
}
