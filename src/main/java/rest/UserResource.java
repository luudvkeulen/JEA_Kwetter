package rest;

import domain.Tweet;
import domain.User;
import dto.TweetDTO;
import dto.UserDTO;
import java.util.ArrayList;
import java.util.Date;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import service.TweetService;
import service.UserService;
import util.ConvertToDTO;

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
    @Inject
    private TweetService tweetService;

    @Context
    SecurityContext securityContext;

    @GET
    public Response get() {
        String username = securityContext.getUserPrincipal().getName();
        List<User> users = userService.findByUsername(username);
        if (users != null && users.size() == 1) {
            User u = users.get(0);
            UserDTO userDTO = new UserDTO(
                    u.getId(),
                    u.getUsername(),
                    u.getFirstName(),
                    u.getLastName(),
                    u.getPicture(),
                    u.getWebsite(),
                    u.getBio(),
                    u.getLocation(),
                    u.getEmail(),
                    u.getTweets().size(),
                    u.getFollowing().size(),
                    u.getFollowers().size()
            );
            return Response.ok(userDTO).build();
        }
        return Response.serverError().build();
    }

    @PUT
    @Path("tweets")
    public Response tweet(TweetDTO tweetDTO) {
        Tweet tweet = new Tweet();
        tweet.setMessage(tweetDTO.getMessage());
        String username = securityContext.getUserPrincipal().getName();
        List<User> users = userService.findByUsername(username);
        if (users == null || users.isEmpty()) {
            return Response.serverError().build();
        }
        User user = users.get(0);
        tweet.setTweetedBy(user);
        tweet.setPublished(new Date());
        tweet.fillTags();

        Tweet persistedTweet = tweetService.tweet(tweet);
        TweetDTO persistedTweetDTO = new TweetDTO(
                persistedTweet.getId(),
                persistedTweet.getTweetedBy().getUsername(),
                persistedTweet.getMessage(),
                persistedTweet.getPublished(),
                persistedTweet.getTags(),
                persistedTweet.getLikes(),
                persistedTweet.getMentions()
        );
        
        System.out.println("Tags: " + persistedTweet.getTags());
        return Response.ok(persistedTweetDTO).build();
    }

    @GET
    @Path("timeline")
    public Response timeline(
            @QueryParam("offset") int offset,
            @QueryParam("limit") int limit,
            @Context SecurityContext securityContext) {
        String username = securityContext.getUserPrincipal().getName();
        List<Tweet> timelineRaw = userService.getTimeLine(username, offset, limit);
        List<TweetDTO> timelineDTO = ConvertToDTO.TWEETSTODTOS(timelineRaw);
        return Response.ok(timelineDTO).build();
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
