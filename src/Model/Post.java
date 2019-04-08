package Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class Post {

    private int id;
    private ArrayList<User> liked_by;
    private Timestamp published_when;
    private String published_by;
    private Double[] location;
    private ArrayList<String> hashtags;


    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<User> getLiked_by() {
        return liked_by;
    }

    public void setLiked_by(ArrayList<User> liked_by) {
        this.liked_by = liked_by;
    }

    public Timestamp getPublished_when() {
        return published_when;
    }

    public void setPublished_when(Timestamp published_when) {
        this.published_when = published_when;
    }

    public String getPublished_by() {
        return published_by;
    }

    public void setPublished_by(String published_by) {
        this.published_by = published_by;
    }

    public Double[] getLocation() {
        return location;
    }

    public void setLocation(Double[] location) {
        this.location = location;
    }

    public ArrayList<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(ArrayList<String> hashtags) {
        this.hashtags = hashtags;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    private void mostraInformacioPost() {              //PASSAR A TO STRING DE POST

        System.out.println("\n\n id:" + id);
        System.out.println("liked_by:" + Arrays.toString(getLiked_by().toArray()));
        System.out.println("published_by:" + published_by);
        System.out.println("published_when:" + published_when.toString());
        System.out.println("location:" + Arrays.toString(location));
        System.out.println("hashtags:" + Arrays.toString(getHashtags().toArray()));

    }
}
