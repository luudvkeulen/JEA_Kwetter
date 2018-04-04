package rest;

import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import service.UserService;

/**
 * This is a rest resource which is available for the currently logged in user.
 *
 * @author Luud
 */
@Path("user")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Stateless
@JWTTokenNeeded
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    public Response get() {
        List<User> users = userService.findByUsername("");
        if (users != null && users.size() == 1) {
            return Response.ok(users.get(0)).build();
        }
        return Response.serverError().build();
    }

    @GET
    @Path("timeline")
    public Response timeline() {
        //return userService.getTimeLine(1);
        return Response.noContent().build();
    }

    @PUT
    @Path("following/:username")
    public Response follow(@PathParam("username") String username) {
        return Response.ok().build();
    }
    
    @DELETE
    @Path("following/:username")
    public Response unfollow(@PathParam("username") String username) {
        return Response.ok().build();
    }
}
