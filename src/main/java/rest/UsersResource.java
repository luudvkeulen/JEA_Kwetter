package rest;

import domain.Tweet;
import domain.User;
import dto.TweetDTO;
import dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.*;
import static javax.ws.rs.core.Response.Status.*;
import javax.ws.rs.core.Response;
import service.TweetService;
import service.UserService;
import util.ConvertToDTO;

/**
 * This is a rest resource which is publicly available for all users (including
 * users which aren't logged in).
 *
 * @author Luud
 */
@Path("users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Stateless
public class UsersResource {

    @Inject
    private UserService userService;
    @Inject
    private TweetService tweetService;

    @GET
    public Response allUsers() {
        List<User> users = userService.allUsers();
        List<UserDTO> usersToReturn = ConvertToDTO.USERSTODTOS(users);

        return Response.ok(usersToReturn).build();
    }

    @POST
    public Response register(User u) {
        if (userService.register(u)) {
            return Response.ok().build();
        }
        
        return Response.serverError().build();
    }

    @GET
    @Path("{username}")
    public Response username(@PathParam("username") String username) {
        List<User> users = userService.findByUsername(username);
        List<UserDTO> dtoUsers = ConvertToDTO.USERSTODTOS(users);
        if (dtoUsers == null || dtoUsers.isEmpty()) {
            return Response.status(NOT_FOUND).build();
        }
        UserDTO user = dtoUsers.get(0);

        return Response.ok(user).build();
    }

    @GET
    @Path("{username}/followers")
    public Response followers(@PathParam("username") String username) {
        List<User> users = userService.findFollowers(username);
        List<UserDTO> dtoUsers = ConvertToDTO.USERSTODTOS(users);

        return Response.ok(dtoUsers).build();
    }

    @GET
    @Path("{username}/following")
    public Response following(@PathParam("username") String username) {
        List<User> users = userService.findFollowing(username);
        List<UserDTO> dtoUsers = ConvertToDTO.USERSTODTOS(users);

        return Response.ok(dtoUsers).build();
    }

    @GET
    @Path("{username}/tweets")
    public Response tweets(@PathParam("username") String username) {
        List<Tweet> tweets = tweetService.getTweetsFromUser(username);
        List<TweetDTO> tweetDTOs = ConvertToDTO.TWEETSTODTOS(tweets);
        return Response.ok(tweetDTOs).build();
    }
}
