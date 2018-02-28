package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name = "Tweet.findAll", query = "SELECT t FROM Tweet t"),
    @NamedQuery(name = "Tweet.findByMessage", query = "SELECT t FROM Tweet t WHERE t.message LIKE '%:message%'")
})
public class Tweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private Date published;
    private List<String> tags = new ArrayList<>();
    @ManyToOne
    private User tweetedBy;
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "tweet_likes")
    private final Set<User> likes = new HashSet<>();
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "tweet_mentions")
    private final Set<User> mentions = new HashSet<>();

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
        return Collections.unmodifiableList(tags);
    }

    public User getTweetedBy() {
        return tweetedBy;
    }

    public Set<User> getLikes() {
        return Collections.unmodifiableSet(likes);
    }

    public Set<User> getMentions() {
        return Collections.unmodifiableSet(mentions);
    }

    public Tweet() {

    }

    public Tweet(String message, User user) {
        this.message = message;
        this.tweetedBy = user;
        this.tags = findTags(message);
        this.published = new Date();
    }

    public boolean like(User user) {
        if (user == null) {
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

    public void addMention(User user) {
        this.mentions.add(user);
    }

    private List<String> findTags(String message) {
        return findRegexMatches(message, "(?:\\s#)([A-Za-z0-9_]+)");
    }

    private List<String> findRegexMatches(String message, String regex) {
        List<String> matches = new ArrayList<>();
        String prefixedString = " ".concat(message);
        Matcher m = Pattern.compile(regex).matcher(prefixedString);
        while (m.find()) {
            matches.add(m.group(1));
        }
        return matches;
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
        final Tweet other = (Tweet) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.published, other.published)) {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        if (!Objects.equals(this.tweetedBy, other.tweetedBy)) {
            return false;
        }
        if (!Objects.equals(this.likes, other.likes)) {
            return false;
        }
        return Objects.equals(this.mentions, other.mentions);
    }
}
