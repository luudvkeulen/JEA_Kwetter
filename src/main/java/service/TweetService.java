package service;

import dao.TweetDAO;
import dao.UserDAO;
import domain.Tweet;
import domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TweetService {

    @Inject
    private UserDAO userDAO;
    
    @Inject
    private TweetDAO tweetDAO;

    private List<User> findMentions(String message) {
        List<User> mentions = new ArrayList<>();

        String prefixedString = " ".concat(message);
        Matcher m = Pattern.compile("(?:\\s#)([A-Za-z0-9_]+)").matcher(prefixedString);
        while (m.find()) {
            List<User> users = userDAO.findByUsername(m.group(1));
            if (!users.isEmpty()) {
                mentions.add(users.get(0));
            }
        }

        return mentions;
    }
    
    public List<Tweet> allTweets() {
        return tweetDAO.findAll();
    }
    
    public Tweet getTweet(long id) {
        return tweetDAO.findById(id);
    }
    
    public void tweet(Tweet tweet) {
        findMentions(tweet.getMessage()).forEach(u -> {
            tweet.addMention(u);
        });
        
        tweetDAO.insert(tweet);
    }
}
