package domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.enterprise.inject.Model;
import javax.json.bind.annotation.JsonbTransient;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.apache.commons.codec.digest.DigestUtils;

@Entity
@Model
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    ,
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email LIKE :email")
    ,
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username LIKE :username")
})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String username;
    private String picture;
    private String website;
    private String firstName;
    private String lastName;
    private String bio;
    private String location;
    @Column(unique = true)
    private String email;
    private String password;
    @ManyToMany(mappedBy = "users", cascade = ALL)
    private Set<UserGroup> groups = new HashSet<>();
    @OneToMany(mappedBy = "tweetedBy", cascade = ALL)
    private final Set<Tweet> tweets = new HashSet<>();
    @ManyToMany
    private final Set<User> following = new HashSet<>();
    @ManyToMany(mappedBy = "following")
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

    @JsonbTransient
    public Set<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<UserGroup> groups) {
        this.groups = groups;
    }

    @JsonbTransient
    public Set<Tweet> getTweets() {
        return Collections.unmodifiableSet(tweets);
    }

    @JsonbTransient
    public Set<User> getFollowing() {
        return Collections.unmodifiableSet(following);
    }

    @JsonbTransient
    public Set<User> getFollowers() {
        return Collections.unmodifiableSet(followers);
    }

    public String getUsername() {
        return this.username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.sha512Hex(password);
    }
    
    public String getStringGroups() {
        String[] array = new String[groups.size()];
        int index = 0;
        for(UserGroup ug : groups) {
            array[index] = ug.toString();
            index++;
        }
        return Arrays.toString(array);
    }

    public User() {

    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = DigestUtils.sha512Hex(password);
    }

    public User(String email, String username, String password, String picture, String website, String firstName, String lastName, String bio, String location) {
        this(email, username, password);
        this.picture = picture;
        this.website = website;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.location = location;

    }

    public Tweet tweet(String message) {
        Tweet tweet = new Tweet(message, this);
        this.tweets.add(tweet);
        return tweet;
    }

    public boolean follow(User user) {
        if (user == null || user == this) {
            return false;
        }

        user.addFollower(this);

        return following.add(user);
    }

    public boolean unfollow(User user) {
        user.removeFollower(this);
        return following.remove(user);
    }

    private void addFollower(User user) {
        followers.add(user);
    }

    private boolean removeFollower(User user) {
        return followers.remove(user);
    }

    public void addGroup(UserGroup group) {
        this.groups.add(group);
        group.addUser(this);
    }
    
    public void removeGroup(UserGroup group) {
        this.groups.remove(group);
        group.removeUser(this);
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
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

}
