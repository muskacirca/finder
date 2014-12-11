package com.finder.da;

import com.com.finder.da.configuration.configuration.HibernateSessionFactory;
import com.finder.da.model.UserDAO;
import com.finder.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 06/12/12
 * Time: 02:13
 */
@Ignore
public class DatabaseAdaptorTest {


    private DatabaseAdaptor databaseAdaptor;
    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeClass
    public static void setUpClass() {
        sessionFactory = HibernateSessionFactory.getInstance();
    }

    @Before
    public void setUp() {
        // Injector injector = Guice.createInjector(new DatabaseModule());
        // databaseAdaptor = injector.getInstance(DatabaseAdaptor.class);
        databaseAdaptor = new DatabaseAdaptor();
        session = sessionFactory.openSession();
    }

    @After
    public void tearDown() {
        session.close();
    }

    @Test
    public void testUserBeanMapping() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        User user = new User();
        user.setLogin("login");
        user.setEmail("email");
        user.setPassword("password");
        user.setId(1);

        UserDAO userDAO = (UserDAO) databaseAdaptor.mapDAOBean(user, UserDAO.class);

        assertEquals("login", userDAO.getName());
        assertEquals("password", userDAO.getPassword());
        assertEquals("email", userDAO.getMail());
        assertEquals(1, userDAO.getId());
    }

    @Test
    public void testGetUserById() throws Exception {

        User user = databaseAdaptor.getUserById(session, 1);
        assertEquals("muskacirca", user.getLogin());
        assertEquals("1234", user.getPassword());
    }

    @Test
    public void testHasUserAlreadySubscribed_OK() {

        User user = getTestUser();
        assertTrue(databaseAdaptor.hasUserAlreadySubscribed(session, user));
    }

    @Test
    public void testHasUserAlreadySubscribed_KO() {

        User user = new User();
        user.setEmail("different@email.com");
        user.setLogin("differentLogin");
        boolean condition = databaseAdaptor.hasUserAlreadySubscribed(session, user);
        assertFalse(condition);
    }

    @Test
    public void test_authenticate() {

        assertFalse(databaseAdaptor.authenticate(session, "login", "wrong_password"));
        assertTrue(databaseAdaptor.authenticate(session, "login", "password"));
        assertFalse(databaseAdaptor.authenticate(session, null, "password"));
        assertFalse(databaseAdaptor.authenticate(session, "not_a_proper_login", "password"));
        assertFalse(databaseAdaptor.authenticate(session, "login", null));
    }

    @Test
    public void tesFindOneByProperty() {

        UserDAO user = (UserDAO) databaseAdaptor.findOneByProperty(session, UserDAO.class, "login", "login");
        assertEquals("login", user.getName());
        assertEquals("password", user.getPassword());
    }


    private User getTestUser() {
        User user = new User();
        user.setEmail("muskacirca@gmail.com");
        user.setLogin("muskacirca");
        user.setLogin("liberate");
        return user;
    }
}
