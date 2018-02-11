package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private UserRole userRole;
    @OneToMany
    private List<Tweet> tweets;
    @OneToMany
    private List<User> following;
    @OneToMany
    private List<User> followers;

    public User() {
        this.userRole = UserRole.USER;
        this.tweets = new ArrayList<>();
        this.following = new ArrayList<>();
    }
    
    public User(String email, String password) {
        this();
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
        if (user == null) {
            return false;
        }
        if (following.contains(user)) {
            return false;
        }
        return following.add(user);
    }

    public boolean unfollow(User user) {
        return following.remove(user);
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

    public boolean addFollower(User user) {
        if (user == null) {
            return false;
        }
        if (this.followers.contains(user)) {
            return false;
        }
        if (!user.addFollower(this)) {
            return false;
        }
        return this.followers.add(user);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if (!(obj instanceof User)) {
            return false;
        }
        User otherUser = (User) obj;
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
