package dao;

import domain.Tweet;
import java.util.List;

public interface TweetDAO {

    List<Tweet> findAll();

    Tweet findById(int id);

    List<Tweet> findByMessage(String query);

    boolean insert(Tweet tweet);

    boolean update(Tweet tweet);

    boolean delete(Tweet tweet);
}
