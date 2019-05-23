package Structures;

import Model.Post;
import Structures.Helpers.LeafNode;
import Structures.Helpers.NodeRTree;

public class RTree {
    private ArrayListt posts;
    private LeafNode.LeafNodeArray leafRoot;
    private NodeRTree.NodeRTreeArray rTreeRoot;


    public RTree(){
        this.posts = new ArrayListt();
        this.leafRoot = new LeafNode.LeafNodeArray();
        this.rTreeRoot = new NodeRTree.NodeRTreeArray();
    }

    public void createTree(ArrayListt posts){
        LeafNode leafNode;

        for (int i = 0; i < posts.getSize(); i++){
            leafNode = new LeafNode((Post) posts.getElement(i));
            System.out.println("\n\n ------------------------ PONEMOS EL POST CON ID -> " + leafNode.getPost().getId() + " -----------------------");
            insertPost(leafNode);
            // System.out.println("javo");
            //showRoot(this.rTreeRoot);
        }
    }


    private void showRoot(NodeRTree.NodeRTreeArray root) {
        int i = 0;
        while (root.getNodos().getElement(i) != null){
            if (root.getNodos().getElement(i) == null){
                System.out.println("    elemento " + i + " del array == null");
            }else {
                if (root.getNodos().getElement(i) instanceof NodeRTree){
                    System.out.println("\nElemento " + i + " es un NODERTREE");
                    System.out.println("    su AREA = " + ((NodeRTree) root.getNodos().getElement(i)).getArea());
                    System.out.println("    y sus HIJOS son --> ");
                    if (((NodeRTree) root.getNodos().getElement(i)).getHijosRTree() != null){
                        showRoot(((NodeRTree) root.getNodos().getElement(i)).getHijosRTree());
                    }else {
                        LeafNode.LeafNodeArray leafNodeArray = ((NodeRTree) root.getNodos().getElement(i)).getHijosLeaf();
                        ArrayListt arrayListt = ((NodeRTree) root.getNodos().getElement(i)).getHijosLeaf().getLeafNodes();

                        for (int j = 0; j < arrayListt.getSize(); j++){
                            System.out.println("            leafNode con ID = " + ((LeafNode)(arrayListt.getElement(j))).getPost().getId());
                        }
                        System.out.println("            PADRE LEAFNODES = aquel con área -> " + ((NodeRTree) root.getNodos().getElement(i)).getArea());
                        System.out.println("        su arrayPadre es aquel cuyas áreas es: ");
                        for (int j = 0; j < leafNodeArray.getFatherArray().getNodos().getSize(); j++){
                            System.out.println("        --> posicion " + j + " con area = " + ((NodeRTree)leafNodeArray.getFatherArray().getNodos().getElement(j)).getArea());
                        }
                    }

                    if (root.getFather() == null){
                        System.out.println("\n  su padre == null");
                    }else {
                        System.out.println("\n      su padre es aquel con área -> " + root.getFather().getArea());
                        System.out.println("        el array de su padre es aquel cuyos hijos son: ");
                        int j = 0;
                        while (root.getFatherArray().getNodos().getElement(j) != null) {
                            NodeRTree nodeRTree = (NodeRTree) (root.getFatherArray().getNodos().getElement(j));
                            System.out.println("            --> hijo NODERTREE de la posicion " + j + " con area " + nodeRTree.getArea());
                            j++;
                        }
                    }
                }
            }
            i++;
        }
    }

    public void insertPost(LeafNode leafNode){
        LeafNode[] pointsForNewR;

        if (leafRoot.getLeafNodes().getSize() < 3){
            leafRoot.setFather(null);
            leafRoot.setFatherArray(null);
            leafRoot.getLeafNodes().addElement(leafNode);

        }else {
            if (rTreeRoot.getNodos().getSize() == 0){
             //   System.out.println("SPLIT POR ABAJO!");
                rTreeRoot.setFather(null);
                rTreeRoot.setFatherArray(null);

                pointsForNewR = getBestPoints(leafRoot, leafNode);
                rTreeRoot = createNewNodeFromLeafs(leafRoot, pointsForNewR);

                for (int i = 0; i < rTreeRoot.getNodos().getSize(); i++){
                    ((NodeRTree)rTreeRoot.getNodos().getElement(i)).setAltura(1);
                }
            }else {
              //  System.out.println("miramos dónde lo podemos poner");
                findBestSplitNode(rTreeRoot, leafNode, null);
            }
        }
    }

    private boolean lookForAnyEmptySpace(LeafNode.LeafNodeArray leafArray) {
        boolean anyEmpty = true;
        NodeRTree.NodeRTreeArray fatherArray;

        if (leafArray.getFather() == null){
            anyEmpty = false;

        }else {
            fatherArray = leafArray.getFatherArray();

            while (anyEmpty){
                if (fatherArray == null) {
                    anyEmpty = false;
                }else{
                    if (fatherArray.getNodos().getSize() < 3 || fatherArray.getNodos().getElement(2) == null){
                        break;
                    }else {
                        fatherArray = fatherArray.getFatherArray();
                    }
                }
            }
        }

        return anyEmpty;
    }


