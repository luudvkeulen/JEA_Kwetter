package controllers;

import domain.Tweet;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.TweetService;

@Named
@ViewScoped
public class TweetController implements Serializable {

    @Inject
    private TweetService tweetService;

    private List<Tweet> tweets;

    private Tweet selectedTweet;
    
    public TweetController() {

    }

    @PostConstruct
    public void init() {
        this.tweets = tweetService.allTweets();
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void delete() {
        tweetService.remove(selectedTweet.getId());
        this.tweets = tweetService.allTweets();
    }

    public Tweet getSelectedTweet() {
        return selectedTweet;
    }

    public void setSelectedTweet(Tweet selectedTweet) {
        this.selectedTweet = selectedTweet;
    }
    
    
}
