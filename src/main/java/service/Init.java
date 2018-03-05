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
        User user1 = new User("test1@test.com", "test1", "12345", "", "test1.nl", "TestN", "TestLN", "Bio", "Eindhoven");
        Tweet user1tweet1 = user1.tweet("testMessage #fun #java");
        Tweet user1tweet2 = user1.tweet("testMessage2");
        User user2 = new User("test2@test.com", "test2", "12345");
        user2.follow(user1);
        Tweet user2tweet1 = user2.tweet("testMessage1 #nice");
        user1tweet1.like(user2);
        user1tweet2.like(user2);
        user1tweet2.addMention(user2);
        user2tweet1.like(user1);
        user2tweet1.addMention(user2);
        userDAO.insert(user1);
        userDAO.insert(user2);
        User user3 = new User("tes3@test.com", "test3", "Password");
        user3.tweet("Hoi");
        userDAO.insert(user3);
    }
}
