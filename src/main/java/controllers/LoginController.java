package controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import service.UserService;

@Named
@SessionScoped
public class LoginController implements Serializable {

    @Inject
    private UserService userService;

    private String username;
    private String password;
    private boolean loggedIn;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public LoginController() {

    }

    public String login() {
        boolean valid = userService.verifyPassword(username, password);
        if (valid) {
            loggedIn = true;
            return "/index.html";
        }

        FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return "/login.xhtml";
    }
    
    public String logout() {
        loggedIn = false;
        return "/login.xhtml";
    }
}
