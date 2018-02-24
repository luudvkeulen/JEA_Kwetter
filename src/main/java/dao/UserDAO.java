package dao;

import domain.User;
import java.util.List;

public interface UserDAO {

    User addUser(User user);

    List<User> getUsers();

    User getUserByEmail(String email);

    List<User> getFollowers();

    List<User> getFollowing();
}
