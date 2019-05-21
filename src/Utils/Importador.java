package Utils;

import Model.Post;
import Model.User;
import Structures.*;
import Structures.Helpers.LeafNode;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.ArrayList;


public class Importador {

    private Gson gson = new Gson();
    public AVLTree tree = new AVLTree();
    public Graph graph = new Graph();
    public RTree rTree = new RTree();
    public Trie trie = new Trie();
    private CSVReader reader = new CSVReader();
    public ArrayListt<User> arrayUsers = new ArrayListt();
    public ArrayListt<Post> arrayPosts = new ArrayListt<Post>();



    private Post[] dataPost;
    private User[] dataUser;


    public int importDataUser(String route) {

        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(route));
            dataUser = gson.fromJson(reader, User[].class);

            return dataUser.length;

        } catch (FileNotFoundException e) {
            System.err.println("No s'ha pogut trobar el fitxer.");
            return -1;
        }
    }

    public int importDataPost(String route) {

        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(route));
            dataPost = gson.fromJson(reader, Post[].class);

            return dataPost.length;

        } catch (FileNotFoundException e) {
            System.err.println("No s'ha pogut trobar el fitxer.");
            return -1;
        }
    }


    public void exportUsers(String exportRoute) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayUsers.getArray());

        try {
            Files.write(Paths.get(exportRoute + "users.json"), json.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportPosts(String exportRoute) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayPosts.getArray());
        System.out.println(json);
        try {
            Files.write(Paths.get(exportRoute + "posts.json"), json.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void AVLImporter() {

        for (Post info : dataPost) {
            tree.addElement(info.getId(), info, info.getPublished_by());
        }

    }
    public void trieImporter() {
        int p;
        for (User info : dataUser) {
            p = trie.insert(info.getUsername());
            if(p ==0 ){
                break;
            }
        }

    }



    public void HashTableImporter() {
    }

    public void RTreeImporter() {
        LeafNode leafNode;

        for (Post info : dataPost) {
            //System.out.println(" --------------- POST CON ID: " + info.getId() + " -------------------");
            leafNode = new LeafNode(info);

            rTree.insertPost(leafNode);
        }
    }

    public void ArrayListImporter(int opcio) {
        if (opcio == 1) {
            for (User info : dataUser) {
                arrayUsers.addElement(info);
            }
        } else if (opcio == 2) {
            for (Post info : dataPost) {
                arrayPosts.addElement(info);
            }
        }
    }

    public void GraphListImporter() {
    }

    public void TriesImporter() {
    }


    private void AVLTester() {

        ArrayList<Post> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new Post(i, System.currentTimeMillis(), "Post" + i));                                 // Creo usuaris de test del 0 al 9
        }

        for (Post info : data) {
            tree.addElement(info.getId(), info, info.getPublished_by());                                           // Afegim users al arbre
        }

        System.out.println("Inicial");
        tree.inOrder(tree.root, 0);
        System.out.println("Borro node 2");
        tree.deleteElement(82025895);
        tree.inOrder(tree.root, 0);
        System.out.println("Borro node 3");
        tree.deleteElement(82025896);
        tree.inOrder(tree.root, 0);
        System.out.println("Borro node 0");
        tree.deleteElement(82025893);
        tree.inOrder(tree.root, 0);
        System.out.println("Borro node 1");
        tree.deleteElement(82025894);
        tree.inOrder(tree.root, 0);

        //User test = (User) tree.search(82025897);                                                                            // Busco usuari 4, l'ha de trobar
        //System.out.println("Trobat el: " + test.getUsername());
        //User test2 = (User) tree.search(82025895);                                                                           // Busco usuari 2, no l'ha de trobar, està borrat
        //System.out.println("Trobat el: "+ test2.getUsername());

    }

    public Post[] getDataPost() {
        return dataPost;
    }

    public void setDataPost(Post[] dataPost) {
        this.dataPost = dataPost;
    }
}
