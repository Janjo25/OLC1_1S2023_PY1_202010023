package main.java.tree;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

import java.io.File;
import java.io.IOException;

public final class TreeVisualizer {
    StringBuilder stringBuilder;

    public TreeVisualizer() {
        this.stringBuilder = new StringBuilder("digraph {");
    }

    public void createAST(boolean flagIsComplete, NodeHandler node) {
        /*1. Arreglo con los distintos tipos de nodos que puede haber: padre, hijo izquierdo e hijo derecho.
         * Se usará para no repetir código. Con un bucle se indicará que tipo de nodo se va a utilizar.*/
        if (!flagIsComplete) {
            NodeHandler[] arrayNodeType = {node, ((NodeHandler) node.nodeL), ((NodeHandler) node.nodeR)};  // 1.

            switch (node.operator) {
                case OPTIONAL, KLEENE, POSITIVE -> {
                    for (int i = 0; i < 2; i++) {
                        createNode(arrayNodeType, i);
                    }

                    stringBuilder.append("\"Node-").append(node.nodeNumber).append("\"");
                    stringBuilder.append(" -> ");
                    stringBuilder.append("\"Node-").append(((NodeHandler) node.nodeL).nodeNumber).append("\"");
                }
                case CONCATENATION, ALTERNATION -> {
                    for (int i = 0; i < 3; i++) {
                        createNode(arrayNodeType, i);
                    }

                    stringBuilder.append("\"Node-").append(node.nodeNumber).append("\"");
                    stringBuilder.append(" -> ");
                    stringBuilder.append("\"Node-").append(((NodeHandler) node.nodeL).nodeNumber).append("\"");

                    stringBuilder.append("\"Node-").append(node.nodeNumber).append("\"");
                    stringBuilder.append(" -> ");
                    stringBuilder.append("\"Node-").append(((NodeHandler) node.nodeR).nodeNumber).append("\"");
                }
                default -> throw new IllegalArgumentException();
            }
        } else {
            stringBuilder.append("}");

            int fileNumber = 1;

            File file = new File("data/ASTs/AST.png");

            while (file.exists()) {
                file = new File("data/ASTs/AST " + "(" + fileNumber + ")" + ".png");

                fileNumber++;
            }

            try {
                Graphviz.fromString(stringBuilder.toString()).render(Format.PNG).toFile(file);
            } catch (IOException fileIOException) {
                System.err.println("Se produjo un error al intentar guardar la imagen.");
            }
        }
    }

    private void createNode(NodeHandler[] arrayNodeType, int i) {
        NodeHandler nodeType = arrayNodeType[i];

        stringBuilder.append("\"").append("Node-").append(nodeType.nodeNumber).append("\"");

        stringBuilder.append(" [label = <<table>");

        stringBuilder.append("<tr>");
        stringBuilder.append("<td>").append(nodeType.firstPosAL).append("</td>");
        stringBuilder.append("<td>").append(nodeType.isNullable).append("</td>");
        stringBuilder.append("<td>").append(nodeType.lastPosAL).append("</td>");
        stringBuilder.append("</tr>");

        stringBuilder.append("<tr>");
        stringBuilder.append("<td colspan=\"3\">").append(nodeType.lexeme).append("</td>");
        stringBuilder.append("</tr>");

        stringBuilder.append("</table>>");
        stringBuilder.append(",shape = none];");
    }
}
