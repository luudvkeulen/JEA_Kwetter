package controllers;

import domain.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.UserService;

@Named
@ViewScoped
public class UserController implements Serializable {

    @Inject
    private UserService userService;

    private List<User> users;

    private User selectedUser;

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public UserController() {

    }

    @PostConstruct
    public void init() {
        this.users = userService.allUsers();
    }

    public List<User> getUsers() {
        return users;
    }

    public void promote() {
        userService.promote(selectedUser.getId());
        this.users = userService.allUsers();
    }

    public void demote() {
        userService.demote(selectedUser.getId());
        this.users = userService.allUsers();
    }

}
