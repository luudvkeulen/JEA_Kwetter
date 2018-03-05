
package rest;

import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.UserService;

@Path("users")
@Stateless
public class UserResource {

    @Inject
    private UserService userService;
    
    @GET
    @Path("allusers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> allUsers() {
        return userService.allUsers();
    }
    
    @GET
    @Path("findbyusername/{username}")
    public List<User> findByUsername(@PathParam("username") String username) {
        return userService.findByUsername(username);
    }
    
    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public void register(User u) {
        userService.register(u);
    }
}
