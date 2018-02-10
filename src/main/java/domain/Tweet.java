package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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

    public Tweet() {
    }

    public Tweet(String message, User user) {
        this.message = message;
        this.tweetedBy = user;
    }

    public Tweet(String message, User user, List<String> tags) {
        this.message = message;
        this.tweetedBy = user;
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
}
