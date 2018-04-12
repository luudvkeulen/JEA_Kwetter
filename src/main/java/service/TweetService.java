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
        Matcher m = Pattern.compile("(?:\\s@)([A-Za-z0-9_]+)").matcher(prefixedString);
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

    public Tweet tweet(Tweet tweet) {
        findMentions(tweet.getMessage()).forEach(u
                -> tweet.addMention(u)
        );

        return tweetDAO.insert(tweet);
    }

    public List<Tweet> findByMessage(String query) {
        return tweetDAO.findByMessage(query);
    }

    public Tweet like(long tweetid, long userid) {
        Tweet tweet = tweetDAO.findById(tweetid);
        User user = userDAO.findById(userid);
        System.out.println("User and tweet: " + user + " - " + tweet);
        if (tweet != null && user != null) {
            tweet.like(user);
            return tweet;
        }
        
        return null;
    }

    public Tweet unlike(long tweetid, long userid) {
        Tweet tweet = tweetDAO.findById(tweetid);
        User user = userDAO.findById(userid);
        if (tweet != null && user != null) {
            tweet.unlike(user);
            return tweet;
        }
        return null;
    }

    public void remove(long tweetid) {
        Tweet tweet = tweetDAO.findById(tweetid);
        tweetDAO.delete(tweet);
    }

    public List<Tweet> getTweetsFromUser(String username) {
        return tweetDAO.getTweetsFromUser(username);
    }
}
