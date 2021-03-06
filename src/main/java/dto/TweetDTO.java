package dto;

import domain.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.json.bind.annotation.JsonbDateFormat;

public class TweetDTO implements Serializable {

    private Long id;
    private String tweetedByString;
    private String tweetedBy_url;
    private String tweetedBy_fullname;
    private String message;
    private Date published;
    private List<String> tags;
    private List<String> likes;
    private List<String> mentions;
    private boolean hasBeenLiked;

    public Long getId() {
        return id;
    }

    public String getTweetedByString() {
        return tweetedByString;
    }

    public String getTweetedBy_url() {
        return tweetedBy_url;
    }

    public String getMessage() {
        return message;
    }

    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
    public Date getPublished() {
        return published;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> getLikes() {
        return likes;
    }

    public List<String> getMentions() {
        return mentions;
    }
    
    public boolean getHasBeenLiked() {
        return hasBeenLiked;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTweetedByString(String tweetedByString) {
        this.tweetedByString = tweetedByString;
    }

    public void setTweetedBy_url(String tweetedBy_url) {
        this.tweetedBy_url = tweetedBy_url;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }
    
    public void setHasBeenLiked(boolean liked) {
        this.hasBeenLiked = liked;
    }

    public String getTweetedBy_fullname() {
        return tweetedBy_fullname;
    }

    public void setTweetedBy_fullname(String tweetedBy_fullname) {
        this.tweetedBy_fullname = tweetedBy_fullname;
    }

    public TweetDTO() {
        
    }
    
    public TweetDTO(Long id, String tweetedByString, String message, Date published, List<String> tags, Set<User> likes, Set<User> mentions) {
        this.id = id;
        this.tweetedByString = tweetedByString;
        this.tweetedBy_url = "http://localhost:8080/Kwetter/api/users/" + tweetedByString;
        this.message = message;
        this.published = published;
        this.tags = tags;
        this.likes = new ArrayList<>();
        likes.forEach((u) -> {
            this.likes.add(u.getUsername());
        });

        this.mentions = new ArrayList<>();
        mentions.forEach((u) -> {
            this.mentions.add(u.getUsername());
        });
    }

}
