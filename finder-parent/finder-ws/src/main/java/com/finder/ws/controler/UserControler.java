package com.finder.ws.controler;

import com.finder.da.DatabaseAdaptor;
import com.finder.da.model.UserDAO;
import com.finder.model.User;
import com.finder.model.ws.EAppMessage;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 09/12/12
 * Time: 22:42
 */
public class UserControler {

    private static final Logger LOG = Logger.getLogger(UserControler.class);

    private DatabaseAdaptor databaseAdaptor = new DatabaseAdaptor();

    public EAppMessage isUserEligibleToSubscription(Session session, User user) {

        UserDAO existingUser = (UserDAO) databaseAdaptor.findOneByProperty(session, UserDAO.class, "login", user.getLogin());
        if (existingUser != null) {
            LOG.debug("User " + user.getLogin() + " already exist");
            return EAppMessage.LOGIN_ALREADY_EXIST;
        }

        existingUser = (UserDAO) databaseAdaptor.findOneByProperty(session, UserDAO.class, "email", user.getEmail());
        if (existingUser != null) {
            LOG.debug("User email " + user.getEmail() + " already exist");
            return EAppMessage.EMAIL_ALREADY_EXIST;
        }

        LOG.debug("User " + user + " is eligible for subscription");
        return EAppMessage.SUCCESS;
    }

    public void setDatabaseAdaptor(DatabaseAdaptor databaseAdaptor) {
        this.databaseAdaptor = databaseAdaptor;
    }
}
