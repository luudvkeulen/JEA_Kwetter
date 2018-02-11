package domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TweetTest {
    
    public TweetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testConstructors() {
        User testUser = new User();
        String testString = "test";
        Tweet tweet = new Tweet(testString, testUser);
        assertEquals(testString, tweet.getMessage());
        
        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");
        tweet = new Tweet(testString, testUser, tags);
        assertEquals(tags, tweet.getTags());
    }

    /**
     * Test of like method, of class Tweet.
     */
    @Test
    public void testLike() {
        User user1 = new User("test1", "");
        User user2 = new User("test2", "");
        Tweet tweet = new Tweet();
        
        //Test normal like
        boolean result1 = tweet.like(user1);
        assertTrue(result1);
        boolean result2 = tweet.like(user2);
        assertTrue(result2);
       
        //Test duplicate like
        result1 = tweet.like(user1);
        assertFalse(result1);
        result2 = tweet.like(user2);
        assertFalse(result2);
        
        //Test null like
        boolean resultnull = tweet.like(null);
        assertFalse(resultnull);
    }

    /**
     * Test of unlike method, of class Tweet.
     */
    @Test
    public void testUnlike() {
        
    }
    
}
