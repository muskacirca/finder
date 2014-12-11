package com.finder.ws;

import com.com.finder.da.configuration.configuration.HibernateSessionFactory;
import com.finder.da.DatabaseAdaptor;
import com.finder.model.User;
import com.finder.model.ws.EAppMessage;
import com.finder.model.ws.EAppResponse;
import com.finder.model.ws.UserResponse;
import com.finder.ws.controler.UserControler;
import org.easymock.IMocksControl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 10/12/12
 * Time: 00:21
 */
public class UserManagementServiceImplTest {

    private UserManagementServiceImpl instance = new UserManagementServiceImpl();

    private IMocksControl mocksControl = createControl();
    private UserControler userControler = mocksControl.createMock(UserControler.class);
    private DatabaseAdaptor databaseAdaptor = mocksControl.createMock(DatabaseAdaptor.class);
    private SessionFactory sessionFactory = mocksControl.createMock(SessionFactory.class);
    private Session session = mocksControl.createMock(Session.class);
    private Transaction transaction = mocksControl.createMock(Transaction.class);

    @Before
    public void setUp() throws Exception {

        mocksControl.reset();
        HibernateSessionFactory.setInstance(sessionFactory);
        instance.setDatabaseAdaptor(databaseAdaptor);
        instance.setUserControler(userControler);
    }

    @After
    public void tearDown() throws Exception {

        mocksControl.verify();
    }

    @Test
    public void testSubscribe_OK() throws Exception {

        User user = new User();
        user.setLogin("login");
        user.setEmail("email");

        commonHibernateExpect();
        expect(userControler.isUserEligibleToSubscription(session, user)).andReturn(EAppMessage.SUCCESS);
        databaseAdaptor.saveUser(session, user);

        mocksControl.replay();

        UserResponse expected = new UserResponse();
        expected.setWsResponse(EAppResponse.SUCCESS);
        expected.setUser(user);

        UserResponse actual = instance.subscribe(user);
        assertEquals(expected.getWsResponse(), actual.getWsResponse());
    }

    private void commonHibernateExpect() {

        expect(sessionFactory.openSession()).andReturn(session);
        expect(session.beginTransaction()).andReturn(transaction);
        transaction.commit();
        expect(session.close()).andReturn(null);
    }

    @Test
    public void testAuthenticate_OK() throws Exception {

        expect(sessionFactory.openSession()).andReturn(session);
        expect(session.close()).andReturn(null);

        expect(databaseAdaptor.authenticate(session, "login", "password")).andReturn(true);

        mocksControl.replay();

        UserResponse actual = instance.authenticate("login", "password");

        verifyWSResponse(actual, EAppMessage.SUCCESS);
    }

    @Test
    public void testAuthenticate_KO() throws Exception {

        expect(sessionFactory.openSession()).andReturn(session);
        expect(session.close()).andReturn(null);

        expect(databaseAdaptor.authenticate(session, "login", "wrong_password")).andReturn(false);

        mocksControl.replay();

        UserResponse actual = instance.authenticate("login", "wrong_password");

        verifyWSResponse(actual, EAppMessage.AUTHENTICATION_FAILED);
    }

    private void verifyWSResponse(UserResponse actual, EAppMessage appMessage) {

        UserResponse expected = new UserResponse();
        if (appMessage == EAppMessage.SUCCESS) {
            expected.setWsResponse(EAppResponse.SUCCESS);
        } else {
            expected.setWsResponse(EAppResponse.ERROR);
        }
        expected.setErrorCode(appMessage.getErrorCode());
        expected.setErrorMessage(appMessage.getErrorMessage());

        assertEquals(expected.getWsResponse(), actual.getWsResponse());
        assertEquals(expected.getErrorCode(), actual.getErrorCode());
        assertEquals(expected.getErrorMessage(), actual.getErrorMessage());
        assertNull(actual.getUser());
    }

    @Test
    public void testBuildResponse_Success() {

        User user = buildUser();

        UserResponse expected = buildResponse(user);
        expected.setWsResponse(EAppResponse.SUCCESS);

        mocksControl.replay();

        UserResponse actual = instance.buildResponse(user, EAppMessage.SUCCESS);

        assertEquals(EAppMessage.SUCCESS.getErrorCode(), actual.getErrorCode());
        assertEquals(EAppMessage.SUCCESS.getErrorMessage(), actual.getErrorMessage());
        assertEquals(EAppResponse.SUCCESS, actual.getWsResponse());
    }

    @Test
    public void testBuildResponse_ExistingLogin() {

        User user = buildUser();

        mocksControl.replay();

        UserResponse actual = instance.buildResponse(user, EAppMessage.LOGIN_ALREADY_EXIST);

        assertEquals(EAppMessage.LOGIN_ALREADY_EXIST.getErrorCode(), actual.getErrorCode());
        assertEquals(EAppMessage.LOGIN_ALREADY_EXIST.getErrorMessage(), actual.getErrorMessage());
        assertEquals(EAppResponse.ERROR, actual.getWsResponse());
    }

    @Test
    public void testBuildResponse_ExistingEmail() {

        User user = buildUser();

        mocksControl.replay();

        UserResponse actual = instance.buildResponse(user, EAppMessage.EMAIL_ALREADY_EXIST);

        assertEquals(EAppMessage.EMAIL_ALREADY_EXIST.getErrorCode(), actual.getErrorCode());
        assertEquals(EAppMessage.EMAIL_ALREADY_EXIST.getErrorMessage(), actual.getErrorMessage());
        assertEquals(EAppResponse.ERROR, actual.getWsResponse());
    }

    private UserResponse buildResponse(User user) {
        UserResponse expected = new UserResponse();
        expected.setUser(user);
        return expected;
    }

    private User buildUser() {
        User user = new User();
        user.setLogin("login");
        user.setEmail("email");
        user.setPassword("password");
        return user;
    }
}
