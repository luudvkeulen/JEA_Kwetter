package dao;

import domain.UserGroup;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserGroupDAOImpl implements UserGroupDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<UserGroup> findAll() {
        return em.createNamedQuery("UserGroup.findAll").getResultList();
    }

    @Override
    public UserGroup findByName(String groupName) {
        return em.find(UserGroup.class, groupName);
    }

}
