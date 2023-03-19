package main.java.tree;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.MutableGraph;

import java.io.File;
import java.io.IOException;

public final class TreeVisualizer {
    MutableGraph digraph;

    public TreeVisualizer() {
        this.digraph = Factory.mutGraph("AST").setDirected(true);
    }

    public void createAST(boolean flagIsComplete, NodeHandler node) {
        if (!flagIsComplete) {
            NodeHandler foo = (NodeHandler) node.nodeL;
            NodeHandler bar = (NodeHandler) node.nodeR;

            switch (node.operator) {
                case OPTIONAL, KLEENE, POSITIVE -> {
                    digraph.add(Factory.mutNode("Node-" + node.nodeNumber).add(Label.of(node.lexeme)));
                    digraph.add(Factory.mutNode("Node-" + foo.nodeNumber).add(Label.of(foo.lexeme)));

                    digraph.add(Factory.mutNode("Node-" + node.nodeNumber).addLink("Node-" + foo.nodeNumber));
                }
                case CONCATENATION, ALTERNATION -> {
                    digraph.add(Factory.mutNode("Node-" + node.nodeNumber).add(Label.of(node.lexeme)));
                    digraph.add(Factory.mutNode("Node-" + foo.nodeNumber).add(Label.of(foo.lexeme)));
                    digraph.add(Factory.mutNode("Node-" + bar.nodeNumber).add(Label.of(bar.lexeme)));

                    digraph.add(Factory.mutNode("Node-" + node.nodeNumber).addLink("Node-" + foo.nodeNumber));
                    digraph.add(Factory.mutNode("Node-" + node.nodeNumber).addLink("Node-" + bar.nodeNumber));
                }
                default -> throw new IllegalArgumentException();
            }
        } else {
            int fileNumber = 1;

            File file = new File("data/ASTs/AST.png");

            while (file.exists()) {
                file = new File("data/ASTs/AST " + "(" + fileNumber + ")" + ".png");

                fileNumber++;
            }

            try {
                Graphviz.fromGraph(digraph).render(Format.PNG).toFile(file);
            } catch (IOException fileIOException) {
                System.err.println("Se produjo un error al intentar guardar la imagen.");
            }
        }
    }
}
