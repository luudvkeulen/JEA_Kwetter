package rest;

import domain.Tweet;
import java.util.ArrayList;
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
import javax.ws.rs.core.Response;
import service.TweetService;

@Path("tweets")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class TweetResource {

    @Inject
    private TweetService tweetService;

    /* GET */
    @GET
    public List<Tweet> all() {
        return tweetService.allTweets();
    }

    @GET
    @Path("{id}")
    public Tweet get(@PathParam("id") long id) {
        return tweetService.getTweet(id);
    }
    
    @GET
    @Path("trending")
    public Response trending() {
        return Response.noContent().build();
    }
    

    @GET
    @Path("findbymessage/{query}")
    public List<Tweet> findByMessage(@PathParam("query") String query) {
        return tweetService.findByMessage(query);
    }
    
    /* DELETE */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") long tweetid) {
        tweetService.remove(tweetid);
    }
}
