package rest;

import domain.User;
import java.util.List;
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
    @Produces(APPLICATION_JSON)
    public List<User> allUsers() {
        return userService.allUsers();
    }

    @GET
    @Path("{username}")
    public List<User> findByUsername(@PathParam("username") String username) {
        return userService.findByUsername(username);
    }

    @GET
    @Path("{username}/followers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response followers(@PathParam("username") String username) {
        //return userService.findFollowers(id);
        return Response.ok().build();
    }

    @GET
    @Path("{username}/following")
    @Produces(MediaType.APPLICATION_JSON)
    public Response following(@PathParam("username") String username) {
        //return userService.findFollowing(id);
        return Response.ok().build();
    }

    /*@GET
    @Path("{userid}/timeline")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tweet> timeline(@PathParam("userid") long id) {
        return userService.getTimeLine(id);
    }*/

    /* POST */
    @POST
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
