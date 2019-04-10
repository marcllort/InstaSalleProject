package Structures.Helpers;


public class NodeAVL<E> {                                                              // Node<E> per d'aquesta forma fer una classe generica que puguem aprofitar tant per Usuaris com Posts

    // Variables

    private int key;                                                                // Clau de cada node, per evitar-nos comparar strings, i aixi saber on ha de colocar el arbre
    private String elementsName;                                                    // String identificador del post o usuari
    private E element;                                                              // Objecte/Element que "guarda" el node
    private NodeAVL<E> leftSon;                                                        // Fill esquerre
    private NodeAVL<E> rightSon;                                                       // Fill dret
    private int height;                                                             // Alçada arbre
    private int balanceFactor;                                                      // Balance factor de tot l'arbre


    //Constructors

    public NodeAVL(int key, E element, String elementKey) {                            // Constructor de node simple (sense fills)
        this(key, element, null, null, elementKey);
    }

    public NodeAVL(int key, E element, NodeAVL<E> leftSon, NodeAVL<E> rightSon, String elementKey) {         // Constructor amb fills inclosos

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

    public NodeAVL<E> getLeftSon() {
        return leftSon;
    }

    public NodeAVL<E> getRightSon() {
        return rightSon;
    }

    public int getHeight() {
        return height;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setElementsName(String elementsName) {
        this.elementsName = elementsName;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setLeftSon(NodeAVL<E> leftSon) {
        this.leftSon = leftSon;
    }

    public void setRightSon(NodeAVL<E> rightSon) {
        this.rightSon = rightSon;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    // Overrides

    @Override

    public String toString() {
        return "Key: " + key + " Name: " + elementsName + " LeftSon (Name): "
                + leftSon.getElementsName() + " RightSon (Name): " + rightSon.getElementsName()
                + " Height: " + height + " Balance: " + balanceFactor;
    }


}
