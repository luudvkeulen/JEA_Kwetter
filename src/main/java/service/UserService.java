package service;

import dao.UserDAO;
import dao.UserGroupDAO;
import domain.User;
import domain.UserGroup;
import domain.UserRole;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;
    
    @Inject
    private UserGroupDAO userGroupDAO;

    public List<User> allUsers() {
        return userDAO.findAll();
    }

    public List<User> findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public void register(User u) {
        userDAO.insert(u);
    }

    public Set<User> findFollowing(long id) {
        return userDAO.findAllFollowing(id);
    }

    public Set<User> findFollowers(long id) {
        return userDAO.findAllFollowers(id);
    }

    public void follow(long userid, long otheruserid) {
        User user = userDAO.findById(userid);
        User otherUser = userDAO.findById(otheruserid);
        if (user != null && otherUser != null) {
            user.follow(otherUser);
        }
    }

    public void unfollow(long userid, long otheruserid) {
        User user = userDAO.findById(userid);
        User otherUser = userDAO.findById(otheruserid);
        if (user != null && otherUser != null) {
            user.unfollow(otherUser);
        }
    }

    public void addGroup(User user, UserGroup userGroup) {
        User persistedUser = userDAO.findById(user.getId());
        if (persistedUser == null) {
            return;
        }
        
        UserGroup persistedGroup = userGroupDAO.findByName(userGroup.getGroupName());
        if(persistedGroup == null) {
            return;
        }

        persistedUser.addGroup(persistedGroup);
    }
    
    public void removeGroup(User user, UserGroup userGroup) {
        User persistedUser = userDAO.findById(user.getId());
        if (persistedUser == null) {
            return;
        }
        
        UserGroup persistedGroup = userGroupDAO.findByName(userGroup.getGroupName());
        if(persistedGroup == null) {
            return;
        }
        
        persistedUser.removeGroup(persistedGroup);
    }
}
