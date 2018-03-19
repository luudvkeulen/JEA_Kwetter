package rest;

import domain.User;
import java.util.List;
import java.util.Set;
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

    /* GET */
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

    @GET
    @Path("followers/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<User> followers(@PathParam("userid") long id) {
        return userService.findFollowers(id);
    }

    @GET
    @Path("following/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<User> following(@PathParam("userid") long id) {
        return userService.findFollowing(id);
    }

    /* POST */
    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public void register(User u) {
        userService.register(u);
    }

    @POST
    @Path("follow/{userid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void follow(@PathParam("userid") long id, long otheruser) {
        userService.follow(id, otheruser);
    }
    
    @POST
    @Path("unfollow/{userid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void unfollow(@PathParam("userid") long id, long otheruser) {
        userService.unfollow(id, otheruser);
    }
}
