package Structures;

import Structures.Helpers.NodeAVL;


public class AVLTree<E> {                                                                                                       // Fem un AVLTree genèric, aligual que el Node, per poder fer-lo servir tant per posts com per users

    public NodeAVL<E> root;                                                                                                     // Guardem el node root, del qual dependràn la resta de nodes que anirem afegint

    public AVLTree() {
        root = null;                                                                                                            // Al crear un tree, inicialment root serà null fins afegir-ne un
    }


    // Inserció

    public void addElement(int key, E element, String elementName) {                                                             // Funció de add nodes simplificada, comprova si tenim root inicial
        if (root == null) {                                                                                                     // En cas de no tenir-ne, el crea, i en cas de tenir-ne crida la inserció normal a partir de root
            root = new NodeAVL<>(key, element, elementName);
        } else {
            insertTo(root, key, element, elementName);
        }
    }

    private void insertTo(NodeAVL<E> node, int keyInsert, E elementInsert, String elementNameInsert) {
        if (keyInsert == node.getKey()) {
            System.out.println("Ja existeix aquest node! No s'ha pogut inserir el node: " + keyInsert + "(" + elementNameInsert + ")");

        } else if (keyInsert > node.getKey()) {
            if (node.getRightSon() == null) {                                                                                   // Si no existeix el node dret, creem el node i el posem
                node.setRightSon(new NodeAVL<>(keyInsert, elementInsert, elementNameInsert));                                   // En cas de caldre un balanceig, es faria als nivells superiors, i si ja som als fills del root no cal fer-ne
                node.setHeight(reCalculateHeight(node));                                                                        // Recalculem la alçada del node
            } else {
                insertTo(node.getRightSon(), keyInsert, elementInsert, elementNameInsert);                                      // Crida recursiva, on el root sera el fill dret
                node.setHeight(reCalculateHeight(node));                                                                        // Recalculem alçada, del node pare, un cop el node ja ha sigut inserit
                balanceFactor(node, keyInsert);                                                                                 // Un cop inserit el node, es possible que ens calgui balancejar el arbre
            }

        } else if (keyInsert < node.getKey()) {
            if (node.getLeftSon() == null) {                                                                                    // Si no existeix el node esquerra, creem el node i el posem
                node.setLeftSon(new NodeAVL<>(keyInsert, elementInsert, elementNameInsert));                                    // En cas de caldre un balanceig, es faria als nivells superiors, i si ja som als fills del root no cal fer-ne
                node.setHeight(reCalculateHeight(node));                                                                        // Recalculem la alçada del node
            } else {
                insertTo(node.getLeftSon(), keyInsert, elementInsert, elementNameInsert);                                       // Crida recursiva, on el root sera el fill esquerre
                node.setHeight(reCalculateHeight(node));                                                                        // Recalculem alçada, del node pare, un cop el node ja ha sigut inserit
                balanceFactor(node, keyInsert);                                                                                 // Un cop inserit el node, es possible que ens calgui balancejar el arbre
            }
        }

    }

    private void LeftLeft(NodeAVL<E> node) {

        NodeAVL<E> left = node.getLeftSon();

        NodeAVL<E> llnode = null;
        NodeAVL<E> lrnode = null;
        if (node.getLeftSon() != null) {                                                                                        // Si te fill esquerre, guardem els dos fills del fill esquerre
            llnode = node.getLeftSon().getLeftSon();
            lrnode = node.getLeftSon().getRightSon();
        }


        node.setLeftSon(llnode);                                                                                                // El nou leftSon del node, serè el fill esquerre del fill esquerre del node inicial
        node.setRightSon(new NodeAVL<>(node.getKey(), node.getElement(), lrnode, node.getRightSon(), node.getElementName()));  // El nou rightSon serà el node inicial, però ara tindrà com a fill esquerre el fill dret del fill esquerre del node incial
        node.setKey(left.getKey());                                                                                             // El nou node "inicial", serà el anterior leftSon, aquí copiem el key
        node.setElement(left.getElement());                                                                                     // Copiem el objecte
        node.setElementName(left.getElementName());                                                                           // Copiem el seu nom

        if (node.getLeftSon() != null) {                                                                                        // En cas de no ser null, al haver hagut una rotació, cal calcular-li la nova alçada
            node.getLeftSon().setHeight(reCalculateHeight(node.getLeftSon()));
        }
        if (node.getRightSon() != null) {                                                                                       // En cas de no ser null, al haver hagut una rotació, cal calcular-li la nova alçada
            node.getRightSon().setHeight(reCalculateHeight(node.getRightSon()));
        }

        node.setHeight(reCalculateHeight(node));                                                                                // Recalculem l'alçada del node inicial

    }

