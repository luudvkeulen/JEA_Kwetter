package dao;

import domain.User;
import java.util.List;
import java.util.Set;

public interface UserDAO {

    List<User> findAll();

    User findById(long id);

    List<User> findByEmail(String email);

    List<User> findByUsername(String username);

    Set<User> findAllFollowers(long id);

    Set<User> findAllFollowing(long id);

    boolean insert(User user);

    boolean update(User user);

}
