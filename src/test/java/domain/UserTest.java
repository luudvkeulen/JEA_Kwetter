package domain;

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

    /**
     * Test of follow method, of class User.
     */
    @Test
    public void testFollow() {
        System.out.println("follow");
        User user = null;
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.follow(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unfollow method, of class User.
     */
    @Test
    public void testUnfollow() {
        System.out.println("unfollow");
        User user = null;
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.unfollow(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of promote method, of class User.
     */
    @Test
    public void testPromote() {
        System.out.println("promote");
        User instance = new User();
        UserRole expResult = null;
        UserRole result = instance.promote();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of demote method, of class User.
     */
    @Test
    public void testDemote() {
        System.out.println("demote");
        User instance = new User();
        UserRole expResult = null;
        UserRole result = instance.demote();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFollower method, of class User.
     */
    @Test
    public void testAddFollower() {
        System.out.println("addFollower");
        User user = null;
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.addFollower(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class User.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        User instance = new User();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
