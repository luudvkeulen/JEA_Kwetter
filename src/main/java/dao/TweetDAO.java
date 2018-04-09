package dao;

import domain.Tweet;
import java.util.List;

public interface TweetDAO {

    List<Tweet> findAll();

    Tweet findById(long id);

    List<Tweet> findByMessage(String query);
    
    List<Tweet> getTweetsFromUser(String username);

    Tweet insert(Tweet tweet);

    boolean update(Tweet tweet);

    boolean delete(Tweet tweet);
}
