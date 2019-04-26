package Structures;

import javax.xml.bind.Element;

public class ArrayListt<E> {
    public Object[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayListt() {
        array = new Object[DEFAULT_CAPACITY];
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
        array[size++] = e;
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
        for (int i = 0; i < size; i++) {
            if (array[i].equals(s)) {
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
            if (array[i].equals(s)) {
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
        Integer[] a = {1, 2, 3, 4};
        ArrayListt<Integer> p = new ArrayListt<Integer>(a);
        p.addElement(5);
        p.addElement(6);
        p.addElement(7);
        p.removeElement(3);
        System.out.println("OBJECT" + p.searchElement(2));
        System.out.println("AAAA" + p.getElement(2));


        for (int i = 0; i < p.getSize(); i++) {
            System.out.println(p.array[i]);
        }

    }
}
