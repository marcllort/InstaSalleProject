package Structures;

public class Trie {
    private TrieNode root;
    private final int LLETRESABC = 26;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String paraula){
        TrieNode nodeAct;
        nodeAct = root;
        int  length, index;

        length = paraula.length();

        for (int i = 0; i< length; i++){
            index = paraula.charAt(i) - 'a';

            if (nodeAct.getFills()[index] == null){
                nodeAct.setFill( new TrieNode(),index);
            }
            nodeAct = nodeAct.getFill(i);

        }
        nodeAct.setFinalParaula(nodeAct.isFinalParaula()+1);
    }

}
