package Utils;

import Model.Post;
import Model.User;
import Structures.AVLTree;

import java.util.ArrayList;


public class Importador {

    public AVLTree tree = new AVLTree();
    private CSVReader reader = new CSVReader();

    public boolean AVLImporter() {


        ArrayList<User> data = reader.readFile("", true);
        for (User info : data) {
            tree.addElement(info.getUsername().hashCode(), info, info.getUsername());
        }

        ArrayList<Post> data2 = reader.readFile("", false);
        for (Post info : data2) {
            tree.addElement(info.getId(), info, info.getPublished_by());
        }


        tree.inOrder(tree.root);
        return true;
    }


}
