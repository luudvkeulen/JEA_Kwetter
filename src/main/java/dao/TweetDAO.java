package dao;

import domain.Tweet;
import domain.User;

public interface TweetDAO {

    Tweet getTweet(int id);

    Tweet create(String content, User user);

    boolean remove(Tweet tweet);
}
