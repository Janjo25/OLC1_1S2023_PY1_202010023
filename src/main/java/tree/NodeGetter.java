package main.java.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class NodeGetter {
    Set<Object> hashSetMergedLexemeFollowPos;

    public NodeGetter() {
        this.hashSetMergedLexemeFollowPos = new HashSet<>();
    }

    public NodeHandler getNodeByLeafNumber(int leafNumber, ArrayList<NodeHandler> arrayListLeaves) {
        for (NodeHandler leafNode : arrayListLeaves) {
            if (leafNumber == leafNode.leafNumber) return leafNode;
        }

        return null;
    }

    @SuppressWarnings({"unchecked", "GrazieInspection"})
    public ArrayList<Object> getNodeFollowPos(
            boolean flagRecursion,
            int leafNumber,
            ArrayList<ArrayList<Object>> arrayListFollowPos
    ) {
        /*AL = ArrayList*/
        /*Este método se usa para retornar un "ArrayList" con el lexema y los "siguientes" de un nodo específico.
         * Con la "ArrayList" retornada se verá si es posible crear un nuevo estado de transición.
         * 1. Se utilizará recursión para hallar los lexemas duplicados en un estado.
         * Si un lexema se encuentra duplicado, se unirán sus primeros con los del lexema original.
         * El lexema y los "followPos" originales no se encuentra en el "HashSet", solo los que se unen con este.
         * 2. La variable "valueConstructor" se refiere a cada valor que se encuentra en la columna "valores".*/
        ArrayList<Object> lexemeFollowPosAL = new ArrayList<>();

        for (ArrayList<Object> followPos : arrayListFollowPos) {
            if (leafNumber == (int) followPos.get(1)) {
                lexemeFollowPosAL.add(followPos.get(0));
                lexemeFollowPosAL.add(((ArrayList<Integer>) followPos.get(2)).clone());

                if (flagRecursion) {  // 1.
                    ArrayList<Integer> duplicateLexemeFollowPosAL = new ArrayList<>();

                    for (Integer valueConstructor : (ArrayList<Integer>) followPos.get(2)) {  // 2.
                        ArrayList<Object> recursionFollowPosConstructorAL = getNodeFollowPos(
                                false,
                                valueConstructor,
                                arrayListFollowPos
                        );

                        if (followPos.get(0).equals(recursionFollowPosConstructorAL.get(0))) {
                            ArrayList<Integer> foo = (ArrayList<Integer>) recursionFollowPosConstructorAL.get(1);

                            for (Integer duplicateLexemeFollowPos : foo) {
                                if (!((ArrayList<Integer>) followPos.get(2)).contains(duplicateLexemeFollowPos)) {
                                    duplicateLexemeFollowPosAL.add(duplicateLexemeFollowPos);

                                    hashSetMergedLexemeFollowPos.add(recursionFollowPosConstructorAL);
                                }
                            }
                        }
                    }

                    ((ArrayList<Integer>) lexemeFollowPosAL.get(1)).addAll(duplicateLexemeFollowPosAL);
                }

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