    public void findBestSplitNode(NodeRTree.NodeRTreeArray root, LeafNode leafNode, NodeRTree bestNodeRtree){
        int i = 0;

        while(root.getNodos().getElement(i) != null){
            if (root.getNodos().getElement(i) instanceof NodeRTree){
                if (bestNodeRtree == null){
                    bestNodeRtree = (NodeRTree) root.getNodos().getElement(i);
                }else {
                    bestNodeRtree = compareNodesIncrement((NodeRTree) root.getNodos().getElement(i), bestNodeRtree, leafNode);
                    //System.out.println("bestNode es el de area = " + bestNodeRtree.getArea());
                }
            }
            i++;
        }

        if (bestNodeRtree.getHijosLeaf() == null && bestNodeRtree.getHijosRTree() != null){
            findBestSplitNode(bestNodeRtree.getHijosRTree(), leafNode, null);
        }else {
            if (bestNodeRtree.getHijosLeaf() != null || bestNodeRtree.getHijosRTree() == null){
                if (bestNodeRtree.getHijosLeaf().getLeafNodes().getSize() < 3 || bestNodeRtree.getHijosLeaf().getLeafNodes().getElement(2) == null){
                   // System.out.println("hay espacio para meterlo como hermano del nodo post con id " + ((LeafNode) bestNodeRtree.getHijosLeaf().getLeafNodes().getElement(0)).getPost().getId());
                    bestNodeRtree.getHijosLeaf().getLeafNodes().addElement(leafNode);
                    actualizaRectangulo(bestNodeRtree);
                    actualizaRoot(bestNodeRtree.getHijosLeaf());
                }else {
                    boolean isEmpty = lookForAnyEmptySpace(bestNodeRtree.getHijosLeaf());
                  //  System.out.println("hay espacio donde poner el nodo -> " + isEmpty);
                    splitTree(bestNodeRtree.getHijosLeaf(), leafNode, isEmpty);
                }
            }
        }
    }

    private void actualizaRectangulo(NodeRTree NodeRtree) {
        double area = 0;
        double minX = 10000, maxX = 0, minY = 10000, maxY = 0;

        if (NodeRtree.getHijosLeaf() != null && NodeRtree.getHijosRTree() == null){
            for (int i = 0; i < NodeRtree.getHijosLeaf().getLeafNodes().getSize(); i++) {
                LeafNode hijo = ((LeafNode) NodeRtree.getHijosLeaf().getLeafNodes().getElement(i));
                if (hijo.getPost().getLocation()[0] <= minX) {
                    minX = hijo.getPost().getLocation()[0];
                }
                if (hijo.getPost().getLocation()[0] >= maxX) {
                    maxX = hijo.getPost().getLocation()[0];
                }

                if (hijo.getPost().getLocation()[1] <= minY) {
                    minY = hijo.getPost().getLocation()[1];
                }
                if (hijo.getPost().getLocation()[1] >= maxY) {
                    maxY = hijo.getPost().getLocation()[1];
                }
            }
        }else {

            int i = 0;
            while (NodeRtree.getHijosRTree().getNodos().getElement(i) != null) {
                NodeRTree hijo = ((NodeRTree) NodeRtree.getHijosRTree().getNodos().getElement(i));
                if (hijo.getBestX()[0] < minX) {
                    minX = hijo.getBestX()[0];
                }

                if (hijo.getBestX()[1] > maxX) {
                    maxX = hijo.getBestX()[1];
                }

                if (hijo.getBestY()[0] < minY) {
                    minY = hijo.getBestY()[0];
                }
                if (hijo.getBestY()[1] > maxY) {
                    maxY = hijo.getBestY()[1];
                }
                i++;
            }
        }

        double[] newX = new double[]{minX, maxX};
        double[] newY = new double[]{minY, maxY};

        area = (maxX - minX) * (maxY - minY);

        NodeRtree.setBestX(newX);
        NodeRtree.setBestY(newY);
        NodeRtree.setArea(area);
    }


