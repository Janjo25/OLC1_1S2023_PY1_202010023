package main.java.tree;

import java.util.ArrayList;

public final class NodeGetter {
    public NodeHandler getNodeByLeafNumber(int leafNumber, ArrayList<NodeHandler> arrayListLeaves) {
        for (NodeHandler leafNode : arrayListLeaves) {
            if (leafNumber == leafNode.leafNumber) return leafNode;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Object> getNodeFollowPos(int leafNumber, ArrayList<ArrayList<Object>> ArrayListFollowPos) {
        /*Este método se usa para retornar un "ArrayList" con el lexema y los "siguientes" de un nodo específico.
         * Con la "ArrayList" retornada se verá si es posible crear un nuevo estado de transición.*/
        ArrayList<Object> lexemeFollowPosAL = new ArrayList<>();

        for (ArrayList<Object> element : ArrayListFollowPos) {
            if (leafNumber == (int) element.get(1)) {
                lexemeFollowPosAL.add(element.get(0));
                lexemeFollowPosAL.add(((ArrayList<Integer>) element.get(2)).clone());

                return lexemeFollowPosAL;
            }
        }

        lexemeFollowPosAL.add(null);
        lexemeFollowPosAL.add(new ArrayList<>());

        return lexemeFollowPosAL;
    }

    public boolean getAcceptanceNode(int numLeave, ArrayList<NodeHandler> leaves) {
        for (NodeHandler element : leaves) {
            if (numLeave == element.leafNumber) return element.isAcceptanceNode;
        }

        return false;
    }
}
