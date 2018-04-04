package rest;

import domain.Tweet;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
        //TODO implement
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
    
    @POST
    @Path("like/{tweetid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void like(@PathParam("tweetid") long tweetid, long userid) {
        tweetService.like(tweetid, userid);
    }
    
    @POST
    @Path("unlike/{tweetid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void unlike(@PathParam("tweetid") long tweetid, long userid) {
        tweetService.unlike(tweetid, userid);
    }
    
    /* DELETE */
    @DELETE
    @Path("remove/{tweetid}")
    public void remove(@PathParam("tweetid") long tweetid) {
        tweetService.remove(tweetid);
    }
}
