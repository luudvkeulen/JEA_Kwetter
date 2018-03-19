package service;

import dao.UserGroupDAO;
import domain.UserGroup;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserGroupService {

    @Inject
    UserGroupDAO userGroupDAO;
    
    public List<UserGroup> findAll() {
        return userGroupDAO.findAll();
    }
}
