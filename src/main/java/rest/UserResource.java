package rest;

import domain.Tweet;
import domain.User;
import java.util.List;
import java.util.Set;
import javax.crypto.KeyGenerator;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.*;
import javax.ws.rs.core.Response;
import service.UserService;

@Path("users")
@Stateless
public class UserResource {

    @Inject
    private UserService userService;
    
    //@Inject
    private KeyGenerator keyGenerator;

    /* GET */
    @GET
    @Path("allusers")
    @Produces(APPLICATION_JSON)
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

    @GET
    @Path("timeline/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tweet> timeline(@PathParam("userid") long id) {
        return userService.getTimeLine(id);
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
    
    @POST
    @Path("login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("username") String username, @FormParam("password")String password) {
        
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
