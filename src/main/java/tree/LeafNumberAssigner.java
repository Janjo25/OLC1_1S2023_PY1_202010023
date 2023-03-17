package main.java.tree;

public final class LeafNumberAssigner {
    private int leafNumber;

    public LeafNumberAssigner(String regularExpression) {
        /*Se removerán todos los operadores para calcular el número de hojas que tendrá la expresión regular.*/
        this.leafNumber = regularExpression.replaceAll("[?*+.|]", "").length() + 1;
    }

    public int assignLeafNumber() {
        this.leafNumber -= 1;

        return leafNumber;
    }
}
