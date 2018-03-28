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
    private int following;
    private int followers;

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
    }

}
