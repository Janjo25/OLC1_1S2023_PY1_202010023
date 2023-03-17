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

        String[] arraySplitRE = regExp.replace(" ", "").split("");

        ArrayList<String> arrayListRECharacters = new ArrayList<>(Arrays.asList(arraySplitRE));

        Collections.reverse(arrayListRECharacters);  // Una ER en notación polaca se lee de derecha a izquierda.

        arrayListRECharacters.forEach((character) -> {
            switch (character) {
                case "?", "*", "+" -> {
                    /*La pila se usa para almacenar temporalmente los nodos, antes de que se usen para armar el árbol.
                     * Los nodos hoja solamente se apilan, porque no tienen nodos hijos.
                     * Los operadores unarios solamente tienen un nodo hijo, por lo que solamente se retira un nodo.
                     * Los operadores binarios tienen dos nodos hijos, por ello se retiran dos nodos.*/
                    NodeHandler unaryNode = stackNodes.pop();

                    Operator operator = switch (character) {
                        case "?" -> Operator.OPTIONAL;
                        case "*" -> Operator.KLEENE;
                        case "+" -> Operator.POSITIVE;
                        default -> throw new IllegalArgumentException();
                    };

                    NodeHandler node = new NodeHandler(character, operator, 0, unaryNode, null, leavesAL, followPosAL);

                    stackNodes.push(node);
                }
                case ".", "|" -> {
                    NodeHandler nodeL = stackNodes.pop();
                    NodeHandler nodeR = stackNodes.pop();

                    Operator operator = switch (character) {
                        case "." -> Operator.CONCATENATION;
                        case "|" -> Operator.ALTERNATION;
                        default -> throw new IllegalArgumentException();
                    };

                    NodeHandler node = new NodeHandler(character, operator, 0, nodeL, nodeR, leavesAL, followPosAL);

                    stackNodes.push(node);  // Los nodos que se sacaron de la pila ahora están unidos con el operador.
                }
                default -> {
                    Operator foo = Operator.LEAF;
                    int bar = leafNumberAssigner.assignLeafNumber();

                    NodeHandler node = new NodeHandler(character, foo, bar, null, null, leavesAL, followPosAL);

                    leavesAL.add(node);

                    stackNodes.push(node);
                }
            }
        });

        this.rootNode = stackNodes.pop();  // El carácter que se encuentre más a la izquierda será la raíz.
    }

    public NodeHandler getRootNode() {
        return rootNode;
    }
}
