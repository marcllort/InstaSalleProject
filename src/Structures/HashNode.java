package Structures.Helpers;

import Model.Post;
import Model.User;

public class NodeHash {
      //atributos
    private int key;                                                                    //Identifica a los elementos
    private E element;                                                                  //Elemento a guardar
    private HashNode<E> nextElement;                                                    //Siguiente nodo que tenga la misma Key que el anterior

    private HashNode(int key, E element) {
        this.key = key;
        this.element = element;
        nextElement = null;
    }

    private HashNode() {
        element = null;
        nextElement = null;
        key = 0;
    }

    //Getters y Setters

    public int getKey() { return key; }

    public void setKey(int key) { this.key = key; }

    public E getElement() { return element; }

    public void setElement(E element) { this.element = element; }

    public HashNode<E> getNextElement() { return nextElement; }

    public void setNextElement(HashNode<E> nextElement) { this.nextElement = nextElement; }




    public String getNomElement() {
        if(element instanceof User) {
            return ((User) element).getUsername();
        }
        else {
            return ((Post) element).toString();
        }
    }
}
