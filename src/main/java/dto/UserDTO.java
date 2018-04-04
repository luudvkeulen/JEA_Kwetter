package dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String picture;
    private String website;
    private String bio;
    private String location;
    private String email;
    private int tweets;
    private String tweets_url;
    private int following;
    private String following_url;
    private int followers;
    private String followers_url;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPicture() {
        return picture;
    }

    public String getWebsite() {
        return website;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public int getTweets() {
        return tweets;
    }

    public int getFollowing() {
        return following;
    }

    public int getFollowers() {
        return followers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTweets(int tweets) {
        this.tweets = tweets;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getTweets_url() {
        return tweets_url;
    }

    public void setTweets_url(String tweets_url) {
        this.tweets_url = tweets_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public UserDTO() {

    }

    public UserDTO(Long id, String username, String firstName, String lastName, String picture, String website, String bio, String location, String email, int tweets, int following, int followers) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.website = website;
        this.bio = bio;
        this.location = location;
        this.email = email;
        this.tweets = tweets;
        this.following = following;
        this.followers = followers;
        this.tweets_url = "http://localhost:8080/Kwetter/api/users/" + username + "/tweets";
        this.following_url = "http://localhost:8080/Kwetter/api/users/" + username + "/following";
        this.followers_url = "http://localhost:8080/Kwetter/api/users/" + username + "/followers";
    }

}
