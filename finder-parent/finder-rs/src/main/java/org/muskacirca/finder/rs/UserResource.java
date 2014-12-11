package org.muskacirca.finder.rs;

import com.com.finder.da.configuration.configuration.HibernateSessionFactory;
import com.finder.da.DatabaseAdaptor;
import com.finder.model.User;
import com.finder.model.ws.EAppMessage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.muskacirca.finder.rs.controler.UserControler;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by muskacirca on 03/11/2014.
 */
@Path("/user")
public class UserResource {

    private static final Logger LOG = Logger.getLogger(UserResource.class);

    private DatabaseAdaptor databaseAdaptor = new DatabaseAdaptor();
    private UserControler userControler = new UserControler();


    @GET
    @Path("/{id}")
    @Produces("application/json")
    public User getUser(@PathParam("id") String id) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        LOG.debug("About to retrieve user with" + id);

        Session session = openSession();
        return databaseAdaptor.getUserById(session, Integer.parseInt(id));
    }

    @POST
    @Path("/signin")
    @Consumes("application/json")
    public Response signIn(@PathParam("user") User user) {

        LOG.debug("About to registered user " + user);

        Response userResponse = null;

        Session session = openSession();

        Transaction transaction = session.beginTransaction();
        try {

            EAppMessage appMessage = userControler.isUserEligibleToSubscription(session, user);
            if (appMessage == EAppMessage.SUCCESS) {

                databaseAdaptor.saveUser(session, user);
                transaction.commit();
                userResponse = buildResponse(user, Response.ok(), appMessage);

                LOG.debug("Successfully registered user : " + user);

            } else {
                LOG.debug("Subscription failed for user : " + user);
                userResponse = buildResponse(user, Response.ok(), appMessage);
            }

        } catch (Exception e) {
            LOG.error("Subscription failed for user :" + user + " due to an internal error. " + e.getMessage(), e);
            if (transaction.isActive()) {
                transaction.rollback();
                userResponse = buildResponse(user, Response.status(Response.Status.INTERNAL_SERVER_ERROR), EAppMessage.EXCEPTION);
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }

        return userResponse;
    }

    @GET
    @Path("/{login}/{password}")
    @Produces("application/json")
    public Response authenticate(@PathParam("login") String login, @PathParam("password") String password) {

        LOG.debug("About to signup user " + login);
        Response response = null;

        Session session = openSession();
        try {

            if (databaseAdaptor.authenticate(session, login, password)) {

                response = buildResponse(Response.ok(), EAppMessage.SUCCESS);
                LOG.debug(login + " is now authenticated");

            } else {

                response = buildResponse(Response.status(Response.Status.FORBIDDEN), EAppMessage.AUTHENTICATION_FAILED);
                LOG.debug("Authentication failed for user " + login);
            }

        } catch (Exception e) {

            LOG.error("Authentication failed for user : " + login + " due to an internal exception. " + e.getMessage(), e);
            response = buildResponse(Response.status(Response.Status.INTERNAL_SERVER_ERROR), EAppMessage.EXCEPTION);

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

    protected Response buildResponse(Response.ResponseBuilder status, EAppMessage message) {
        return buildResponse(null, status, message);
    }

    protected Response buildResponse(User user, Response.ResponseBuilder status,  EAppMessage message) {

        Response.ResponseBuilder response = status;
        response.entity(message.name());

        if(user != null) response.entity(user);

        return response.build();
    }


    public void setDatabaseAdaptor(DatabaseAdaptor databaseAdaptor) {
        this.databaseAdaptor = databaseAdaptor;
    }

    public void setUserControler(UserControler userControler) {
        this.userControler = userControler;

    }

}
