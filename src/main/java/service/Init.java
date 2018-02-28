package service;

import dao.UserDAO;
import domain.Tweet;
import domain.User;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class Init {

    @Inject
    UserDAO userDAO;

    @PostConstruct
    public void init() {
        User user1 = new User("test1@test.com", "12345");
        Tweet user1tweet1 = user1.tweet("testMessage");
        Tweet user1tweet2 = user1.tweet("testMessage2");
        User user2 = new User("test2@test.com", "12345");
        user2.follow(user1);
        Tweet user2tweet1 = user2.tweet("testMessage1");
        user1tweet1.like(user2);
        user1tweet2.like(user2);
        user1tweet2.addMention(user2);
        user2tweet1.like(user1);
        user2tweet1.addMention(user2);
        userDAO.insert(user1);
        userDAO.insert(user2);
        User user3 = new User("tes3@test.com", "Password");
        user3.tweet("Hoi");
        userDAO.insert(user3);
    }
}
