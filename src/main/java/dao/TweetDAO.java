package dao;

import domain.Tweet;
import domain.User;
import java.util.List;

public interface TweetDAO {

    Tweet getTweet(int id);

    Tweet create(String content, User user);

    Tweet create(String content, User user, List<String> tags);

    boolean remove(Tweet tweet);
}
