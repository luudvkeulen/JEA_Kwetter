package dao;

import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public User findById(int id) {
        return em.find(User.class, id);
    }
    
    @Override
    public List<User> findByEmail(String email) {
        return em.createNamedQuery("User.findByEmail").setParameter("email", email).getResultList();
    }

    @Override
    public List<User> findAllFollowers() {
        return em.createNamedQuery("User.findAllFollowers").getResultList();
    }

    @Override
    public List<User> findAllFollowing() {
        return em.createNamedQuery("User.findAllFollowing").getResultList();
    }

    @Override
    public boolean insert(User user) {
        em.persist(user);
        return true;
    }

    @Override
    public boolean update(User user) {
        em.merge(user);
        return true;
    }

}
