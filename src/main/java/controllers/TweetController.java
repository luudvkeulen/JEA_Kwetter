package controllers;

import domain.Tweet;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.TweetService;

@Named
@RequestScoped
public class TweetController implements Serializable {

    @Inject
    private TweetService tweetService;

    private List<Tweet> tweets;

    public TweetController() {

    }

    @PostConstruct
    public void init() {
        this.tweets = tweetService.allTweets();
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

}
