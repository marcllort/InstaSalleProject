package Utils;

import Model.Post;
import Model.User;
import Structures.AVLTree;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;


public class Importador {

    private Gson gson = new Gson();
    public AVLTree tree = new AVLTree();
    private CSVReader reader = new CSVReader();


    public User[] importDataUser(String route) {
        User[] dataUser;
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(route));
            dataUser = gson.fromJson(reader, User[].class);

            return dataUser;

        } catch (FileNotFoundException e) {
            System.err.println("No s'ha pogut trobar el fitxer.");
            return null;
        }
    }


    public Post[] importDataPost(String route) {
        Post[] dataPost;
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(route));
            dataPost = gson.fromJson(reader, Post[].class);

            return dataPost;

        } catch (FileNotFoundException e) {
            System.err.println("No s'ha pogut trobar el fitxer.");
            return null;
        }
    }

    public void exportUsers() {

    }

    public void exportPosts() {

    }


    public boolean AVLImporter() {

        //User[] data = importDataUser("");

        ArrayList<User> data = new ArrayList<>();

        for (int i =0; i < 10; i++){
            data.add(new User("User"+i,new Timestamp(System.currentTimeMillis())));
        }

        for (User info : data) {
            tree.addElement(info.getUsername().hashCode(), info, info.getUsername());
        }

        /*Post[] data2 = importDataPost("");
        for (Post info : data2) {
            tree.addElement(info.getId(), info, info.getPublished_by());
        }*/

        //tree.inOrder(tree.root,0);
        tree.deleteElement(82025895);
        tree.deleteElement(82025896);
        tree.deleteElement(82025893);
        System.out.println("ELIMINANT");
        tree.deleteElement(82025894);
        tree.inOrder(tree.root,0);
        return true;
    }


}
