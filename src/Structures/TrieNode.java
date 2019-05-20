package Structures;

public class TrieNode {
    private TrieNode[] fills;
    private int finalParaula;
    public static final int LLETRES = 26;

    public TrieNode(){
        finalParaula = 0;
        fills = new TrieNode[LLETRES];
        for (int i = 0; i < LLETRES; i++){
            fills[i] = null;
        }
    }

    public int isFinalParaula() {
        return finalParaula;
    }

    public TrieNode[] getFills() {
        return fills;
    }
    public TrieNode getFill(int i) {
        return fills[i];
    }

    public void setFinalParaula(int finalParaula) {
        this.finalParaula = finalParaula;
    }

    public void setFills(TrieNode[] fills) {
        this.fills = fills;
    }
    public void setFill(TrieNode fill, int i) {
        this.fills[i] = fill;
    }
}
