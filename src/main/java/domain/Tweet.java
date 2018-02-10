package domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tweet {
    
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String message;
    private Date published;
    private List<String> tags;
    private User tweetedBy;
    private List<User> likes;
    private List<User> mentions;
    
    public Tweet() {
    }
}
