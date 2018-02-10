package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String picture;
    private String website;
    private String firstName;
    private String lastName;
    private String bio;
    private String location;
    @Column(unique=true)
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
    
    public User(String picture, String website, String firstName, String lastName, String bio, String location, String email, String password) {
        this.picture = picture;
        this.website = website;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.location = location;
        this.email = email;
        this.password = password;
        this.userRole = UserRole.USER;
        this.tweets = new ArrayList<>();
        this.following = new ArrayList<>();
    }
    
    public boolean follow(User user) {
        if(user == null) return false;
        if(following.contains(user)) return false;
        return following.add(user);
    }
    
    public boolean unfollow(User user) {
        return following.remove(user);
    }
    
    public UserRole promote() {
        switch(userRole) {
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
        switch(userRole) {
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
        if(obj instanceof User) return false;
        User otherUser = (User)obj;
        return this.email.equals(otherUser.email);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    
}
