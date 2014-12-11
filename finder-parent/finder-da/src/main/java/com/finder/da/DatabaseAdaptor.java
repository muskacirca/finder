package com.finder.da;


import com.finder.da.model.UserDAO;
import com.finder.model.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 06/12/12
 * Time: 02:00
 */

public class DatabaseAdaptor {

    private static final Logger LOG = Logger.getLogger(DatabaseAdaptor.class);


    public User getUserById(Session session, int id) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        UserDAO userDAO = (UserDAO) session.get(UserDAO.class, id);
        return (User) mapDAOBean(userDAO, User.class);
    }

    Object mapDAOBean(Object origin, Class destination) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        Object dest = destination.newInstance();
        BeanUtils.copyProperties(dest, origin);
        return dest;
    }

    public boolean hasUserAlreadySubscribed(Session session, com.finder.model.User user) {

        UserDAO existingUser = (UserDAO) session.createCriteria(UserDAO.class)
                .add(Restrictions.or(
                        Restrictions.eq("name", user.getLogin()),
                        Restrictions.eq("mail", user.getEmail()))).uniqueResult();

        if (existingUser == null) {
            return false;
        }

        return true;
    }

    public boolean authenticate(Session session, String name, String password) {

        UserDAO userDAO = (UserDAO) findOneByProperty(session, UserDAO.class, "name", name);

        if (userDAO == null) return false;

        return userDAO.getPassword().equals(password);
    }

    public Object findOneByProperty(Session session, Class clazz, String propertyName, String value) {

        LOG.debug("loading " + clazz.getName() + " with property " + propertyName + "=" + value);
        Object object = session.createCriteria(clazz).add(Restrictions.eq(propertyName, value)).uniqueResult();
        if (object != null) {
            LOG.debug("Found one object : " + object);
        }

        return object;
    }

    public void saveUser(Session session, User user) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        UserDAO userDAO = (UserDAO) mapDAOBean(user, UserDAO.class);
        session.save(userDAO);
    }
}
