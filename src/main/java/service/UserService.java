package service;

import dao.UserDAO;
import dao.UserGroupDAO;
import domain.Tweet;
import domain.User;
import domain.UserGroup;
import java.util.ArrayList;
import java.util.Collections;
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

    public List<User> allUsers(int offset, int limit) {
        List<User> users = userDAO.findAll();
        int toIndex = users.size() < (offset + limit) ? users.size() : (offset + limit);
        return users.subList(offset, toIndex);
    }
    
    public List<User> allUsers() {
        return allUsers(0, 20);
    }

    public List<User> findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public boolean register(User u) {
        return userDAO.insert(u);
    }

    public List<User> findFollowing(String username) {
        return new ArrayList<>(userDAO.findAllFollowing(username));
    }

    public List<User> findFollowers(String username) {
        return new ArrayList<>(userDAO.findAllFollowers(username));
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
        if (persistedGroup == null) {
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
        if (persistedGroup == null) {
            return;
        }

        persistedUser.removeGroup(persistedGroup);
    }
    
    public List<Tweet> getTimeLine(String username, int offset, int limit) {
        List<User> users = userDAO.findByUsername(username);
        if (users == null || users.size() != 1) {
            return new ArrayList<>();
        }
        User user = users.get(0);
        
        List<Tweet> tweets = new ArrayList<>();
        Set<User> following = user.getFollowers();
        for (User u : following) {
            Set<Tweet> userTweets = u.getTweets();
            tweets.addAll(userTweets);
        }
        tweets.addAll(user.getTweets());
        Collections.sort(tweets);
        int toIndex = tweets.size() < (offset + limit) ? tweets.size() : (offset + limit);
        return tweets.subList(offset, toIndex);
    }

}
