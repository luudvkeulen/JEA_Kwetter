package service;

import dao.UserDAO;
import domain.User;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;

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

    public void promote(long id) {
        User user = userDAO.findById(id);
        if (user == null) {
            return;
        }
        user.promote();
    }

    public void demote(long id) {
        User user = userDAO.findById(id);
        user.demote();
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
}