    private void RightRight(NodeAVL<E> node) {

        NodeAVL<E> right = node.getRightSon();

        NodeAVL<E> rrnode = null;
        NodeAVL<E> rlnode = null;
        if (node.getRightSon() != null) {                                                                                        // Si te fill esquerre, guardem els dos fills del fill esquerre
            rlnode = node.getRightSon().getLeftSon();
            rrnode = node.getRightSon().getRightSon();
        }


        node.setRightSon(rrnode);                                                                                                // El nou leftSon del node, serè el fill esquerre del fill esquerre del node inicial
        node.setLeftSon(new NodeAVL<>(node.getKey(), node.getElement(), node.getLeftSon(), rlnode, node.getElementName()));     // El nou rightSon serà el node inicial, però ara tindrà com a fill esquerre el fill dret del fill esquerre del node incial
        node.setKey(right.getKey());                                                                                             // El nou node "inicial", serà el anterior leftSon, aquí copiem el key
        node.setElement(right.getElement());                                                                                     // Copiem el objecte
        node.setElementName(right.getElementName());                                                                           // Copiem el seu nom

        if (node.getLeftSon() != null) {                                                                                         // En cas de no ser null, al haver hagut una rotació, cal calcular-li la nova alçada
            node.getLeftSon().setHeight(reCalculateHeight(node.getLeftSon()));
        }
        if (node.getRightSon() != null) {                                                                                        // En cas de no ser null, al haver hagut una rotació, cal calcular-li la nova alçada
            node.getRightSon().setHeight(reCalculateHeight(node.getRightSon()));
        }

        node.setHeight(reCalculateHeight(node));                                                                                 // Recalculem l'alçada del node inicial

    }


    // Eliminació

    public void deleteElement(int key) {
        if (root == null) {                                                                                                     // En cas de no tenir root, error, i en cas de tenir-ne crida la el deleteTo, el qual buscarà el node
            System.out.println("Error al borrar, l'arrel de l'arbre és null");
        } else {
            root = deleteTo(root, key);
        }
    }

    private NodeAVL<E> deleteTo(NodeAVL<E> node, int key) {
        if (key == node.getKey()) {
            node = deleteNode(node);                                                                                            // Borrem el node
            if (node == null) return null;
        } else if (key > node.getKey()) {
            if (node.getRightSon() == null) {                                                                                   // Si no existeix el node dret, no podem borrar
                System.out.println("No s'ha pogut borrar el node " + key + ", no està al arbre!");
                return null;
            } else {
                node.setRightSon(deleteTo(node.getRightSon(), key));                                                            // Crida recursiva, on el root sera el fill dret
                node.setHeight(reCalculateHeight(node));                                                                        // Recalculem alçada, del node pare, un cop el node ja ha sigut borrat
                balanceFactorDelete(node);                                                                                      // Un cop borrat el node, es possible que ens calgui balancejar el arbre

            }

        } else if (key < node.getKey()) {
            if (node.getLeftSon() == null) {                                                                                    // Si no existeix el node esquerra, no podem borrar
                System.out.println("No s'ha pogut borrar el node " + key + ", no està al arbre!");
                return null;
            } else {
                node.setLeftSon(deleteTo(node.getLeftSon(), key));                                                              // Crida recursiva, on el root sera el fill esquerre
                node.setHeight(reCalculateHeight(node));                                                                        // Recalculem alçada, del node pare, un cop el node ja ha sigut borrat
                balanceFactorDelete(node);                                                                                      // Un cop borrat el node, es possible que ens calgui balancejar el arbre

            }
        }

        return node;
    }

    private NodeAVL<E> deleteNode(NodeAVL<E> node) {

        if ((node.getRightSon() == null) && (node.getLeftSon() == null)) {                                                      // Si no tenim cap fill, posem el node a null (el borrem)
            node = null;
        } else if ((node.getRightSon() == null) || (node.getLeftSon() == null)) {                                               // Si només tenim 1 fill, substituim el node, per el seu únic fill
            if (node.getLeftSon() != null) {
                node = node.getLeftSon();
            } else if (node.getRightSon() != null) {
                node = node.getRightSon();
            }
        } else {                                                                                                                // Si tenim els dos fills, subtituim el node eliminat per el node mes petit del subarbre dret
            NodeAVL<E> nodeTemp = lowerNodeRight(node.getRightSon());
            node.setKey(nodeTemp.getKey());
            node.setElement(nodeTemp.getElement());
            node.setElementName(nodeTemp.getElementName());
            node.setRightSon(deleteTo(node.getRightSon(), node.getKey()));

        }
        return node;
    }

    private NodeAVL<E> lowerNodeRight(NodeAVL<E> node) {                                                                              // Funcio recursiva per trobar el node més baix del subarbre
        if (node.getLeftSon() == null) {
            return node;
        } else {
            return lowerNodeRight(node.getLeftSon());
        }
    }


    // Busqueda

    public E search(int key) {
        if (root == null) {                                                                                                      // En cas de no tenir root, error, i en cas de tenir-ne crida la cerca de element
            System.out.println("Error! ArbreAVL buit");
            return null;
        } else {
            return searchElementByNode(root, key);
        }
    }

