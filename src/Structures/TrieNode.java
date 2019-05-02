package Structures;

public class TrieNode {
    private TrieNode[] fills;
    private boolean finalParaula;
    public static final int LLETRES = 24;

    public TrieNode(){
        finalParaula = false;
        fills = new TrieNode[LLETRES];
    }

    public boolean isFinalParaula() {
        return finalParaula;
    }

    public TrieNode[] getFills() {
        return fills;
    }

    public void setFinalParaula(boolean finalParaula) {
        this.finalParaula = finalParaula;
    }

    public void setFills(TrieNode[] fills) {
        this.fills = fills;
    }
}
