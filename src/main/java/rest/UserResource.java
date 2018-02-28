
package rest;

import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
}
