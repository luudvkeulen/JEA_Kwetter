
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
    
    @GET
    @Path("alltweets")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tweet> allTweets() {
        return tweetService.allTweets();
    }
    
    @GET
    @Path("gettweet/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tweet getTweet(@PathParam("id") long id) {
        return tweetService.getTweet(id);
    }
    
    @POST
    @Path("tweet")
    @Consumes(MediaType.APPLICATION_JSON)
    public void tweet(Tweet tweet) {
        
    }
}
