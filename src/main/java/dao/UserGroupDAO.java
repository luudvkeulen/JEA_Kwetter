package dao;

import domain.UserGroup;
import java.util.List;

public interface UserGroupDAO {
    public List<UserGroup> findAll();
    
    public UserGroup findByName(String groupName);
}
