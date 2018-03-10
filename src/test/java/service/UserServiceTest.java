package service;

import dao.UserDAO;
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

public class UserServiceTest {
    
    @Mock
    private UserDAO userDAO;
    
    @InjectMocks
    private UserService userService;
    
    public UserServiceTest() {
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
     * Test of allUsers method, of class UserService.
     */
    @Test
    public void testAllUsers() throws Exception {
        userService.allUsers();
        
        verify(userDAO, times(1)).findAll();
    }

    /**
     * Test of findByUsername method, of class UserService.
     */
    @Test
    public void testFindByUsername() throws Exception {
        userService.findByUsername("user");
        
        verify(userDAO, times(1)).findByUsername("user");
    }

    /**
     * Test of register method, of class UserService.
     */
    @Test
    public void testRegister() throws Exception {
       User user = new User("test@test.com", "test", "1234");
       
       userService.register(user);
       
        verify(userDAO, times(1)).insert(user);
    }

    /**
     * Test of findFollowing method, of class UserService.
     */
    @Test
    public void testFindFollowing() throws Exception {
        userService.findFollowing(1);
        
        verify(userDAO, times(1)).findAllFollowing(1);
    }

    /**
     * Test of findFollowers method, of class UserService.
     */
    @Test
    public void testFindFollowers() throws Exception {
        userService.findFollowers(1);
        
        verify(userDAO, times(1)).findAllFollowers(1);
    }

    /**
     * Test of promote method, of class UserService.
     */
    @Test
    public void testPromote() throws Exception {
        userService.promote(1);
        
        verify(userDAO, times(1)).findById(1);
    }

    /**
     * Test of demote method, of class UserService.
     */
    @Test
    public void testDemote() throws Exception {
        userService.demote(1);
        
        verify(userDAO, times(1)).findById(1);
    }
    
}
