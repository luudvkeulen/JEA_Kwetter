package domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String picture;
    private String website;
    private String firstName;
    private String lastName;
    private String bio;
    private String location;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private UserRole userRole = UserRole.USER;
    @OneToMany
    private final Set<Tweet> tweets = new HashSet<>();
    @OneToMany
    private final Set<User> following = new HashSet<>();
    @OneToMany
    private final Set<User> followers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public String getWebsite() {
        return website;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Set<Tweet> getTweets() {
        return Collections.unmodifiableSet(tweets);
    }

    public Set<User> getFollowing() {
        return Collections.unmodifiableSet(following);
    }

    public Set<User> getFollowers() {
        return Collections.unmodifiableSet(followers);
    }

    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String picture, String website, String firstName, String lastName, String bio, String location, String email, String password) {
        this(email, password);
        this.picture = picture;
        this.website = website;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.location = location;

    }

    public boolean follow(User user) {
        if (user == null || user == this) {
            return false;
        }

        if(!user.addFollower(this)) {
            return false;
        }
        
        return following.add(user);
    }

    public boolean unfollow(User user) {
        user.removeFollower(this);
        return following.remove(user);
    }
    
    private boolean addFollower(User user) {
        if(user == null || user == this) {
            return false;
        }
        
        followers.add(user);
        return true;
    }
    
    private boolean removeFollower(User user) {
        if(user == null) {
            return false;
        }
        
        return followers.remove(user);
    }

    public UserRole promote() {
        switch (userRole) {
            case USER:
                userRole = UserRole.MODERATOR;
                break;
            case MODERATOR:
                userRole = UserRole.ADMIN;
                break;
        }

        return userRole;
    }

    public UserRole demote() {
        switch (userRole) {
            case MODERATOR:
                userRole = UserRole.USER;
                break;
            case ADMIN:
                userRole = UserRole.MODERATOR;
                break;
        }

        return userRole;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User otherUser = (User) obj;
        if(this.email == null || otherUser.email == null) return false;
        return this.email.equals(otherUser.email);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.picture);
        hash = 59 * hash + Objects.hashCode(this.website);
        hash = 59 * hash + Objects.hashCode(this.firstName);
        hash = 59 * hash + Objects.hashCode(this.lastName);
        hash = 59 * hash + Objects.hashCode(this.bio);
        hash = 59 * hash + Objects.hashCode(this.location);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.password);
        hash = 59 * hash + Objects.hashCode(this.userRole);
        return hash;
    }

}
