package dao;

import domain.Tweet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TweetDAOImpl implements TweetDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Tweet> findAll() {
        return em.createNamedQuery("Tweet.findAll").getResultList();
    }

    @Override
    public Tweet findById(long id) {
        return em.find(Tweet.class, id);
    }

    @Override
    public List<Tweet> findByMessage(String message) {
        return em.createNamedQuery("Tweet.findByMessage").setParameter("message", "%" + message + "%").getResultList();
    }

    @Override
    public Tweet insert(Tweet tweet) {
        em.persist(tweet);
        return tweet;
    }

    @Override
    public boolean update(Tweet tweet) {
        em.merge(tweet);
        return true;
    }

    @Override
    public boolean delete(Tweet tweet) {
        em.remove(tweet);
        return true;
    }

    @Override
    public List<Tweet> getTweetsFromUser(String username) {
        return em.createNamedQuery("Tweet.getFromUser").setParameter("username", username).getResultList();
    }

}
