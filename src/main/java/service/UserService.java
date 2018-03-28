package service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import dao.UserDAO;
import dao.UserGroupDAO;
import domain.Tweet;
import domain.User;
import domain.UserGroup;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;

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

    public List<Tweet> getTimeLine(long userid) {
        User user = userDAO.findById(userid);
        if (user == null) {
            return new ArrayList<>();
        }

        List<Tweet> tweets = new ArrayList<>();
        Set<User> following = user.getFollowers();
        for (User u : following) {
            Set<Tweet> userTweets = u.getTweets();
            tweets.addAll(userTweets);
        }
        Collections.sort(tweets);
        return tweets;
    }

    public boolean authenticate(String username, String password) {
        List<User> users = userDAO.findByUsername(username);
        if (users.isEmpty()) {
            return false;
        }

        User user = users.get(0);

        String hashedPw = DigestUtils.sha512Hex(password);
        return hashedPw.equals(user.getPassword());
    }

    public String issueToken(String login) {
        Algorithm algorithm;
        String token = "";
        try {
            algorithm = Algorithm.HMAC512("supersecret");
            token = JWT.create().withSubject(login).withIssuer("Luud").sign(algorithm);
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
        }

        return token;
    }
}
