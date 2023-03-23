package main.java.tree;

import java.util.ArrayList;
import java.util.Objects;

public final class NodeHandler {
    int nodeNumber;
    int leafNumber;

    boolean isAcceptanceNode;
    boolean isNullable;

    String lexeme;

    Operator operator;

    Object nodeL;
    Object nodeR;

    ArrayList<Integer> firstPosAL;
    ArrayList<Integer> lastPosAL;

    ArrayList<NodeHandler> leavesAL;

    ArrayList<ArrayList<Object>> followPosAL;

    public NodeHandler(
            int nodeNumber,
            String lexeme,
            Operator operator,
            int leafNumber,
            Object nodeL,
            Object nodeR,
            ArrayList<NodeHandler> leavesAL,
            ArrayList<ArrayList<Object>> followPosAL
    ) {
        /*AL = ArrayList*/
        this.nodeNumber = nodeNumber;
        this.leafNumber = leafNumber;

        this.lexeme = lexeme;

        this.isAcceptanceNode = "#".equals(this.lexeme);
        this.isNullable = false;

        this.operator = operator;

        this.nodeL = nodeL;
        this.nodeR = nodeR;

        this.firstPosAL = new ArrayList<>();
        this.lastPosAL = new ArrayList<>();

        this.leavesAL = leavesAL;

        this.followPosAL = followPosAL;
    }

    public NodeHandler calculateNFL(TreeVisualizer tree) {  // NFL = Nullable-FirstPos-LastPos.
        /*1. Este tipo de operador se llama "operador condicional ternario".
         * Es una sentencia condicional, pero escrita en una sola línea.
         * 2. La palabra reservada "instanceof" se utiliza para revisar si un objeto pertenece a una clase específica.
         * Hay que indicar que el objeto de "this" es de tipo "NodeHandler" para acceder a sus atributos.
         * La recursión es para descender el árbol. Cada vez que se llama "calculateNFL" se está usando otro objeto.
         * 3. Iniciará desde la raíz, y siempre que el objeto sea una instancia de "NodeHandler" empleará recursión.
         * Cuando empleamos "this" nos referimos al nodo en el que nos encontramos actualmente.
         * Los "NFL" se calcularán solamente si sus dos hijos son nulos, o si ya se retornaron ambos hijos.*/
        this.nodeL = this.nodeL instanceof NodeHandler ? ((NodeHandler) this.nodeL).calculateNFL(tree) : null;  // 1, 2.
        this.nodeR = this.nodeR instanceof NodeHandler ? ((NodeHandler) this.nodeR).calculateNFL(tree) : null;

        if (this.operator != null) switch (this.operator) {  // 3.
            case LEAF -> {
                this.isNullable = false;

                this.firstPosAL.add(this.leafNumber);
                this.lastPosAL.add(this.leafNumber);
            }
            case OPTIONAL, KLEENE, POSITIVE -> {
                if (this.nodeL != null) {
                    switch (this.operator) {
                        case OPTIONAL -> this.isNullable = ((NodeHandler) this.nodeL).isNullable;
                        case KLEENE, POSITIVE -> this.isNullable = true;
                        default -> throw new IllegalArgumentException();
                    }

                    this.firstPosAL.addAll(((NodeHandler) this.nodeL).firstPosAL);
                    this.lastPosAL.addAll(((NodeHandler) this.nodeL).lastPosAL);

                    tree.createAST(false, this);
                }
            }
            case CONCATENATION -> {
                if (this.nodeL != null && this.nodeR != null) {
                    this.isNullable = ((NodeHandler) this.nodeL).isNullable && ((NodeHandler) this.nodeR).isNullable;

                    this.firstPosAL.addAll(((NodeHandler) this.nodeL).firstPosAL);
                    this.lastPosAL.addAll(((NodeHandler) this.nodeR).lastPosAL);

                    if (((NodeHandler) this.nodeL).isNullable) {
                        this.firstPosAL.addAll(((NodeHandler) this.nodeR).firstPosAL);
                    }

                    if (((NodeHandler) this.nodeR).isNullable) {
                        this.lastPosAL.addAll(((NodeHandler) this.nodeL).lastPosAL);
                    }

                    tree.createAST(false, this);
                }
            }
            case ALTERNATION -> {
                if (this.nodeL != null && this.nodeR != null) {
                    this.isNullable = ((NodeHandler) this.nodeL).isNullable || ((NodeHandler) this.nodeR).isNullable;

                    this.firstPosAL.addAll(((NodeHandler) this.nodeL).firstPosAL);
                    this.firstPosAL.addAll(((NodeHandler) this.nodeR).firstPosAL);

                    this.lastPosAL.addAll(((NodeHandler) this.nodeL).lastPosAL);
                    this.lastPosAL.addAll(((NodeHandler) this.nodeR).lastPosAL);

                    tree.createAST(false, this);
                }
            }
            default -> throw new IllegalArgumentException();
        }

        return this;  // Se debe retornar el nodo para guardar los cambios efectuados.
    }

    public Object calculateFollowPos() {
        /*1. Se usará un bucle para atravesar la lista que guarda los "últimos" del hijo izquierdo.
         * Cada elemento se guardará en la variable "leafNumber". Esta variable se actualizará en cada iteración.
         * 2. Se utilizará la variable "leafNumber" para obtener el nodo al que pertenece.*/
        this.nodeL = this.nodeL instanceof NodeHandler ? ((NodeHandler) this.nodeL).calculateFollowPos() : null;
        this.nodeR = this.nodeR instanceof NodeHandler ? ((NodeHandler) this.nodeR).calculateFollowPos() : null;

        if (this.operator != null) switch (this.operator) {
            case KLEENE, CONCATENATION -> {
                for (int leafNumber : ((NodeHandler) Objects.requireNonNull(this.nodeL)).lastPosAL) {  // 1.
                    NodeGetter nodeGetter = new NodeGetter();

                    NodeHandler node = nodeGetter.getNodeByLeafNumber(leafNumber, leavesAL);  // 2.

                    FollowPosHandler followPosHandler = new FollowPosHandler();

                    if (node != null) {
                        switch (this.operator) {
                            case KLEENE -> {
                                ArrayList<Integer> foo = ((NodeHandler) this.nodeL).firstPosAL;

                                followPosHandler.groupFollowPos(node.lexeme, (node).leafNumber, foo, followPosAL);
                            }
                            case CONCATENATION -> {
                                ArrayList<Integer> foo = ((NodeHandler) Objects.requireNonNull(this.nodeR)).firstPosAL;

                                followPosHandler.groupFollowPos(node.lexeme, (node).leafNumber, foo, followPosAL);
                            }
                            default -> throw new IllegalArgumentException();
                        }
                    }
                }
            }
            case LEAF, OPTIONAL, POSITIVE, ALTERNATION -> {
                return this;
            }
            default -> throw new IllegalArgumentException();
        }

        return this;
    }
}
