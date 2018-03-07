package service;

import dao.TweetDAO;
import domain.Tweet;
import domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class TweetServiceTest {

    @Mock
    private TweetDAO tweetDAO;

    @InjectMocks
    private TweetService tweetService;

    public TweetServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of allTweets method, of class TweetService.
     */
    @Test
    public void testAllTweets() throws Exception {
        tweetService.allTweets();

        verify(tweetDAO, times(1)).findAll();
    }

    /**
     * Test of getTweet method, of class TweetService.
     */
    @Test
    public void testGetTweet() throws Exception {
        tweetService.getTweet(1);

        verify(tweetDAO, times(1)).findById(1);
    }

    /**
     * Test of tweet method, of class TweetService.
     */
    @Test
    public void testTweet() throws Exception {
        Tweet tweet = new Tweet("Test", new User());
        tweetService.tweet(tweet);

        verify(tweetDAO, times(1)).insert(tweet);
    }

    /**
     * Test of findByMessage method, of class TweetService.
     */
    @Test
    public void testFindByMessage() throws Exception {
        tweetService.findByMessage("mes");
        
        verify(tweetDAO, times(1)).findByMessage("mes");
    }

}
