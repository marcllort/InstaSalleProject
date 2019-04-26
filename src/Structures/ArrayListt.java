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
        System.out.println(array[size].toString());

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

    public Object getElement(int i) {
        return array[i];
    }

    public  Object searchElement(Object o){
        int p = -1;
        for (int i = 0; i < size; i++) {
            if (o == array[i]) {
                p = i;
            }
        }
        if (p!=-1) {
            return array[p];
        }else {
            return null;
        }
    }

    public  Object searchUser(String s){
        int p = -1;
        User a ;
        for (int i = 0; i < size; i++) {
            a = (User) array[i];
            if (a.compareTo(s) == 1) {
                System.out.println("AAAAA");
                p = i;
            }
        }
        if (p!=-1) {
            return array[p];
        }else {
            return null;
        }

    }

    public  Object searchPost(int s){
        int p = -1;
        for (int i = 0; i < size; i++) {
            Post post =(Post) array[i];

            if (post.compareTo(s)==1) {
                p = i;
            }
        }
        if (p!=-1) {
            return array[p];
        }else {
            return null;
        }

    }


    public static void main(String[] args) {
        User a = new User();
        a.setUsername("alex");
        User b = new User();
        b.setUsername("bala");

        ArrayListt<User> p = new ArrayListt<User>();
        p.addElement(a);
        p.addElement(b);




        User j = (User)p.getElement(0);
        System.out.println(j.getUsername());

        User r = (User)p.searchUser("alex");
        System.out.println("OBJECT" + r.getUsername());




    }
}
