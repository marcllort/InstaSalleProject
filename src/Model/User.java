package Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class User {

    private String username;
    private Timestamp creation;
    private ArrayList<String> to_follow;

    public User() {
        to_follow = new ArrayList<>();
    }

    public User(String username, Timestamp creation) {
        this.username = username;
        this.creation = creation;
        to_follow = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreation() {
        return creation;
    }

    public void setCreation(Timestamp creation) {
        this.creation = creation;
    }

    public ArrayList<String> getTo_follow() {
        return to_follow;
    }

    public void setTo_follow(ArrayList<String> to_follow) {
        this.to_follow = to_follow;
    }

    public void addFollwing(String user) {
        to_follow.add(user);
    }

    @Override
    public String toString() {
        return username;
    }


    private void mostraInformacioUser() {

        System.out.println("Nom d'usuari:");
        System.out.println(username);
        System.out.println("Data creació:");
        System.out.println(creation.toString());
        System.out.println("Usuaris que segueix:");
        System.out.println(Arrays.toString(to_follow.toArray()));
        System.out.println("Número de posts:");
        System.out.println(to_follow.size());               //cal canviar per la funcio getPosts(user) i fer un size()

    }


}