    private void splitTree(LeafNode.LeafNodeArray splitNode, LeafNode leafNode, boolean anyEmpty){
        NodeRTree father, grandfather;
        LeafNode[] pointsForNewR;
        NodeRTree.NodeRTreeArray fatherArray, grandfatherArray, newRNodesArray;
        NodeRTree topRNode;
        boolean introducido = false;
        ArrayListt newRnodes, topNewRNode;


        fatherArray = splitNode.getFatherArray();
        father = splitNode.getFather();

        grandfatherArray = fatherArray.getFatherArray();
        grandfather = fatherArray.getFather();

        pointsForNewR = getBestPoints(splitNode, leafNode);
        newRnodes = createNewRs(fatherArray, father, pointsForNewR);

        if (!anyEmpty){
           // System.out.println("SPLIT POR ABAJO!");

            //realizamos un balance al árbol
            balanceRoot(splitNode.getFatherArray(), splitNode.getFather(), newRnodes);

        }else{
           // System.out.println("split por arriba!");

            while (!introducido) {

                if (fatherArray.getNodos().getElement(2) == null || fatherArray.getNodos().getSize() < 3) {
                  //  System.out.println("se puede poner como tío del splitNode");

                    newRNodesArray = new NodeRTree.NodeRTreeArray();
                    newRNodesArray.setNodos(newRnodes);
                    newRNodesArray.setFather(grandfather);
                    newRNodesArray.setFatherArray(grandfatherArray);

                    for (int i = 0; i < newRNodesArray.getNodos().getSize(); i++) {
                        if (((NodeRTree) newRNodesArray.getNodos().getElement(i)).getHijosLeaf() != null){
                            ((NodeRTree) newRNodesArray.getNodos().getElement(i)).getHijosLeaf().setFatherArray(newRNodesArray);
                            ((NodeRTree) newRNodesArray.getNodos().getElement(i)).getHijosLeaf().setFather((NodeRTree) newRNodesArray.getNodos().getElement(i));

                        }else {
                            ((NodeRTree) newRNodesArray.getNodos().getElement(i)).getHijosRTree().setFatherArray(newRNodesArray);
                            ((NodeRTree) newRNodesArray.getNodos().getElement(i)).getHijosRTree().setFather((NodeRTree) newRNodesArray.getNodos().getElement(i));

                        }
                    }

                    if (grandfatherArray != null) {
                        grandfather.setHijosRTree(newRNodesArray);
                        grandfather.setHijosLeaf(null);

                        actualizaRoot(((NodeRTree) splitNode.getFatherArray().getFather().getHijosRTree().getNodos().getElement(0)).getHijosLeaf());
                    } else {
                        newRNodesArray.setFather(null);
                        newRNodesArray.setFatherArray(null);
                        this.rTreeRoot = newRNodesArray;
                    }
                    introducido = true;

                } else {
                 //   System.out.println("no se puede poner como tío del splitNode");

                    topNewRNode = getLowerAreaNodes(newRnodes);

                    newRNodesArray = new NodeRTree.NodeRTreeArray();
                    for (int i = 0; i < topNewRNode.getSize() - 2; i++) {
                        newRNodesArray.getNodos().addElement(topNewRNode.getElement(i));
                    }

                    for (int i = 0; i < newRNodesArray.getNodos().getSize(); i++) {
                        if (((NodeRTree)newRNodesArray.getNodos().getElement(i)).getHijosLeaf() == null) {
                            ((NodeRTree)(newRNodesArray.getNodos().getElement(i))).getHijosRTree().setFatherArray(newRNodesArray);
                            ((NodeRTree)(newRNodesArray.getNodos().getElement(i))).getHijosRTree().setFather((NodeRTree) newRNodesArray.getNodos().getElement(i));
                        } else {
                            ((NodeRTree)(newRNodesArray.getNodos().getElement(i))).getHijosLeaf().setFatherArray(newRNodesArray);
                            ((NodeRTree)(newRNodesArray.getNodos().getElement(i))).getHijosLeaf().setFather((NodeRTree) newRNodesArray.getNodos().getElement(i));
                        }
                    }
                    newRNodesArray.setFather(grandfather);
                    newRNodesArray.setFatherArray(grandfatherArray);
                    grandfather.setHijosRTree(newRNodesArray);
                    grandfather.setHijosLeaf(null);

                    actualizaRectangulo(grandfather);

                    newRNodesArray = new NodeRTree.NodeRTreeArray();
                    for (int i = 2; i < topNewRNode.getSize(); i++) {
                        if (((NodeRTree)topNewRNode.getElement(i)).getHijosRTree() != null){
                            for (int j = 0; j < ((NodeRTree)topNewRNode.getElement(i)).getHijosRTree().getNodos().getSize(); j++){
                                ((NodeRTree)topNewRNode.getElement(i)).getHijosRTree().setFatherArray(newRNodesArray);
                                ((NodeRTree)topNewRNode.getElement(i)).getHijosRTree().setFather((NodeRTree) topNewRNode.getElement(i));
                            }

                        }else {
                            for (int j = 0; j < ((NodeRTree)topNewRNode.getElement(i)).getHijosLeaf().getLeafNodes().getSize(); j++){
                                ((NodeRTree)topNewRNode.getElement(i)).getHijosLeaf().setFatherArray(newRNodesArray);
                                ((NodeRTree)topNewRNode.getElement(i)).getHijosLeaf().setFather((NodeRTree) topNewRNode.getElement(i));

                            }
                        }

                        newRNodesArray.getNodos().addElement(topNewRNode.getElement(i));
                    }
                    topRNode = new NodeRTree();
                    topRNode.setHijosLeaf(null);
                    topRNode.setHijosRTree(newRNodesArray);
                    actualizaRectangulo(topRNode);
                    newRNodesArray.setFather(topRNode);

                    newRnodes = new ArrayListt();
                    for (int i = 0; i < grandfatherArray.getNodos().getSize(); i++) {
                        newRnodes.addElement(grandfatherArray.getNodos().getElement(i));
                    }

                    newRnodes.addElement(topRNode);

                    father = fatherArray.getFather();
                    fatherArray = fatherArray.getFatherArray();

                    grandfather = fatherArray.getFather();
                    grandfatherArray = fatherArray.getFatherArray();
                }
            }
            actualizaRoot(splitNode.getFather().getHijosLeaf());
        }
    }

