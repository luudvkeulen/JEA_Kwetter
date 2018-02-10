package domain;

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
     * Test of like method, of class Tweet.
     */
    @org.junit.Test
    public void testLike() {
        System.out.println("like");
        User user = null;
        Tweet instance = new Tweet();
        boolean expResult = false;
        boolean result = instance.like(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unlike method, of class Tweet.
     */
    @org.junit.Test
    public void testUnlike() {
        System.out.println("unlike");
        User user = null;
        Tweet instance = new Tweet();
        boolean expResult = false;
        boolean result = instance.unlike(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
