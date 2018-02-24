package domain;

import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    public UserTest() {
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
    public void testConstructor() {
        String picture = "picturetest";
        String website = "website";
        String firstname = "firstname";
        String lastname = "lastname";
        String bio = "bio";
        String location = "location";
        String email = "email";
        String password = "password";
        User user = new User(picture, website, firstname, lastname, bio, location, email, password);
        assertEquals(user.getPicture(), picture);
        assertEquals(user.getWebsite(), website);
        assertEquals(user.getFirstName(), firstname);
        assertEquals(user.getLastName(), lastname);
        assertEquals(user.getBio(), bio);
        assertEquals(user.getLocation(), location);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getPassword(), password);
        assertNotNull(user.getFollowers());
        assertNotNull(user.getFollowing());
        assertNotNull(user.getTweets());
    }
    
    /**
     * Test of follow method, of class User.
     */
    @Test
    public void testFollow() {
        User user1 = new User("user1", "");
        User user2 = new User("user2", "");
        boolean result1 = user1.follow(user2);
        assertTrue(result1);
        User followingUser = user1.getFollowing().iterator().next();
        assertEquals(user2, followingUser);
        User followedUser = user2.getFollowers().iterator().next();
        assertEquals(user1, followedUser);
        
        boolean result2 = user1.follow(user1);
        assertFalse(result2);
        boolean result3 = user1.follow(null);
        assertFalse(result3);
    }

    /**
     * Test of unfollow method, of class User.
     */
    @Test
    public void testUnfollow() {
        User user1 = new User("user1", "");
        User user2 = new User("user2", "");
        user1.follow(user2);
        boolean unfollowresult = user1.unfollow(user2);
        assertTrue(unfollowresult);
        
        int following = user1.getFollowing().size();
        int followers = user2.getFollowers().size();
        assertEquals(0, following);
        assertEquals(0, followers);
    }

    /**
     * Test of promote method, of class User.
     */
    @Test
    public void testPromote() {
        User user = new User();
        assertEquals(UserRole.USER, user.getUserRole());

        user.promote();
        assertEquals(UserRole.MODERATOR, user.getUserRole());
        user.promote();
        assertEquals(UserRole.ADMIN, user.getUserRole());
        //Promote again to check if there is nothing strange happening
        user.promote();
        assertEquals(UserRole.ADMIN, user.getUserRole());
    }

    /**
     * Test of demote method, of class User.
     */
    @Test
    public void testDemote() {
        User user = new User();
        //Promote twice to admin
        user.promote();
        user.promote();
        
        assertEquals(UserRole.ADMIN, user.getUserRole());
        user.demote();
        assertEquals(UserRole.MODERATOR, user.getUserRole());
        user.demote();
        assertEquals(UserRole.USER, user.getUserRole());
        //Demote again to check if there is nothing strange happening
        user.demote();
        assertEquals(UserRole.USER, user.getUserRole());
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals() {
        User user1 = new User("user1", "");
        User user2 = new User("user1", "");
        User user3 = new User("user2", "");
        
        assertTrue(user1.equals(user2));
        assertFalse(user1.equals(user3));
        
        assertFalse(user1.equals(new Object()));
        
        User nullUser2 = new User();
        assertFalse(user1.equals(nullUser2));
    }

    /**
     * Test of hashCode method, of class User.
     */
    @Test
    public void testHashCode() {

    }

}