    private ArrayListt getLowerAreaNodes(ArrayListt newRnodes) {
        NodeRTree nodeRTree, bestNodeRTree = new NodeRTree();
        ArrayListt returnArray;
        Object bestStartNode = new Object();
        double bestArea = Double.POSITIVE_INFINITY;
        double bestX = Double.POSITIVE_INFINITY;
        boolean found;

        for (int i = 0; i < newRnodes.getSize(); i++){
            if (((NodeRTree)newRnodes.getElement(i)).getBestX()[0] <= bestX){
                bestX = ((NodeRTree)newRnodes.getElement(i)).getBestX()[0];
                bestStartNode = newRnodes.getElement(i);
            }
        }

        for (int i = 0; i < newRnodes.getSize(); i++){
            returnArray = new ArrayListt();
            returnArray.addElement(bestStartNode);
            if (!newRnodes.getElement(i).equals(bestStartNode)) {
                returnArray.addElement(newRnodes.getElement(i));
                nodeRTree = makeNodeRTree(returnArray);

                //&& !noHaySolapamiento(nodeRTree, newRnodes)
                if (nodeRTree.getArea() <= bestArea ){
                    bestArea = nodeRTree.getArea();
                    bestNodeRTree = nodeRTree;
                }
            }
        }

        returnArray = new ArrayListt();
        for (int i = 0; i < newRnodes.getSize(); i++){
            found = false;
            for (int j = 0; j < bestNodeRTree.getHijosRTree().getNodos().getSize(); j++){
                if (newRnodes.getElement(i).equals(bestNodeRTree.getHijosRTree().getNodos().getElement(j))){
                    found = true;
                }
            }

            if (!found){
                returnArray.addElement(newRnodes.getElement(i));
            }
        }

        nodeRTree = makeNodeRTree(returnArray);

        returnArray = new ArrayListt();
        if (bestNodeRTree.getArea() <= nodeRTree.getArea()){
            returnArray.addElement(bestNodeRTree.getHijosRTree().getNodos().getElement(0));
            returnArray.addElement(bestNodeRTree.getHijosRTree().getNodos().getElement(1));
            returnArray.addElement(nodeRTree.getHijosRTree().getNodos().getElement(0));
            returnArray.addElement(nodeRTree.getHijosRTree().getNodos().getElement(1));

        }else {
            returnArray.addElement(nodeRTree.getHijosRTree().getNodos().getElement(0));
            returnArray.addElement(nodeRTree.getHijosRTree().getNodos().getElement(1));
            returnArray.addElement(bestNodeRTree.getHijosRTree().getNodos().getElement(0));
            returnArray.addElement(bestNodeRTree.getHijosRTree().getNodos().getElement(1));
        }

        return returnArray;
    }

    private boolean noHaySolapamiento(NodeRTree nodeRTree, ArrayListt newRnodes) {
        boolean solapamineto = false;
        NodeRTree node2compare1, node2compare2;

        node2compare1 = (NodeRTree) nodeRTree.getHijosRTree().getNodos().getElement(0);
        node2compare2 = (NodeRTree) nodeRTree.getHijosRTree().getNodos().getElement(1);

        for (int i = 0; i < newRnodes.getSize(); i++){
            if (!(newRnodes.getElement(i).equals(node2compare1)) && !(newRnodes.getElement(i).equals(node2compare2))){
                if ((((NodeRTree)newRnodes.getElement(i)).getBestX()[0] > nodeRTree.getBestX()[0] && ((NodeRTree)newRnodes.getElement(i)).getBestX()[0] < nodeRTree.getBestX()[1])
                        || (((NodeRTree)newRnodes.getElement(i)).getBestX()[1] > nodeRTree.getBestX()[0] && ((NodeRTree)newRnodes.getElement(i)).getBestX()[1] < nodeRTree.getBestX()[1])){

                    if (((NodeRTree)newRnodes.getElement(i)).getBestY()[0] > nodeRTree.getBestY()[0] && ((NodeRTree)newRnodes.getElement(i)).getBestY()[0] < nodeRTree.getBestY()[1]) {
                        solapamineto = true;
                        break;
                    }else {
                        if (((NodeRTree)newRnodes.getElement(i)).getBestY()[1] > nodeRTree.getBestY()[0] && ((NodeRTree)newRnodes.getElement(i)).getBestY()[1] < nodeRTree.getBestY()[1]) {
                            solapamineto = true;
                            break;
                        }
                    }
                }
            }
        }


        return solapamineto;
    }

