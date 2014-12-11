package com.finder.ws;

import com.com.finder.da.configuration.configuration.HibernateSessionFactory;
import com.finder.da.DatabaseAdaptor;
import com.finder.model.User;
import com.finder.model.ws.EAppMessage;
import com.finder.model.ws.EAppResponse;
import com.finder.model.ws.UserResponse;
import com.finder.ws.controler.UserControler;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.jws.WebService;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 08/12/12
 * Time: 20:31
 */
@WebService(endpointInterface = "com.finder.ws.UserManagementService",
        serviceName = "UserManagement", portName = "UserManagementPort")
public class UserManagementServiceImpl implements UserManagementService {

    private static final Logger LOG = Logger.getLogger(UserManagementServiceImpl.class);

    private DatabaseAdaptor databaseAdaptor = new DatabaseAdaptor();
    private UserControler userControler = new UserControler();

    @Override
    public UserResponse subscribe(User user) {

        LOG.debug("About to registered user " + user);

        UserResponse userResponse = null;

        Session session = openSession();

        Transaction transaction = session.beginTransaction();
        try {

            EAppMessage appMessage = userControler.isUserEligibleToSubscription(session, user);
            if (appMessage == EAppMessage.SUCCESS) {

                databaseAdaptor.saveUser(session, user);
                transaction.commit();
                userResponse = buildResponse(user, appMessage);

                LOG.debug("Successfully registered user : " + user);

            } else {
                LOG.debug("Subscription failed for user : " + user);
                userResponse = buildResponse(user, appMessage);
            }

        } catch (Exception e) {
            LOG.error("Subscription failed for user :" + user + " due to an internal error. " + e.getMessage(), e);
            if (transaction.isActive()) {
                transaction.rollback();
                userResponse = buildResponse(user, EAppMessage.EXCEPTION);
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }

        return userResponse;
    }

    @Override
    public UserResponse authenticate(String login, String password) {

        LOG.debug("About to signup user " + login);
        UserResponse response = null;

        Session session = openSession();
        try {

            if (databaseAdaptor.authenticate(session, login, password)) {

                response = buildResponse(EAppMessage.SUCCESS);
                LOG.debug(login + " is now authenticated");

            } else {

                response = buildResponse(EAppMessage.AUTHENTICATION_FAILED);
                LOG.debug("Authentication failed for user " + login);
            }

        } catch (Exception e) {

            LOG.error("Authentication failed for user : " + login + " due to an internal exception. " + e.getMessage(), e);
            response = buildResponse(EAppMessage.EXCEPTION);

        } finally {
            if (session != null) {
                session.close();
            }
        }


        return response;
    }

    private Session openSession() {
        SessionFactory sessionFactory = HibernateSessionFactory.getInstance();
        return sessionFactory.openSession();
    }

    UserResponse buildResponse(EAppMessage appMessage) {
        return buildResponse(null, appMessage);
    }

    UserResponse buildResponse(User user, EAppMessage appMessage) {

        UserResponse userResponse = new UserResponse();
        userResponse.setErrorCode(appMessage.getErrorCode());
        userResponse.setErrorMessage(appMessage.getErrorMessage());

        EAppResponse wsResponse = EAppResponse.ERROR;
        if (appMessage == EAppMessage.SUCCESS) {
            wsResponse = EAppResponse.SUCCESS;
        }
        userResponse.setWsResponse(wsResponse);
        userResponse.setUser(user);

        return userResponse;
    }


    public void setDatabaseAdaptor(DatabaseAdaptor databaseAdaptor) {
        this.databaseAdaptor = databaseAdaptor;
    }

    public void setUserControler(UserControler userControler) {
        this.userControler = userControler;
    }
}
