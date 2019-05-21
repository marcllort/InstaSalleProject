package Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class Post implements Comparable<Integer>{

    private int id;
    private ArrayList<String> liked_by;
    private long published_when;
    private String published_by;
    private double[] location;
    private ArrayList<String> hashtags;


    public Post() {
        liked_by = new ArrayList<>();
        hashtags = new ArrayList<>();
    }

    public Post(int id, long published_when, String published_by) {
        this.id = id;
        this.published_when = published_when;
        this.published_by = published_by;
    }

    public Post(int id, ArrayList<String> liked_by, long published_when, String published_by, double[] location, ArrayList<String> hashtags) {
        this.id = id;
        this.liked_by = liked_by;
        this.published_when = published_when;
        this.published_by = published_by;
        this.location = location;
        this.hashtags = hashtags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getLiked_by() {
        return liked_by;
    }

    public void setLiked_by(ArrayList<String> liked_by) {
        this.liked_by = liked_by;
    }

    public long getPublished_when() {
        return published_when;
    }

    public void setPublished_when(long published_when) {
        this.published_when = published_when;
    }

    public String getPublished_by() {
        return published_by;
    }

    public void setPublished_by(String published_by) {
        this.published_by = published_by;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public ArrayList<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(ArrayList<String> hashtags) {
        this.hashtags = hashtags;
    }

    public void addHashtag(String hashtag) {
        hashtags.add(hashtag);
    }

    public void addLike(String user) {
        liked_by.add(user);
    }


    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public int compareTo(Integer idd){
        return idd == id ? 1 : 0 ;
    }

    public void mostraInformacioPost() {              //PASSAR A TO STRING DE POST

        System.out.println("\n\n id:" + id);
        System.out.println("liked_by:" + Arrays.toString(getLiked_by().toArray()));
        System.out.println("published_by:" + published_by);
        System.out.println("published_when:" + published_when);
        System.out.println("location:" + Arrays.toString(location));
        System.out.println("hashtags:" + Arrays.toString(getHashtags().toArray()));

    }

}
