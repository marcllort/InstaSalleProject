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
        } else {
            insertTo(root, key, element, elementKey);
        }
    }


    private void insertTo(Node<E> node, int key, E element, String elementName) {
        if (key == node.getKey()) {
            System.out.println("Ja existeix aquest node! No s'ha pogut inserir el node: " + key + "(" + elementName + ")");

        } else if (key > node.getKey()) {
            if (node.getRightSon() == null) {                                                                                   // Si no existeix el node dret, creem el node i el posem
                node.setRightSon(new Node<>(key, element, elementName));                                                        // En cas de caldre un balanceig, es faria als nivells superiors, i si ja som als fills del root no cal fer-ne
                node.setHeight(recalculateHeight(node));                                                                        // Recalculem la alçada del node
            } else {
                insertTo(node.getRightSon(), key, element, elementName);                                                        // Crida recursiva, on el root sera el fill dret
                node.setHeight(recalculateHeight(node));                                                                        // Recalculem alçada, del node pare, un cop el node ja ha sigut inserit
                balance(node, key);                                                                                             // Un cop inserit el node, es possible que ens calgui balancejar el arbre
            }

        } else if (key < node.getKey()) {
            if (node.getLeftSon() == null) {                                                                                    // Si no existeix el node esquerra, creem el node i el posem
                node.setLeftSon(new Node<>(key, element, elementName));                                                         // En cas de caldre un balanceig, es faria als nivells superiors, i si ja som als fills del root no cal fer-ne
                node.setHeight(recalculateHeight(node));                                                                        // Recalculem la alçada del node
            } else {
                insertTo(node.getLeftSon(), key, element, elementName);                                                         // Crida recursiva, on el root sera el fill esquerre
                node.setHeight(recalculateHeight(node));                                                                        // Recalculem alçada, del node pare, un cop el node ja ha sigut inserit
                balance(node, key);                                                                                             // Un cop inserit el node, es possible que ens calgui balancejar el arbre
            }
        }

    }


    private void LeftLeft(Node<E> node) {

        Node<E> left = node.getLeftSon();

        Node<E> llnode = null;
        Node<E> lrnode = null;
        if (node.getLeftSon() != null) {                                                                                        // Si te fill esquerre, guardem els dos fills del fill esquerre
            llnode = node.getLeftSon().getLeftSon();
            lrnode = node.getLeftSon().getRightSon();
        }


        node.setLeftSon(llnode);                                                                                                // El nou leftSon del node, serè el fill esquerre del fill esquerre del node inicial
        node.setRightSon(new Node<>(node.getKey(), node.getElement(), lrnode, node.getRightSon(), node.getElementsName()));     // El nou rightSon serà el node inicial, però ara tindrà com a fill esquerre el fill dret del fill esquerre del node incial
        node.setKey(left.getKey());                                                                                             // El nou node "inicial", serà el anterior leftSon, aquí copiem el key
        node.setElement(left.getElement());                                                                                     // Copiem el objecte
        node.setElementsName(left.getElementsName());                                                                           // Copiem el seu nom

        if (node.getLeftSon() != null) {                                                                                        // En cas de no ser null, al haver hagut una rotació, cal calcular-li la nova alçada
            node.getLeftSon().setHeight(recalculateHeight(node.getLeftSon()));
        }
        if (node.getRightSon() != null) {                                                                                        // En cas de no ser null, al haver hagut una rotació, cal calcular-li la nova alçada
            node.getRightSon().setHeight(recalculateHeight(node.getRightSon()));
        }

        node.setHeight(recalculateHeight(node));                                                                                  // Recalculem l'alçada del node inicial


    }


    private void RightRight(Node<E> node) {

        Node<E> right = node.getRightSon();

        Node<E> rrnode = null;
        Node<E> rlnode = null;
        if (node.getLeftSon() != null) {                                                                                         // Si te fill esquerre, guardem els dos fills del fill esquerre
            rlnode = node.getRightSon().getLeftSon();
            rrnode = node.getRightSon().getRightSon();
        }


        node.setRightSon(rrnode);                                                                                                // El nou leftSon del node, serè el fill esquerre del fill esquerre del node inicial
        node.setLeftSon(new Node<>(node.getKey(), node.getElement(), node.getLeftSon(), rlnode, node.getElementsName()));        // El nou rightSon serà el node inicial, però ara tindrà com a fill esquerre el fill dret del fill esquerre del node incial
        node.setKey(right.getKey());                                                                                             // El nou node "inicial", serà el anterior leftSon, aquí copiem el key
        node.setElement(right.getElement());                                                                                     // Copiem el objecte
        node.setElementsName(right.getElementsName());                                                                           // Copiem el seu nom

        if (node.getLeftSon() != null) {                                                                                         // En cas de no ser null, al haver hagut una rotació, cal calcular-li la nova alçada
            node.getLeftSon().setHeight(recalculateHeight(node.getLeftSon()));
        }
        if (node.getRightSon() != null) {                                                                                        // En cas de no ser null, al haver hagut una rotació, cal calcular-li la nova alçada
            node.getRightSon().setHeight(recalculateHeight(node.getRightSon()));
        }

        node.setHeight(recalculateHeight(node));                                                                                   // Recalculem l'alçada del node inicial


    }


    public void deleteElement(int key) {
        if (root == null) {                                                                                                     // En cas de no tenir root, error, i en cas de tenir-ne crida la el deleteTo, el qual buscarà el node
            System.out.println("Error al borrar, l'arrel de l'arbre és null");
        } else {
            deleteTo(root, key);
        }
    }


    private void deleteTo(Node<E> node, int key) {
        if (key == node.getKey()) {
            deleteNode(node, key);                                                                                              // Borrem el node
        } else if (key > node.getKey()) {
            if (node.getRightSon() == null) {                                                                                   // Si no existeix el node dret, no podem borrar
                System.out.println("No s'ha pogut borrar el node " + key + ", no està al arbre!");
            } else {
                deleteTo(node.getRightSon(), key);                                                                              // Crida recursiva, on el root sera el fill dret
                node.setHeight(recalculateHeight(node));                                                                        // Recalculem alçada, del node pare, un cop el node ja ha sigut inserit
                balance(node, key);                                                                                             // Un cop inserit el node, es possible que ens calgui balancejar el arbre
            }

        } else if (key < node.getKey()) {
            if (node.getLeftSon() == null) {                                                                                    // Si no existeix el node esquerra, no podem borrar
                System.out.println("No s'ha pogut borrar el node " + key + ", no està al arbre!");
            } else {
                deleteTo(node.getLeftSon(), key);                                                                               // Crida recursiva, on el root sera el fill esquerre
                node.setHeight(recalculateHeight(node));                                                                        // Recalculem alçada, del node pare, un cop el node ja ha sigut inserit
                balance(node, key);                                                                                             // Un cop inserit el node, es possible que ens calgui balancejar el arbre
            }
        }
    }

    private void deleteNode(Node<E> node, int key) {

        if ((node.getRightSon() == null) && (root.getLeftSon() == null)) {                                                      // Si no tenim cap fill, posem el node a null (el borrem)
            node = null;
        } else if ((node.getRightSon() == null) || (root.getLeftSon() == null)) {                                               // Si només tenim 1 fill, substituim el node, per el seu únic fill
            if (node.getLeftSon() != null) {
                node = node.getLeftSon();
            } else if (node.getRightSon() != null) {
                node = node.getRightSon();
            }
        } else {                                                                                                                // Si tenim els dos fills, subtituim el node eliminat per el node mes petit del subarbre dret
            Node<E> nodeTemp = lowerNodeRight(node.getRightSon());
            node = nodeTemp;
            deleteTo(node.getRightSon(), node.getKey());
        }

    }

    private Node<E> lowerNodeRight(Node<E> node) {                                                                              // Funcio recursiva per trobar el node més baix del subarbre
        if (node.getLeftSon() == null) {
            return node;
        } else {
            return lowerNodeRight(node.getLeftSon());
        }
    }





}
