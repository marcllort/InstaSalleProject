package Structures;

/**
 * https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/
 */

import Model.Post;
import Structures.Helpers.HashNode;


public class HashTable<E> {

    private static final int MAX_CAPACITY = 50;                                                                 //Longitud máxima de la tabla de Hash. Servira para generar la llave de los elementos o para encontrar el indice de donde se encuentran los elementos
    private ArrayListt<HashNode<E>> hashTable;                                                                  //La tabla de hash
    private int numNodes;                                                                                       //Numero de nodos que tenemos en la estructura

    public HashTable() {
        hashTable = new ArrayListt<>();
        numNodes = 0;
        for(int i = 0; i < MAX_CAPACITY; i++) {
            hashTable.addElement(null);
        }
    }
                                                                                                                        //Getters y Setters
    public static int getMaxCapacity() { return MAX_CAPACITY; }

    public ArrayListt<HashNode<E>> getHashTable() { return hashTable; }

    public void setHashTable(ArrayListt<HashNode<E>> hashTable) { this.hashTable = hashTable; }

    public int getNumNodes() { return numNodes; }

    public void setNumNodes(int numNodes) { this.numNodes = numNodes; }

    public int getIndexByKey(int key) { return (key % MAX_CAPACITY); }                                          //Funcion que nos permite saber donde esta indexado el elemento

    public HashNode<E> getNode(int key) {                                                                       //Getter de un nodo a partir de la llave

        int index = getIndexByKey(key);
        HashNode<E> node = (HashNode<E>) hashTable.getElement(index);                                           //Buscamos dónde puede estar indexado

        if(node == null) {
            System.out.println("Aquest element no existeix!");
        }
        return node;
    }

    public HashNode<E> getNodeByIndex(int index) {
        return (HashNode<E>) hashTable.getElement(index);
    }

    public HashNode<E> getNode(int key, E element) {

        HashNode<E> node = getNode(key);
        if(node == null) {
            return null;
        }
        else {
            while(node != null) {
                if(element == node.getElement()) {
                    return node;
                }
                node = node.getNextElement();
            }
        }
        System.out.println("Aquest element no existeix!");
        return null;
    }

    public void addNode(int key, E element) {

        int index = getIndexByKey(key);                                                     //Buscamos el
        HashNode<E> node = getNodeByIndex(index);
        numNodes++;

        HashNode<E> newNode = new HashNode<>(key, element);
        newNode.setNextElement(node);
        hashTable.setElement(index, newNode);

    }

    public void removeElement(int key) {

        int index = getIndexByKey(key);                                                         //Buscamos la casilla de la hashTable en donde se puede encontrar el elemento
        HashNode<E> node =(HashNode<E>) hashTable.getElement(index);
        HashNode<E> previousNode = null;

        if(node == null) {
            System.out.println("Aquest element no existeix");
            return;
        }
        else {
            while(node != null) {
                if(node.getKey() == key) break;                                                 //Hemos encontrado el nodo que queremos eliminar. En caso de que no se haya encontrado el nodo valdra null

                previousNode = node;                                                            //el nodo actual pasa a ser el anterior y el siguiente nodo el actual
                node = node.getNextElement();
            }

            if(node == null) {
                System.out.println("Aquest element no existeix!");
                return;
            }
            else {
                numNodes--;                                                                      //Reducimos el numero de elementos de la tabla
                if(previousNode == null) {                                                      //Significa que queremos eliminar el primer elemento, y por lo tanto tenemos que poner como primer elemento el siguiente del nodo a eliminar, y este ponerlo como primer elemento de la tabla con esa llave
                    hashTable.setElement(index, node.getNextElement());                         //
                }
                else {
                    previousNode.setNextElement(node.getNextElement());                         //Al nodo anterior del que queremos eliminar, ponemos como nodo siguiente el siguiente del nodo que queremos eliminar
                }
            }
        }
    }

    public void updateElement(int key, E element) {

        int index = getIndexByKey(key);                                                         //Buscamos casilla donde se encontrara en la HashTable
        HashNode<E> node = getNode(index);                                                         //Cogemos el primer elemento de la Key

        if(node == null) {
            System.out.println("No s'ha pogut modificar cap element ja que aquest no existeix!");
            return;
        }

        while(node != null) {
            if(node.getKey() == key) {
                node.setElement(element);                                                       //Buscamos el elemento y si lo encontramos modificamos el element. En el caso que no lo encuentre muestra mensaje de error
                return;
            }
            node = node.getNextElement();
        }

        if(node == null)
            System.out.println("No s'ha pogut modificar cap element ja que aquest no existeix!");

    }


    public void deletePost(int idPost, int key) {                                                  //Por si se elimina un post o un usuario tambien hay que sacarlo de la estructura. La key indica el hashtag para el que hay que eliminarlo
                                                                                                    //El elemnto de hashNode contiene un array de posts
        int index = getIndexByKey(key);
        HashNode<E> node = getNode(index);

        while(node != null) {
            if(node.getKey() == key) {                                                              //significa que habremos encontrado el hashtag
                break;
            }
            node = node.getNextElement();
        }

        if(node == null) {
            System.out.println("No s'ha pogut modificar cap element ja que aquest hashtag no existeix!");
            return;
        }
        else {
            ArrayListt<Post> posts = (ArrayListt<Post>) node.getElement();

            Post postToDelete = (Post) posts.searchPost(idPost);
            if(postToDelete == null) {
                System.out.println("No s'ha pogut eliminar el post ja que aquest no existeix");
                return;
            }
            else {
                posts.removeElement(postToDelete);
                node.setElement((E)posts);
            }
        }
    }
}
