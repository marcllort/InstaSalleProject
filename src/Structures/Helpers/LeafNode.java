package Structures.Helpers;

import Model.Post;
import Structures.ArrayListt;

public class LeafNode {
    private Post post;
    private boolean activated;


    public LeafNode(Post post) {
        this.post = post;
        activated = true;
    }


    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
    public boolean isActivated() { return activated; }
    public void setActivated(boolean activated) { this.activated = activated; }


    public static class LeafNodeArray {
        private ArrayListt leafNodes;
        private Structures.Helpers.NodeRTree father;
        private Structures.Helpers.NodeRTree.NodeRTreeArray fatherArray;

        public LeafNodeArray() {
            this.leafNodes = new ArrayListt();
            this.father = new Structures.Helpers.NodeRTree();
            this.fatherArray = new Structures.Helpers.NodeRTree.NodeRTreeArray();
        }

        public Structures.Helpers.NodeRTree getFather() { return father; }
        public void setFather(Structures.Helpers.NodeRTree father) { this.father = father; }
        public Structures.Helpers.NodeRTree.NodeRTreeArray getFatherArray() { return fatherArray; }
        public void setFatherArray(Structures.Helpers.NodeRTree.NodeRTreeArray fatherArray) { this.fatherArray = fatherArray; }
        public ArrayListt getLeafNodes() { return leafNodes; }
        public void setLeafNodes(ArrayListt leafNodes) { this.leafNodes = leafNodes; }
    }
}
