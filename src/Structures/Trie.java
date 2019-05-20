package Structures;

import java.util.ArrayList;

public class Trie {
    private final int LLETRESABC = 26;
    private TrieNode root;
    private int paraules;
    private int limitParaules;

    public Trie() {
        paraules = 0;
        limitParaules = 50;
        root = new TrieNode();

    }

    public void insert(String paraula) {
        if (limitParaules > paraules) {
            TrieNode nodeAct;
            nodeAct = root;
            int length, index;

            length = paraula.length();

            for (int i = 0; i < length; i++) {
                index = paraula.charAt(i) - 'a';

                if (nodeAct.getFills()[index] == null) {
                    nodeAct.setFill(new TrieNode(), index);
                }
                nodeAct = nodeAct.getFill(index);

            }
            nodeAct.setFinalParaula(1);
            paraules++;
        }else {
            System.out.println("Màxim de paraules als tries ");
        }

    }

    public ArrayListt<String> search(String paraula) {
        TrieNode nodeAct;
        nodeAct = root;
        int length, index;
        ArrayListt<LlistaStrings> result = new ArrayListt<>();

        length = paraula.length();

        for (int i = 0; i < length; i++) {
            index = paraula.charAt(i) - 'a';

            if (nodeAct.getFill(index) == null) {

                return null;
            }

            nodeAct = nodeAct.getFill(index);

        }

        //Arribat aquest punt estem al final de la paraula que ens han entrat i construirem una llista de possibles paraules ordenades pel pes de les mateixes.

        LlistaStrings a = new LlistaStrings(paraula, nodeAct.isFinalParaula()); //Paraula i node del que partim


        result = afegeixTotesParaules(nodeAct, a, "zzzz");                              //Metode que afegeix totes les paraules restants
        result = Ordena(result);                                                //Metode que ordena els resultats


        //Passo el resultat a arraylist d'string un altre cop
        ArrayListt<String> strings = new ArrayListt<String>();
        LlistaStrings l;
        for (int p = 0; p < result.getSize(); p++) {
            l = (LlistaStrings) result.getElement(p);
            strings.addElement(l.getParaula());
        }


        return strings;
    }


    //Metode que afegeix totes les paraules que queden a un arraylist de Strings
    public ArrayListt<LlistaStrings> afegeixTotesParaules(TrieNode node, LlistaStrings paraula, String search) {
        ArrayListt<LlistaStrings> llista = new ArrayListt<LlistaStrings>();
        if (search == "zzzz") {
            search = paraula.getParaula();
        }

        char a;

        for (int i = 0; i < LLETRESABC; i++) {
            if (node.getFill(i) != null) {
                a = 'a';
                a += i;
                search += a;

                if (node.getFill(i).isFinalParaula() >= 1) {
                    LlistaStrings nova = new LlistaStrings(search, node.getFill(i).isFinalParaula());
                    llista.addElement(nova);
                }
                llista.concat(afegeixTotesParaules(node.getFill(i), paraula, search));

            }
        }

        return llista;

    }

    //Funcio que serveix per que un cop mostrades les opcions, actualitza el numero de cops que hem utilitzat la que escollim
    public void actualitzaParaula(String paraula) {
        TrieNode nodeAct;
        nodeAct = root;
        int length, index;
        ArrayListt<LlistaStrings> result = new ArrayListt<>();

        length = paraula.length();

        for (int i = 0; i < length; i++) {
            index = paraula.charAt(i) - 'a';

            nodeAct = nodeAct.getFill(index);

        }
        nodeAct.setFinalParaula(nodeAct.isFinalParaula() + 1);
    }


    public ArrayListt<LlistaStrings> Ordena(ArrayListt<LlistaStrings> llista) {
        LlistaStrings b[] = llista.getArrayLl();
        MyQuickSort sort = new MyQuickSort(b);
        b = sort.getArray();
        ArrayListt retorna = new ArrayListt<LlistaStrings>(b);
        return retorna;
    }

    public int getLimitParaules() {
        return limitParaules;
    }

//Canvio el limit de paraules i en cas que sigui menor, elimino les menys utilitzades
    public void setLimitParaules(int limitParaules) {
        this.limitParaules = limitParaules;
        ArrayListt<String> paraules = new ArrayListt<String>();
        paraules = this.search("");
        if (paraules.getSize() > limitParaules){
            for (int i = paraules.getSize(); i > limitParaules; i--){
                this.remove((String )paraules.getElement(i));
            }
        }
    }
    //Funció que elimina una paraula dels tries
    public void remove(String paraula){

        TrieNode nodeAct = goTo(paraula);
        //El if controla que no haigin entrat una paraula que no existia.
        if(nodeAct != null) {
            //NodeAct es el ultim node de la paraula
            nodeAct.setFinalParaula(0);             //Faig que deixi de ser final de paraula
            boolean fills = false;
            int index;
            for (int l = 1; l < paraula.length(); l++) {
                for (int p = 0; p < LLETRESABC; p++) {
                    if (nodeAct.getFill(p) != null) {
                        fills = true;
                        break;
                    }
                }
                if (fills == false) {
                    //Elimino la ultima lletra i vaig a la seguent

                    index = paraula.charAt(paraula.length() - 1) - 'a';
                    paraula = paraula.substring(0, paraula.length() - 1);
                    goTo(paraula);
                    //Ara que ja estic al node 'pare' poso el fill a null
                    nodeAct.setFill(null, index);

                } else {
                    //Si hi ha algun fill més no vull que faci més voltes
                    l = paraula.length();
                }

            }
            paraules--;
        }else {
            System.out.println("Paraula a eliminar '"+paraula+"' inexistent!");
        }

    }
    //Funció que serveix per anar a un punt en concret del trie
    private TrieNode goTo(String paraula){
        TrieNode nodeAct;
        nodeAct = root;
        int length, index=0;
        ArrayListt<LlistaStrings> result = new ArrayListt<>();

        length = paraula.length();

        for (int i = 0; i < length; i++) {
            index = paraula.charAt(i) - 'a';
            if (nodeAct != null) {
                nodeAct = nodeAct.getFill(index);
            }else {
                return null;
            }

        }
        return nodeAct;

    }

}


class MyQuickSort {

    private LlistaStrings array[];
    private int length;

    public MyQuickSort(LlistaStrings[] arr) {
        array = arr;
    }

    public LlistaStrings[] getArray() {
        return array;
    }

    public void sort(LlistaStrings[] inputArr) {

        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSort(0, length - 1);
    }

    private void quickSort(int lowerIndex, int higherIndex) {

        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2].getPes();
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i].getPes() < pivot) {
                i++;
            }
            while (array[j].getPes() > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }

    private void exchangeNumbers(int i, int j) {
        LlistaStrings temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


