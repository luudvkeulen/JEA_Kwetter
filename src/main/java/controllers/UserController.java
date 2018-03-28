package controllers;

import domain.User;
import domain.UserGroup;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.UserGroupService;
import service.UserService;

@Named
@ViewScoped
public class UserController implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    UserGroupService userGroupService;

    private List<User> users;
    private List<UserGroup> userGroups;

    private User selectedUser;
    private UserGroup selectedUserGroup;

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public UserGroup getSelectedUserGroup() {
        return selectedUserGroup;
    }

    public void setSelectedUserGroup(UserGroup selectedUserGroup) {
        this.selectedUserGroup = selectedUserGroup;
    }

    public UserController() {

    }

    @PostConstruct
    public void init() {
        this.users = userService.allUsers();
        this.userGroups = userGroupService.findAll();
    }

    public List<User> getUsers() {
        return users;
    }
    
    public void addGroup() {
        if(selectedUser.getGroups().contains(selectedUserGroup)) {
            return;
        }
        
        userService.addGroup(selectedUser, selectedUserGroup);
        users = userService.allUsers();
    }
    
    public void removeGroup() {
        if(!selectedUser.getGroups().contains(selectedUserGroup)) {
            return;
        }
        
        userService.removeGroup(selectedUser, selectedUserGroup);
        users = userService.allUsers();
    }
}
