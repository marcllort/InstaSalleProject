package Structures.Helpers;

import Structures.ArrayListt;

public class GraphNode<E> {
  
    private int id;
    private E element;
    private ArrayListt<E> connections;


    public GraphNode(E element) {
        this.element = element;
        connections = new ArrayListt<>();

    }
  
}
