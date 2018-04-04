package dao;

import domain.User;
import java.util.List;
import java.util.Set;

public interface UserDAO {

    List<User> findAll();

    User findById(long id);

    List<User> findByEmail(String email);

    List<User> findByUsername(String username);

    Set<User> findAllFollowers(String username);

    Set<User> findAllFollowing(String username);

    boolean insert(User user);

    boolean update(User user);

}
