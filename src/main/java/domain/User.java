package domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String picture;
    private String website;
    private String firstName;
    private String lastName;
    private String bio;
    private String location;
    private String email;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private UserRole userRole;
    @OneToMany
    private List<Tweet> tweets;
    @OneToMany
    private List<User> following;
    @OneToMany
    private List<User> followers;

    public User() {
        
    }
    
    public User(String picture, String website, String firstName, String lastName, String bio, String location, String email, String password, UserRole role, List<Tweet> tweets, List<User> following, List<User> followers) {
        this.picture = picture;
        this.website = website;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.location = location;
        this.email = email;
        this.password = password;
        this.userRole = role;
        this.tweets = tweets;
        this.following = following;
        this.followers = followers;
    }
}
