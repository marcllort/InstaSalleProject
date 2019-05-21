package Structures;

import Model.Post;
import Model.User;
import javafx.geometry.Pos;

import javax.xml.bind.Element;

public class ArrayListt<E> {
    public Object[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayListt() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public ArrayListt(Object[] array) {
        this.array = array;
        this.size = array.length;

    }

    public void setElement(int index, E element) {
        if(index > size) { //caso de indice mas grande que medida array se crea un nuevo array de la medida del indice
            Object[] arraynou = new Object[index + 1];
            int i = 0;
            for (Object a : array) {
                arraynou[i] = a;
                i++;
            }
            size = index + 1;
            array = arraynou;
            array[index] = element;
        }
        else if(array[index] == null) { // si casilla vacia
            array[index] = element;
            size++;
        }
        else {
            Object[] arraynou = new Object[size + 1];
            int i = 0;
            for(Object a : array) {
                if(i == index) {
                    arraynou[index] = element;   //en index va element, lo reemplazamos
                    i++;      //aumentamos i
                }
                else {
                    arraynou[i] = a;
                    i++;
                }
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void addElement(E e) {
        if (size == array.length) {

            Object[] arraynou = new Object[size + 1];
            int i = 0;
            for (Object a : array) {
                arraynou[i] = a;
                i++;
            }
            arraynou[size] = e;

            array = arraynou;
        }
        //System.out.println(e.toString());
        array[size] = e;
        //System.out.println(array[size].toString());

        size++;
    }

    public void removeElement(int p) {


        Object[] arraynou = new Object[size - 1];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (i != p) {
                arraynou[j] = array[i];
                j++;
            }
        }

        array = arraynou;

        size--;
    }

    public void removeElement(E element) {

        Object[] arraynou = new Object[size - 1];
        int j = 0;
        for(int i = 0; i < size; i++) {
            if(!array[i].equals(element)) {
                arraynou[j] = array[i];
                j++;
            }
        }
        array = arraynou;
        size--;
    }

    public Object getElement(int i) {
        return array[i];
    }

    public Object searchElement(Object o) {
        int p = -1;
        for (int i = 0; i < size; i++) {
            if (o == array[i]) {
                p = i;
            }
        }
        if (p != -1) {
            return array[p];
        } else {
            return null;
        }
    }

    public Object searchUser(String s) {
        int p = -1;
        User a;
        for (int i = 0; i < size; i++) {
            a = (User) array[i];
            if (a.compareTo(s) == 1) {
                p = i;
            }
        }
        if (p != -1) {
            return array[p];
        } else {
            return null;
        }

    }

    public Object searchPost(int s) {
        int p = -1;
        for (int i = 0; i < size; i++) {
            Post post = (Post) array[i];

            if (post.compareTo(s) == 1) {
                p = i;
            }
        }
        if (p != -1) {
            return array[p];
        } else {
            return null;
        }

    }

    public Post[] getArray() {
        Post[] p = new Post[size];
        for (int i = 0 ; i< size; i++) {
            Post m = (Post) array[i];
            p[i] = m;
        }
        return p;

    }

    public LlistaStrings[] getArrayLl() {
        LlistaStrings[] p = new LlistaStrings[size];
        for (int i = 0 ; i< size; i++) {
            LlistaStrings m = (LlistaStrings) array[i];
            p[i] = m;
        }
        return p;

    }

    public Post[] searchPostPosition(int radi, int x, int y) {
        int p = -1;
        ArrayListt<Post> post = new ArrayListt();

        for (int i = 0; i < size; i++) {


            if (pointInside(radi, x, y, (Post) array[i]) == 0) {
                System.out.println("ENTRA");
                post.addElement((Post) array[i]);
                p = 0;
            }
        }
        if (p != -1) {
            return post.getArray();
        } else {
            return null;
        }

    }

    public void concat(ArrayListt<E> array2){
        for (int i = 0; i< array2.size; i++){
            this.addElement((E)array2.getElement(i));
        }
    }

    private int pointInside(int radi, int x, int y, Post post) {

        double distance = Math.sqrt(Math.abs((y - post.getLocation()[1]) * (y - post.getLocation()[1]) + (x - post.getLocation()[0]) * (x - post.getLocation()[0])));
        System.out.println("DISTANCE "+distance);
        return distance > radi ? 1 : 0;
    }



   /* public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("alex");
        t.insert("aaaa");
        t.insert("almansa");

        ArrayListt<String>  a = t.search("alex");
        for (int i = 0; i< a.getSize(); i++){
            System.out.println(a.getElement(i));
        }
        System.out.println("------------------");

        t.setLimitParaules(1);
        ArrayListt<String>  p = t.search("a");
        for (int i = 0; i< p.getSize(); i++){
            System.out.println(p.getElement(i));
        }


    }*/
}
