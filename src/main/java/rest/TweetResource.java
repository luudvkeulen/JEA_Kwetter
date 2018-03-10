package rest;

import domain.Tweet;
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
import service.TweetService;

@Path("tweets")
@Stateless
public class TweetResource {

    @Inject
    private TweetService tweetService;

    /* GET */
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tweet> all() {
        return tweetService.allTweets();
    }

    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tweet get(@PathParam("id") long id) {
        return tweetService.getTweet(id);
    }

    @GET
    @Path("getLatest/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tweet> getLatest(@PathParam("userid") long userid) {
        return null;
    }

    @GET
    @Path("findbymessage/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tweet> findByMessage(@PathParam("query") String query) {
        return tweetService.findByMessage(query);
    }

    /* POST */
    @POST
    @Path("tweet/{userid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void tweet(@PathParam("userid") long id, Tweet tweet) {
        tweetService.tweet(tweet);
    }
}
