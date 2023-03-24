package main.java.tree;

public final class LeafNumberAssigner {
    private int leafNumber;

    public LeafNumberAssigner(String regularExpression) {
        /*1. Se removerán todos los operadores para calcular el número de hojas que tendrá la expresión regular.
         * 2. Solamente se cuenta una de las comillas, por ello la de cierre no será tomada en cuenta.*/
        String cleanRegularExpression = regularExpression.replaceAll("[?*+.|]", "");  // 1.

        int length = 0;

        boolean insideQuotes = false;
        boolean insideBraces = false;

        for (int i = 0; i < cleanRegularExpression.length(); i++) {
            char character = cleanRegularExpression.charAt(i);

            if (character == '\"') {
                insideQuotes = !insideQuotes;

                length += insideQuotes ? 1 : 0;  // 2.
            } else if (character == '{') {
                insideBraces = true;
            } else if (character == '}') {
                insideBraces = false;

                length++;
            } else {
                if (!insideQuotes && !insideBraces) {
                    length++;
                }
            }
        }

        this.leafNumber = length + 1;
    }

    public int assignLeafNumber() {
        this.leafNumber -= 1;

        return leafNumber;
    }
}
