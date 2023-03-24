package main.java.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public final class NodeInitializer {  // Ya que la clase no hereda nada de otra clase, se le agrega "final".
    NodeHandler rootNode;

    public NodeInitializer(String regExp, ArrayList<NodeHandler> leavesAL, ArrayList<ArrayList<Object>> followPosAL) {
        /*AL = ArrayList*/
        LeafNumberAssigner leafNumberAssigner = new LeafNumberAssigner(regExp);

        Stack<NodeHandler> stackNodes = new Stack<>();

        String[] arraySplitRE = regExp.split("");

        ArrayList<String> arrayListRECharacters = new ArrayList<>(Arrays.asList(arraySplitRE));

        Collections.reverse(arrayListRECharacters);  // Una ER en notación polaca se lee de derecha a izquierda.

        StringBuilder stringBuilder = new StringBuilder();

        int nodeNum = 0;

        boolean insideQuotes = false;
        boolean insideBraces = false;

        for (String character : arrayListRECharacters) {
            if (!insideQuotes && character.equals("\"")) {
                insideQuotes = true;

                stringBuilder.append(character);

                continue;
            } else if (insideQuotes && !character.equals("\"")) {
                stringBuilder.append(character);

                continue;
            } else if (character.equals("\"")) {
                insideQuotes = false;

                stringBuilder.append(character);
            }

            if (!insideBraces && character.equals("}")) {
                insideBraces = true;

                stringBuilder.append(character);

                continue;
            } else if (insideBraces && !character.equals("{")) {
                stringBuilder.append(character);

                continue;
            } else if (character.equals("{")) {
                insideBraces = false;

                stringBuilder.append(character);
            }

            switch (character) {
                case "?", "*", "+" -> {
                    /*La pila se usa para almacenar temporalmente los nodos, antes de que se usen para armar el árbol.
                     * Los nodos hoja solamente se apilan, porque no tienen nodos hijos.
                     * Los operadores unarios solamente tienen un nodo hijo, por lo que solamente se retira un nodo.
                     * Los operadores binarios tienen dos nodos hijos, por ello se retiran dos nodos.*/
                    NodeHandler nodeU = stackNodes.pop();  // nodeU = unaryNode.

                    Operator op = switch (character) {
                        case "?" -> Operator.OPTIONAL;
                        case "*" -> Operator.KLEENE;
                        case "+" -> Operator.POSITIVE;
                        default -> throw new IllegalArgumentException();
                    };

                    NodeHandler node = new NodeHandler(nodeNum, character, op, 0, nodeU, null, leavesAL, followPosAL);

                    stackNodes.push(node);
                }
                case ".", "|" -> {
                    NodeHandler nodeL = stackNodes.pop();
                    NodeHandler nodeR = stackNodes.pop();

                    Operator op = switch (character) {
                        case "." -> Operator.CONCATENATION;
                        case "|" -> Operator.ALTERNATION;
                        default -> throw new IllegalArgumentException();
                    };

                    NodeHandler node = new NodeHandler(nodeNum, character, op, 0, nodeL, nodeR, leavesAL, followPosAL);

                    stackNodes.push(node);  // Los nodos que se sacaron de la pila ahora están unidos con el operador.
                }
                default -> {
                    Operator foo = Operator.LEAF;
                    int bar = leafNumberAssigner.assignLeafNumber();

                    NodeHandler node;

                    if (stringBuilder.length() == 0) {  // Si no se usó el "StringBuilder" solo se envía el carácter.
                        node = new NodeHandler(nodeNum, character, foo, bar, null, null, leavesAL, followPosAL);
                    } else {
                        stringBuilder.reverse();

                        String baz = stringBuilder.toString().replace("\"", "").replace("{", "").replace("}", "");

                        node = new NodeHandler(nodeNum, baz, foo, bar, null, null, leavesAL, followPosAL);

                        stringBuilder.setLength(0);
                    }

                    leavesAL.add(node);

                    stackNodes.push(node);
                }
            }

            nodeNum++;
        }

        this.rootNode = stackNodes.pop();  // El carácter que se encuentre más a la izquierda será la raíz.
    }

    public NodeHandler getRootNode() {
        return rootNode;
    }
}
