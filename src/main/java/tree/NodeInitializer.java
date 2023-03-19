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

        TreeVisualizer treeVisualizer = new TreeVisualizer();

        Stack<NodeHandler> stackNodes = new Stack<>();

        String[] arraySplitRE = regExp.replace(" ", "").split("");

        ArrayList<String> arrayListRECharacters = new ArrayList<>(Arrays.asList(arraySplitRE));

        Collections.reverse(arrayListRECharacters);  // Una ER en notación polaca se lee de derecha a izquierda.

        int nodeNum = 0;

        for (String character : arrayListRECharacters) {
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

                    treeVisualizer.createAST(false, node);

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

                    treeVisualizer.createAST(false, node);

                    stackNodes.push(node);  // Los nodos que se sacaron de la pila ahora están unidos con el operador.
                }
                default -> {
                    Operator foo = Operator.LEAF;
                    int bar = leafNumberAssigner.assignLeafNumber();

                    NodeHandler node = new NodeHandler(nodeNum, character, foo, bar, null, null, leavesAL, followPosAL);

                    leavesAL.add(node);

                    stackNodes.push(node);
                }
            }

            nodeNum++;
        }

        treeVisualizer.createAST(true, null);

        this.rootNode = stackNodes.pop();  // El carácter que se encuentre más a la izquierda será la raíz.
    }

    public NodeHandler getRootNode() {
        return rootNode;
    }
}