    private NodeRTree makeNodeRTree(ArrayListt returnArray) {
        NodeRTree nodeRTree = new NodeRTree();
        NodeRTree.NodeRTreeArray nodeRTreeArray = new NodeRTree.NodeRTreeArray();

        nodeRTreeArray.setNodos(returnArray);
        nodeRTree.setHijosLeaf(null);
        nodeRTree.setHijosRTree(nodeRTreeArray);

        actualizaRectangulo(nodeRTree);

        return nodeRTree;
    }

    private void balanceRoot(NodeRTree.NodeRTreeArray fatherArray, NodeRTree father, ArrayListt newRnodes) {
        NodeRTree.NodeRTreeArray nodeRTreeArray, newRNodesArray;
        NodeRTree.NodeRTreeArray newRTree2put, finalNodeArray = new NodeRTree.NodeRTreeArray();
        NodeRTree nodeRTree;
        ArrayListt topNewRNode;

        topNewRNode = getLowerAreaNodes(newRnodes);

        finalNodeArray.setFatherArray(fatherArray.getFatherArray());
        finalNodeArray.setFather(fatherArray.getFather());

        newRNodesArray = new NodeRTree.NodeRTreeArray();
        for (int i = 0; i < topNewRNode.getSize() - 2; i++) {
            newRNodesArray.getNodos().addElement(topNewRNode.getElement(i));
        }

        nodeRTree = new NodeRTree();

        for (int i = 0; i < newRNodesArray.getNodos().getSize(); i++) {
            if (((NodeRTree)newRNodesArray.getNodos().getElement(i)).getHijosLeaf() == null) {
                ((NodeRTree)(newRNodesArray.getNodos().getElement(i))).getHijosRTree().setFatherArray(newRNodesArray);
                ((NodeRTree)(newRNodesArray.getNodos().getElement(i))).getHijosRTree().setFather((NodeRTree) newRNodesArray.getNodos().getElement(i));
            } else {
                ((NodeRTree)(newRNodesArray.getNodos().getElement(i))).getHijosLeaf().setFatherArray(newRNodesArray);
                ((NodeRTree)(newRNodesArray.getNodos().getElement(i))).getHijosLeaf().setFather((NodeRTree) newRNodesArray.getNodos().getElement(i));
            }
        }
        newRNodesArray.setFather(nodeRTree);
        newRNodesArray.setFatherArray(finalNodeArray);
        nodeRTree.setHijosLeaf(null);
        nodeRTree.setHijosRTree(newRNodesArray);

        actualizaRectangulo(nodeRTree);

        finalNodeArray.getNodos().addElement(nodeRTree);

        newRNodesArray = new NodeRTree.NodeRTreeArray();
        for (int i = 2; i < topNewRNode.getSize(); i++) {
            newRNodesArray.getNodos().addElement(topNewRNode.getElement(i));
        }

        nodeRTree = new NodeRTree();

        for (int i = 0; i < newRNodesArray.getNodos().getSize(); i++) {
            if (((NodeRTree)newRNodesArray.getNodos().getElement(i)).getHijosLeaf() == null) {
                ((NodeRTree)(newRNodesArray.getNodos().getElement(i))).getHijosRTree().setFatherArray(newRNodesArray);
                ((NodeRTree)(newRNodesArray.getNodos().getElement(i))).getHijosRTree().setFather((NodeRTree) newRNodesArray.getNodos().getElement(i));

            } else {
                ((NodeRTree)(newRNodesArray.getNodos().getElement(i))).getHijosLeaf().setFatherArray(newRNodesArray);
                ((NodeRTree)(newRNodesArray.getNodos().getElement(i))).getHijosLeaf().setFather((NodeRTree) newRNodesArray.getNodos().getElement(i));
            }
        }
        newRNodesArray.setFather(nodeRTree);
        newRNodesArray.setFatherArray(finalNodeArray);
        nodeRTree.setHijosLeaf(null);
        nodeRTree.setHijosRTree(newRNodesArray);

        actualizaRectangulo(nodeRTree);

        finalNodeArray.getNodos().addElement(nodeRTree);

        if (fatherArray.getFather() != null){
            fatherArray.getFather().setHijosRTree(finalNodeArray);
            actualizaRectangulo(finalNodeArray.getFather());
        }else {
            this.rTreeRoot = finalNodeArray;
        }

        father = fatherArray.getFather();
        fatherArray = fatherArray.getFatherArray();

        while (fatherArray != null){
            nodeRTreeArray = new NodeRTree.NodeRTreeArray();
            nodeRTree = new NodeRTree();
            newRTree2put = new NodeRTree.NodeRTreeArray();

            newRTree2put.setFatherArray(fatherArray.getFatherArray());
            newRTree2put.setFather(fatherArray.getFather());

            //le añadimos el nodo resultante de realizar un split por abajo
            father.getHijosRTree().setFatherArray(newRTree2put);
            newRTree2put.getNodos().addElement(father);

            for (int i = 0; i < fatherArray.getNodos().getSize(); i++){
                if (!fatherArray.getNodos().getElement(i).equals(father)){
                    if (((NodeRTree)fatherArray.getNodos().getElement(i)).getHijosLeaf() != null){
                        ((NodeRTree)fatherArray.getNodos().getElement(i)).getHijosLeaf().setFatherArray(nodeRTreeArray);
                    }else{
                        ((NodeRTree)fatherArray.getNodos().getElement(i)).getHijosRTree().setFatherArray(nodeRTreeArray);
                    }
                    nodeRTreeArray.getNodos().addElement(fatherArray.getNodos().getElement(i));
                }
            }
            nodeRTreeArray.setFatherArray(newRTree2put);
            nodeRTreeArray.setFather(nodeRTree);

            nodeRTree.setHijosRTree(nodeRTreeArray);
            nodeRTree.setHijosLeaf(null);
            actualizaRectangulo(nodeRTree);

            newRTree2put.getNodos().addElement(nodeRTree);

            if (fatherArray.getFather() != null){
                fatherArray.getFather().setHijosRTree(newRTree2put);
                actualizaRectangulo(fatherArray.getFather());
            }else {
                this.rTreeRoot = newRTree2put;
            }
            father = fatherArray.getFather();
            fatherArray = fatherArray.getFatherArray();
        }
    }

