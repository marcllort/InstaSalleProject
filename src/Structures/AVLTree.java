package Structures;

import Structures.Helpers.Node;

public class AVLTree<E> {                                                                                                       // Fem un AVLTree genèric, aligual que el Node, per poder fer-lo servir tant per posts com per users

    private Node<E> root;                                                                                                       // Guardem el node root, del qual dependràn la resta de nodes que anirem afegint

    public AVLTree() {
        root = null;                                                                                                            // Al crear un tree, inicialment root serà null fins afegir-ne un
    }


    // Inserció

    public void addElement(int key, E element, String elementKey) {                                                             // Funció de add nodes simplificada, comprova si tenim root inicial
        if (root == null) {                                                                                                     // En cas de no tenir-ne, el crea, i en cas de tenir-ne crida la inserció normal a partir de root
            root = new Node<>(key, element, elementKey);
            return;
        }
        insertTo(root, key, element, elementKey);
    }


    private void insertTo(Node<E> node, int key, E element, String elementName) {
        if (key == node.getKey()) {
            System.out.println("Ja existeix aquest node! No s'ha pogut inserir el node: " + key + "(" + elementName + ")");

        } else if (node.getRightSon() != null && key > node.getKey()) {
            insertTo(node.getRightSon(), key, element, elementName);                                                            // Crida recursiva, on el root sera el fill dret
            node.setHeight(calculateHeight(node));                                                                              // Recalculem alçada, del node pare, un cop el node ja ha sigut inserit
            balance(node, key);                                                                                                 // Un cop inserit el node, es possible que ens calgui balancejar el arbre

        } else if (node.getRightSon() == null && key > node.getKey()) {                                                         // Si no existeix el node dret, creem el node i el posem
            node.setRightSon(new Node<>(key, element, elementName));                                                            // En cas de caldre un balanceig, es faria als nivells superiors, i si ja som als fills del root no cal fer-ne
            node.setHeight(calculateHeight(node));                                                                              // Recalculem la alçada del node

        } else if (node.getLeftSon() != null && key < node.getKey()) {
            insertTo(node.getLeftSon(), key, element, elementName);                                                             // Crida recursiva, on el root sera el fill esquerre
            node.setHeight(calculateHeight(node));                                                                              // Recalculem alçada, del node pare, un cop el node ja ha sigut inserit
            balance(node, key);                                                                                                 // Un cop inserit el node, es possible que ens calgui balancejar el arbre

        } else if (node.getLeftSon() == null && key < node.getKey()) {                                                          // Si no existeix el node esquerra, creem el node i el posem
            node.setLeftSon(new Node<>(key, element, elementName));                                                             // En cas de caldre un balanceig, es faria als nivells superiors, i si ja som als fills del root no cal fer-ne
            node.setHeight(calculateHeight(node));                                                                              // Recalculem la alçada del node
        }

    }




}
