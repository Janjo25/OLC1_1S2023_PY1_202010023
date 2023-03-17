package main.java.tree;

public enum Operator {  // El nombre de la clase debe de ser singular, ya que representa un grupo de valores contantes.
    LEAF,  // No es un operador, pero se usará para identificar a los nodos que sean hojas.
    OPTIONAL,
    KLEENE,
    POSITIVE,
    CONCATENATION,
    ALTERNATION,
}