    private void actualizaRoot(LeafNode.LeafNodeArray splitNode) {
        NodeRTree.NodeRTreeArray fatherArray = splitNode.getFatherArray();
        NodeRTree father = splitNode.getFather();

        while (fatherArray != null){
            actualizaRectangulo(father);
            father = fatherArray.getFather();
            fatherArray = fatherArray.getFatherArray();
        }
    }

    private ArrayListt createNewRs(NodeRTree.NodeRTreeArray fatherArray, NodeRTree father, LeafNode[] pointsForNewR) {
        ArrayListt newRnodes = new ArrayListt();
        LeafNode.LeafNodeArray newLeafNode = new LeafNode.LeafNodeArray();
        NodeRTree nodeRTree = new NodeRTree();

        newLeafNode.getLeafNodes().addElement(pointsForNewR[0]);
        newLeafNode.getLeafNodes().addElement(pointsForNewR[1]);
        newLeafNode.setFather(nodeRTree);

        nodeRTree = rellenaInfoNodo(newLeafNode.getLeafNodes());
        nodeRTree.setHijosRTree(null);
        nodeRTree.setHijosLeaf(newLeafNode);

        newRnodes.addElement(nodeRTree);


        newLeafNode = new LeafNode.LeafNodeArray();
        nodeRTree = new NodeRTree();

        newLeafNode.getLeafNodes().addElement(pointsForNewR[2]);
        newLeafNode.getLeafNodes().addElement(pointsForNewR[3]);

        nodeRTree = rellenaInfoNodo(newLeafNode.getLeafNodes());
        nodeRTree.setHijosRTree(null);
        nodeRTree.setHijosLeaf(newLeafNode);

        newRnodes.addElement(nodeRTree);

        for (int j = 0; j < fatherArray.getNodos().getSize(); j++){
            if (!fatherArray.getNodos().getElement(j).equals(father)){
                newRnodes.addElement(fatherArray.getNodos().getElement(j));
            }
        }

        return newRnodes;
    }


    private NodeRTree.NodeRTreeArray createNewNodeFromLeafs(LeafNode.LeafNodeArray splitNode, LeafNode[] pointsForNewR){
        NodeRTree.NodeRTreeArray newNodeArray = new NodeRTree.NodeRTreeArray();
        LeafNode.LeafNodeArray newLeafArray = new LeafNode.LeafNodeArray();
        NodeRTree.NodeRTreeArray nodeRTreeArray = new NodeRTree.NodeRTreeArray();
        NodeRTree newNode;

        newLeafArray.getLeafNodes().addElement(pointsForNewR[0]);
        newLeafArray.getLeafNodes().addElement(pointsForNewR[1]);

        newNode = rellenaInfoNodo(newLeafArray.getLeafNodes());
        newNode.setHijosLeaf(newLeafArray);
        newNode.setHijosRTree(null);

        nodeRTreeArray.getNodos().addElement(newNode);


        newLeafArray = new LeafNode.LeafNodeArray();
        newLeafArray.getLeafNodes().addElement(pointsForNewR[2]);
        newLeafArray.getLeafNodes().addElement(pointsForNewR[3]);

        newNode = new NodeRTree();
        newNode = rellenaInfoNodo(newLeafArray.getLeafNodes());
        newNode.setHijosLeaf(newLeafArray);
        newNode.setHijosRTree(null);

        nodeRTreeArray.getNodos().addElement(newNode);

        nodeRTreeArray.setFather(splitNode.getFather());
        nodeRTreeArray.setFatherArray(splitNode.getFatherArray());

        for (int i = 0; i < nodeRTreeArray.getNodos().getSize(); i++){
            ((NodeRTree)nodeRTreeArray.getNodos().getElement(i)).getHijosLeaf().setFatherArray(nodeRTreeArray);
            ((NodeRTree)nodeRTreeArray.getNodos().getElement(i)).getHijosLeaf().setFather((NodeRTree) nodeRTreeArray.getNodos().getElement(i));
        }

        return nodeRTreeArray;
    }


