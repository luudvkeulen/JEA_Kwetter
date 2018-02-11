package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private Date published;
    private List<String> tags;
    @OneToOne
    private User tweetedBy;
    @OneToMany
    private List<User> likes;
    @OneToMany
    private List<User> mentions;

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getPublished() {
        return published;
    }

    public List<String> getTags() {
        return tags;
    }

    public User getTweetedBy() {
        return tweetedBy;
    }

    public List<User> getLikes() {
        return likes;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public Tweet() {
        tags = new ArrayList<>();
        mentions = new ArrayList<>();
        likes = new ArrayList<>();
    }

    public Tweet(String message, User user) {
        this();
        this.message = message;
        this.tweetedBy = user;
    }

    public Tweet(String message, User user, List<String> tags) {
        this(message, user);
        this.tags = tags;
    }

    public boolean like(User user) {
        if (user == null) {
            return false;
        }
        if (likes.contains(user)) {
            return false;
        }
        return likes.add(user);
    }

    public boolean unlike(User user) {
        if (user == null) {
            return false;
        }
        return likes.remove(user);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.message);
        hash = 47 * hash + Objects.hashCode(this.published);
        hash = 47 * hash + Objects.hashCode(this.tags);
        hash = 47 * hash + Objects.hashCode(this.tweetedBy);
        return hash;
    }
}