    private E searchElementByNode(NodeAVL<E> node, int key) {
        if (key == node.getKey()) {
            System.out.println(node.getElementName() + " trobat");
            return node.getElement();                                                                                           // Retornem el Element guardat, quan trobem que el key coincideix amb el buscat
        } else if (key > node.getKey()) {
            if (node.getRightSon() == null) {                                                                                   // Si no existeix el node dret, node no trobat, retornem null
                System.out.println("Node no trobat");
                return null;
            } else {
                return searchElementByNode(node.getRightSon(), key);                                                            // Seguim buscant per la dreta
            }
        } else if (key < node.getKey()) {
            if (node.getLeftSon() == null) {                                                                                    // Si no existeix el node esquerra, node no trobat, retornem null
                System.out.println("Node no trobat");
                return null;
            } else {
                return searchElementByNode(node.getLeftSon(), key);                                                             // Seguim buscant per la esquerra
            }
        }
        return null;
    }


    // Balanceig

    private void balanceFactor(NodeAVL<E> node, int keyInsert) {
        node.setBalanceFactor(calculateBalanceFactor(node));

        if (node.getBalanceFactor() > 1) {

            if (keyInsert < node.getLeftSon().getKey()) {                                                                       // El key del node inserit es menor al del fill esquerre del node root
                LeftLeft(node);                                                                                                 // Vol dir que han inserit a l'esquerra del fill esquerra --> LL
            } else {
                RightRight(node.getLeftSon());                                                                                  // En cas de que el inserit sigui mes gran, vol dir que es major que el fill esquerre de root
                LeftLeft(node);                                                                                                 // Vol dir que han inserit a la dreta del fill esquerra --> LR
            }

        } else if (node.getBalanceFactor() < -1) {

            if (keyInsert > node.getRightSon().getKey()) {                                                                      // El key del node inserit es major al del fill dret del node root
                RightRight(node);                                                                                               // Vol dir que han inserit a l'dreta del fill dret --> RR
            } else {
                LeftLeft(node.getRightSon());                                                                                   // En cas de que el inserit sigui menor, vol dir que es menor que el fill dret de root
                RightRight(node);                                                                                               // Vol dir que han inserit a la esquerra del fill dret --> RL
            }

        }

    }

    private void balanceFactorDelete(NodeAVL<E> node) {
        node.setBalanceFactor(calculateBalanceFactor(node));

        if (node.getBalanceFactor() > 1) {

            if (calculateBalanceFactor(node.getLeftSon()) >= 0) {                                                               // Balance factor fill esquerre es superior o igual a 0
                LeftLeft(node);                                                                                                 // --> LL
            } else {
                RightRight(node.getLeftSon());                                                                                  // En cas contrari
                LeftLeft(node);                                                                                                 // Vol dir que han inserit a la dreta del fill esquerra --> LR
            }

        } else if (node.getBalanceFactor() < -1) {

            if (calculateBalanceFactor(node.getRightSon()) <= 0) {                                                              // Balance factor fill dret es menor o igual a 0
                RightRight(node);                                                                                               // --> RR
            } else {
                LeftLeft(node.getRightSon());                                                                                   // En cas contrari
                RightRight(node);                                                                                               // Vol dir que han inserit a la esquerra del fill dret --> RL
            }

        }

    }

    private int calculateBalanceFactor(NodeAVL<E> node) {
        int leftHeight = 0;                                                                                                     // Retornem la resta de l'alçada de cada un dels fills
        int rightHeight = 0;

        if (node.getLeftSon() != null) leftHeight = node.getLeftSon().getHeight() + 1;
        if (node.getRightSon() != null) rightHeight = node.getRightSon().getHeight() + 1;

        return leftHeight - rightHeight;
    }

    private int reCalculateHeight(NodeAVL<E> node) {
        if (node == null) {
            return 0;
        } else if (node.getLeftSon() != null && node.getRightSon() != null) {                                                   // En cas de que cap sigui null, tornem l'alçada max dels dos més 1
            return Math.max(node.getLeftSon().getHeight(), node.getRightSon().getHeight()) + 1;
        } else if (node.getLeftSon() == null && node.getRightSon() == null) {                                                   // Si no té cap fill, alçada es 0 perque és node fulla
            return 0;
        } else if (node.getLeftSon() != null && node.getRightSon() == null) {                                                   // Si només té un fill, serà l'alçada del fill més 1
            return node.getLeftSon().getHeight() + 1;
        } else if (node.getLeftSon() == null && node.getRightSon() != null) {                                                   // Si només té un fill, serà l'alçada del fill més 1
            return node.getRightSon().getHeight() + 1;
        }
        return 0;
    }


    // Test

    public void inOrder(NodeAVL node, int nivell) {                                                                                                  // Serveix per visualitzar el inOrdre del arbre/subarbre

        if (node != null) {
            inOrder(node.getLeftSon(), nivell + 1);
            if (nivell == 0) {
                System.out.println(root.getKey() + " (" + root.getElementName() + ") N:" + nivell + "  --> ");
            } else {
                System.out.println(node.getKey() + " (" + node.getElementName() + ") N:" + nivell + "  --> ");
            }
            inOrder(node.getRightSon(), nivell + 1);
        }
    }


}
