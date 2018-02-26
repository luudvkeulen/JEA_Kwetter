package dao;

import domain.User;
import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User findById(int id);
    
    List<User> findByEmail(String email);

    List<User> findAllFollowers();

    List<User> findAllFollowing();

    boolean insert(User user);

    boolean update(User user);

}
