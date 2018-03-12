package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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

    /**
     * Test of constructor, of class Tweet.
     */
    @Test
    public void testConstructor() {
        User testUser = new User();
        String testString = "test";
        Tweet tweet = new Tweet(testString, testUser);
        assertEquals(testString, tweet.getMessage());
        assertEquals(testUser, tweet.getTweetedBy());
    }

    /**
     * Test of like method, of class Tweet.
     */
    @Test
    public void testLike() {
        User user1 = new User("test1", "test1", "");
        User user2 = new User("test2", "test1", "");
        Tweet tweet = new Tweet();

        //Test normal like
        boolean result1 = tweet.like(user1);
        assertTrue(result1);
        boolean result2 = tweet.like(user2);
        assertTrue(result2);

        Set<User> likes = tweet.getLikes();
        Set<User> expectedLikes = new HashSet<>();
        expectedLikes.add(user1);
        expectedLikes.add(user2);
        assertEquals(expectedLikes, likes);

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
        User user1 = new User("test1", "test1", "");
        User user2 = new User("test2", "test1", "");
        Tweet tweet = new Tweet();
        tweet.like(user1);
        boolean unlike1 = tweet.unlike(user1);
        boolean unlike2 = tweet.unlike(user2);

        assertTrue(unlike1);
        assertFalse(unlike2);

        boolean unlikeNull = tweet.unlike(null);
        assertFalse(unlikeNull);
    }

    /**
     * Test of findTags method, of class Tweet.
     */
    @Test
    public void testTags() {
        User user = new User();
        Tweet tweet = new Tweet("#test dit is een #bericht", user);

        ArrayList<String> expectedTags = new ArrayList<>();
        expectedTags.add("test");
        expectedTags.add("bericht");

        assertEquals(expectedTags, tweet.getTags());
    }

    @Test
    public void testAddMention() {
        User user = new User("email", "username", "pw");
        Tweet tweet = new Tweet();
        tweet.addMention(user);
        Set<User> expectedMentions = new HashSet<>();
        expectedMentions.add(user);
        assertEquals(expectedMentions, tweet.getMentions());
    }

    @Test
    public void testEquals() {
        User user1 = new User();
        Tweet tweet1 = new Tweet("message1", user1);
        Tweet tweet2 = new Tweet("message2", user1);

        boolean equals = tweet1.equals(tweet2);
        assertFalse(equals);

        equals = tweet1.equals(tweet1);
        assertTrue(equals);

        equals = tweet1.equals(null);
        assertFalse(equals);

    }

    @Test
    public void testHashcode() {
        User user = new User();
        Tweet tweet1 = new Tweet("1", user);
        Tweet tweet2 = new Tweet("2", user);
        //Tweet tweet3 = new Tweet("1", user);
        assertFalse(tweet1.hashCode() == tweet2.hashCode());
    }
}
