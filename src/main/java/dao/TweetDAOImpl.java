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
    public Tweet findById(int id) {
        return em.find(Tweet.class, id);
    }

    @Override
    public List<Tweet> findByMessage(String query) {
        return em.createNamedQuery("Tweet.findByMessage").setParameter("message", query).getResultList();
    }

    @Override
    public boolean insert(Tweet tweet) {
        em.persist(tweet);
        return true;
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

}
