package dao;

import domain.User;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());

    @PersistenceContext
    EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public User findById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findByEmail(String email) {
        return em.createNamedQuery("User.findByEmail").setParameter("email", email).getResultList();
    }

    @Override
    public List<User> findByUsername(String username) {
        return em.createNamedQuery("User.findByUsername").setParameter("username", "%"+ username + "%").getResultList();
    }

    @Override
    public Set<User> findAllFollowers(String username) {
        List<User> users = findByUsername(username);
        if (users != null && users.size() == 1) {
            return users.get(0).getFollowers();
        }
        return new HashSet<>();
    }

    @Override
    public Set<User> findAllFollowing(String username) {
        List<User> users = findByUsername(username);
        if (users != null && users.size() == 1) {
            return users.get(0).getFollowing();
        }
        return new HashSet<>();
    }

    @Override
    public boolean insert(User user) {
        try {
            em.persist(user);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean update(User user) {
        em.merge(user);
        return true;
    }
}
