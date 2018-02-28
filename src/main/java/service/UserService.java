package service;

import dao.UserDAO;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;

    public List<User> allUsers() {
        return userDAO.findAll();
    }
}
