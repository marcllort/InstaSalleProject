package Structures.Helpers;


public class Node<E> {                                                              // Node<E> per d'aquesta forma fer una classe generica que puguem aprofitar tant per Usuaris com Posts

    // Variables

    private int key;                                                                // Clau de cada node, per evitar-nos comparar strings
    private String elementsName;                                                    // String identificador del post o usuari
    private E element;                                                              // Objecte/Element que "guarda" el node
    private Node<E> leftSon;                                                        // Fill esquerre
    private Node<E> rightSon;                                                       // Fill dret
    private int height;                                                             // Alçada arbre
    private int balanceFactor;                                                      // Balance factor de tot l'arbre


    //Constructors

    public Node(int key, E element, String elementKey) {                            // Constructor de node simple (sense fills)
        this(key, element, null, null, elementKey);
    }

    public Node(int key, E element, Node<E> leftSon, Node<E> rightSon, String elementKey) {         // Constructor amb fills inclosos

        this.key = key;
        this.element = element;
        this.elementsName = elementKey;
        this.leftSon = leftSon;
        this.rightSon = rightSon;

        if (leftSon == null && rightSon == null) {                                  // Si no te fills la alçada sera 0
            height = 0;
        } else if (rightSon == null) {                                              // En cas de nomes un fill alçada sera 1
            height = leftSon.height + 1;
        } else if (leftSon == null) {
            height = rightSon.height + 1;
        } else {
            height = Math.max(leftSon.height, rightSon.height) + 1;                 // En cas de tenir dos fills, mirem quin dels dos te la major alçada
        }

    }


    // Getters i Setters

    public int getKey() {
        return key;
    }

    public String getElementsName() {
        return elementsName;
    }

    public E getElement() {
        return element;
    }

    public Node<E> getLeftSon() {
        return leftSon;
    }

    public Node<E> getRightSon() {
        return rightSon;
    }

    public int getHeight() {
        return height;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }


    // Overrides

    @Override

    public String toString() {
        return "Key: " + key + " Name: " + elementsName + " LeftSon (Name): "
                + leftSon.getElementsName() + " RightSon (Name): " + rightSon.getElementsName()
                + " Height: " + height + " Balance: " + balanceFactor;
    }


}
