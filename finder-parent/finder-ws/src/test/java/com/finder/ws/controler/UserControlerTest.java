package com.finder.ws.controler;

import com.finder.da.DatabaseAdaptor;
import com.finder.da.model.UserDAO;
import com.finder.model.User;
import com.finder.model.ws.EAppMessage;
import org.easymock.IMocksControl;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 09/12/12
 * Time: 23:59
 */
public class UserControlerTest {

    private UserControler userControler = new UserControler();

    private IMocksControl mockControls = createControl();
    private DatabaseAdaptor databaseAdaptor = mockControls.createMock(DatabaseAdaptor.class);
    private Session session = mockControls.createMock(Session.class);

    @Before
    public void setUp() throws Exception {

        mockControls.reset();
        userControler.setDatabaseAdaptor(databaseAdaptor);
    }

    @After
    public void tearDown() throws Exception {

        mockControls.verify();
    }

    @Test
    public void testIsUserEligibleToSubscription_OK() throws Exception {

        User user = new User();
        user.setLogin("login");
        user.setEmail("email");

        expect(databaseAdaptor.findOneByProperty(session, UserDAO.class, "login", "login")).andReturn(null);
        expect(databaseAdaptor.findOneByProperty(session, UserDAO.class, "email", "email")).andReturn(null);

        mockControls.replay();

        assertEquals(EAppMessage.SUCCESS, userControler.isUserEligibleToSubscription(session, user));
    }

    @Test
    public void testIsUserEligibleToSubscription_login_exist() throws Exception {

        UserDAO userDAO = new UserDAO();
        userDAO.setName("login");
        userDAO.setMail("email");

        User user = new User();
        user.setLogin("login");
        user.setEmail("email");

        expect(databaseAdaptor.findOneByProperty(session, UserDAO.class, "login", user.getLogin())).andReturn(userDAO);

        mockControls.replay();

        assertEquals(EAppMessage.LOGIN_ALREADY_EXIST, userControler.isUserEligibleToSubscription(session, user));
    }

    @Test
    public void testIsUserEligibleToSubscription_email_exist() throws Exception {

        UserDAO userDAO = new UserDAO();
        userDAO.setName("login");
        userDAO.setMail("email");

        User user = new User();
        user.setLogin("login");
        user.setEmail("email");

        expect(databaseAdaptor.findOneByProperty(session, UserDAO.class, "login", user.getLogin())).andReturn(null);
        expect(databaseAdaptor.findOneByProperty(session, UserDAO.class, "email", user.getEmail())).andReturn(userDAO);

        mockControls.replay();

        assertEquals(EAppMessage.EMAIL_ALREADY_EXIST, userControler.isUserEligibleToSubscription(session, user));
    }
}