    private NodeRTree rellenaInfoNodo(ArrayListt leafArray){
        NodeRTree nodeRTree = new NodeRTree();
        double area;
        double[] bestX = new double[2];
        double[] bestY = new double[2];
        Post post1, post2;

        post1 = ((LeafNode)leafArray.getElement(0)).getPost();
        post2 = ((LeafNode)leafArray.getElement(1)).getPost();

        if (post1.getLocation()[0] <= post2.getLocation()[0]){
            bestX[0] = post1.getLocation()[0];
            bestX[1] = post2.getLocation()[0];
        }else{
            bestX[0] = post2.getLocation()[0];
            bestX[1] = post1.getLocation()[0];
        }

        if (post1.getLocation()[1] <= post2.getLocation()[1]){
            bestY[0] = post1.getLocation()[1];
            bestY[1] = post2.getLocation()[1];
        }else{
            bestY[0] = post2.getLocation()[1];
            bestY[1] = post1.getLocation()[1];
        }

        area = (bestX[1] - bestX[0]) * (bestY[1] - bestY[0]);

        nodeRTree.setBestX(bestX);
        nodeRTree.setBestY(bestY);
        nodeRTree.setArea(area);

        return nodeRTree;
    }


    private LeafNode[] getBestPoints(LeafNode.LeafNodeArray splitArray, LeafNode leafNode){

        LeafNode[] points = new LeafNode[4];
        double solution, bestSolution = Double.POSITIVE_INFINITY;
        Post postNewNode = leafNode.getPost();

        points[0] = leafNode;

        for (int i = 0; i < splitArray.getLeafNodes().getSize(); i++){
            Post postLookingNode = ((LeafNode) splitArray.getLeafNodes().getElement(i)).getPost();
            solution = Math.pow((postNewNode.getLocation()[0] - postLookingNode.getLocation()[0]),2);
            solution += Math.pow((postNewNode.getLocation()[1] - postLookingNode.getLocation()[1]),2);

            if (solution < bestSolution){
                if (points[2] == null){
                    points[2] = points[1];
                }else {
                    points[3] = points[1];
                }
                bestSolution = solution;
                points[1] = (LeafNode) splitArray.getLeafNodes().getElement(i);
            }else{
                if (points[2] == null){
                    points[2] = (LeafNode) splitArray.getLeafNodes().getElement(i);
                }else {
                    points[3] = (LeafNode) splitArray.getLeafNodes().getElement(i);
                }
            }
        }

       /* for (int i = 0; i < points.length; i++){
            System.out.println("puntos: " + points[i].getPost().getId());
        }*/

        return points;
    }


    private NodeRTree compareNodesIncrement(NodeRTree actualNode, NodeRTree bestNode, LeafNode leafNode){
        double[] increment1 = new double[2], increment2 = new double[2];
        double areaActualNode, areaBestNode;
        int lessAreaIncrement;

        increment1[0] = minimumBest(actualNode.getBestX(), leafNode.getPost().getLocation()[0]);
        increment1[1] = minimumBest(actualNode.getBestY(), leafNode.getPost().getLocation()[1]);
        areaActualNode = (actualNode.getBestX()[1] - actualNode.getBestX()[0] + increment1[0]) * (actualNode.getBestY()[1] - actualNode.getBestY()[0] + increment1[1]);

        increment2[0] = minimumBest(bestNode.getBestX(), leafNode.getPost().getLocation()[0]);
        increment2[1] = minimumBest(bestNode.getBestY(), leafNode.getPost().getLocation()[1]);
        areaBestNode = (bestNode.getBestX()[1] - bestNode.getBestX()[0] + increment2[0]) * (bestNode.getBestY()[1] - bestNode.getBestY()[0] + increment2[1]);


        lessAreaIncrement = calculoDiferenciaArea(areaActualNode, actualNode.getArea(), areaBestNode, bestNode.getArea());

        //si el increment1 < increment2
        if (lessAreaIncrement == 1){
            bestNode = actualNode;

            //si increment1 == increment2
        }else if (lessAreaIncrement == 0){
            if (actualNode.getArea() < bestNode.getArea()){
                bestNode = actualNode;
            }
        }

        return bestNode;
    }


    private double minimumBest(double[] bestX, double xCoordenate){
        double minimum1 = 0, minimum2 = 0;

        if (bestX[0] <= xCoordenate && xCoordenate <= bestX[1]){
            return 0;
        }else{
            minimum1 = Math.abs(bestX[0] - xCoordenate);
            minimum2 = Math.abs(bestX[1] - xCoordenate);
        }


        if (minimum1 <= minimum2){
            return  minimum1;
        }else {
            return minimum2;
        }
    }


