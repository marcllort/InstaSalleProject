package Structures.Helpers;

import Structures.ArrayListt;
import Structures.Helpers.LeafNode;

public class NodeRTree {
    private double area;
    private double[] bestX;
    private double[] bestY;
    private NodeRTreeArray hijosRTree;
    private LeafNode.LeafNodeArray hijosLeaf;
    private int altura;


    public NodeRTree(){
        this.area = 0;
        this.bestX = new double[2];
        this.bestY = new double[2];
        this.hijosRTree = null;
        this.hijosLeaf = null;
        this.altura = 0;
    }


    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }
    public double[] getBestX() { return bestX; }
    public void setBestX(double[] bestX) { this.bestX = bestX; }
    public double[] getBestY() { return bestY; }
    public void setBestY(double[] bestY) { this.bestY = bestY; }
    public NodeRTreeArray getHijosRTree() {
        return hijosRTree;
    }
    public void setHijosRTree(NodeRTreeArray hijosRTree) {
        this.hijosRTree = hijosRTree;
    }
    public LeafNode.LeafNodeArray getHijosLeaf() {
        return hijosLeaf;
    }
    public void setHijosLeaf(LeafNode.LeafNodeArray hijosLeaf) {
        this.hijosLeaf = hijosLeaf;
    }
    public int getAltura() { return altura; }
    public void setAltura(int altura) { this.altura = altura; }


    //----------------------------------------------------------------------------------------//


    public static class NodeRTreeArray{
        private ArrayListt nodos;
        private NodeRTree father;
        private NodeRTreeArray fatherArray;


        public NodeRTreeArray(){
            this.nodos = new ArrayListt();
            this.father = new NodeRTree();
            this.fatherArray = null;
        }

        public NodeRTree getFather() { return father; }
        public void setFather(NodeRTree father) { this.father = father; }
        public ArrayListt getNodos() { return nodos; }
        public void setNodos(ArrayListt rTreeNodes) { this.nodos = rTreeNodes; }

        public NodeRTreeArray getFatherArray() {
            return fatherArray;
        }

        public void setFatherArray(NodeRTreeArray fatherArray) {
            this.fatherArray = fatherArray;
        }
    }
}
