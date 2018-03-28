package service;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import dao.UserDAO;
import domain.Tweet;
import domain.User;
import domain.UserGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        UserGroup userGroup = new UserGroup("users");
        UserGroup modGroup = new UserGroup("moderators");
        UserGroup adminGroup = new UserGroup("admins");
        
        //Create and persist admin
        User admin = new User("admin@admin.com", "admin", "admin");
        admin.addGroup(adminGroup);
        userDAO.insert(admin);
        
        //Create and persist moderator
        User moderator = new User("mod@mod.com", "moderator", "moderator");
        moderator.addGroup(modGroup);
        userDAO.insert(moderator);
        
        //Generation settings
        final int usercount = 25;
        final int tweets = 30;
        
        //Generate users;
        List<User> users = new ArrayList<>();
        for(int i = 0; i < usercount; i++) {
            User tempUser = new User("test" + i + "@test.com", "test" + i, "12345678", "", "test" + i + ".nl", "TestFN" + i, "TestLN" + 1, "Hello I am test" + i, "Eindhoven");
            tempUser.addGroup(userGroup);
            userDAO.insert(tempUser);
            users.add(tempUser);
        }
        
        Lorem lorem = LoremIpsum.getInstance();
        Random r = new Random();
        for(User u : users) {
            int utweets = r.nextInt(tweets);
            for(int i = 0; i < utweets; i++) {
                u.tweet(lorem.getWords(2, 12));
            }
            
            int follow = users.size() / 3;
            List<User> toFollow = new ArrayList<>(users);
            toFollow.remove(u);
            for(int i = 0; i < follow; i++) {
                User otherUser = toFollow.get(r.nextInt(toFollow.size()));
                u.follow(otherUser);
                toFollow.remove(otherUser);
            }
        }
    }
}