    private int calculoDiferenciaArea(double NewAreaActualNode, double actualNodeArea, double areaBestNode, double bestNodeArea){

        if ((NewAreaActualNode - actualNodeArea) < (areaBestNode - bestNodeArea)){
            return 1;
        }else{
            if ((NewAreaActualNode - actualNodeArea) == (areaBestNode - bestNodeArea)){
                return 0;
            }else return 2;
        }
    }

    public ArrayListt busquedaPorLocalizacion(double[] localizacion, int radio, NodeRTree.NodeRTreeArray rTreeRoot, ArrayListt returnPosts){
        //NodeRTree.NodeRTreeArray rTreeRoot = getrTreeRoot();

        double[] bestX = new double[2];
        bestX[0] = localizacion[0] - radio;
        bestX[1] = localizacion[0] + radio;

        double[] bestY = new double[2];
        bestY[0] = localizacion[1] - radio;
        bestY[1] = localizacion[1] + radio;


        for (int i = 0; i < rTreeRoot.getNodos().getSize(); i++){
            NodeRTree lookingNode = (NodeRTree) rTreeRoot.getNodos().getElement(i);

            if ((bestX[0] >= lookingNode.getBestX()[0] && bestX[0] <= lookingNode.getBestX()[1])
                            || (bestX[1] >= lookingNode.getBestX()[0] && bestX[1] <= lookingNode.getBestX()[1])){

                if ((bestY[0] >= lookingNode.getBestY()[0] && bestY[0] <= lookingNode.getBestY()[1])
                        || (bestY[1] >= lookingNode.getBestY()[0] && bestY[1] <= lookingNode.getBestY()[1])) {

                    if (lookingNode.getHijosLeaf() == null && lookingNode.getHijosRTree() != null) {
                        busquedaPorLocalizacion(localizacion, radio, lookingNode.getHijosRTree(), returnPosts);

                    } else {
                        for (int j = 0; j < lookingNode.getHijosLeaf().getLeafNodes().getSize(); j++){
                            if (((LeafNode)lookingNode.getHijosLeaf().getLeafNodes().getElement(j)).isActivated()){
                                returnPosts.addElement(((LeafNode)lookingNode.getHijosLeaf().getLeafNodes().getElement(j)).getPost());
                            }
                        }
                    }
                }
            }
        }
        return returnPosts;
    }

    public void eliminaPost (Post post, NodeRTree.NodeRTreeArray rTreeRoot){
        if(post != null) {
            double[] localizacion = post.getLocation();

            for (int i = 0; i < rTreeRoot.getNodos().getSize(); i++) {
                NodeRTree lookingNode = (NodeRTree) rTreeRoot.getNodos().getElement(i);

                if ((localizacion[0] >= lookingNode.getBestX()[0] && localizacion[0] <= lookingNode.getBestX()[1])
                        && (localizacion[1] >= lookingNode.getBestY()[0] && localizacion[1] <= lookingNode.getBestY()[1])) {

                    if (lookingNode.getHijosLeaf() == null && lookingNode.getHijosRTree() != null) {
                        eliminaPost(post, lookingNode.getHijosRTree());

                    } else {
                        for (int j = 0; j < lookingNode.getHijosLeaf().getLeafNodes().getSize(); j++) {
                            if (((LeafNode) lookingNode.getHijosLeaf().getLeafNodes().getElement(j)).getPost().equals(post)) {
                                ((LeafNode) lookingNode.getHijosLeaf().getLeafNodes().getElement(j)).setActivated(false);
                            }
                        }
                    }
                }
            }
        }
    }

    public void visualizaEstructura(NodeRTree.NodeRTreeArray root){
        for (int i = 0; i < root.getNodos().getSize();i++){
            if (root.getNodos().getElement(i) instanceof NodeRTree){
                if (((NodeRTree) root.getNodos().getElement(i)).getHijosLeaf() == null){
                    visualizaEstructura(((NodeRTree) root.getNodos().getElement(i)).getHijosRTree());

                }else {
                    System.out.println();
                    System.out.println("Posts en el mismo rectángulo");
                    for (int j = 0; j < ((NodeRTree) root.getNodos().getElement(i)).getHijosLeaf().getLeafNodes().getSize(); j++){
                        LeafNode leafNode = (LeafNode) ((NodeRTree) root.getNodos().getElement(i)).getHijosLeaf().getLeafNodes().getElement(j);
                        System.out.println("    ---> " + j + ". Post con ID" + leafNode.getPost().getId());
                    }
                }
            }
        }
    }

    public ArrayListt getPosts() { return posts; }
    public void setPosts(ArrayListt posts) { this.posts = posts; }

    public LeafNode.LeafNodeArray getLeafRoot() {
        return leafRoot;
    }

    public void setLeafRoot(LeafNode.LeafNodeArray leafRoot) {
        this.leafRoot = leafRoot;
    }

    public NodeRTree.NodeRTreeArray getrTreeRoot() {
        return rTreeRoot;
    }

    public void setrTreeRoot(NodeRTree.NodeRTreeArray rTreeRoot) {
        this.rTreeRoot = rTreeRoot;
    }
}
