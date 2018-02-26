package service;

import dao.UserDAO;
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
        User user = new User("test1@test.com", "12345");
        user.tweet("testMessage");
        userDAO.insert(user);
    }
}
