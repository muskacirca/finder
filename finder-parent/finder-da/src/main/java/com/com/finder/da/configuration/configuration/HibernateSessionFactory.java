package com.com.finder.da.configuration.configuration;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 06/12/12
 * Time: 01:21
 */
public class HibernateSessionFactory {

    private static SessionFactory instance;
    private static ServiceRegistry serviceRegistry;

    public HibernateSessionFactory() {
    }

    public static SessionFactory getInstance() {

        if(instance == null) {
            configure();
        }
        return instance;
    }


    private static void configure() {

        Configuration configuration = new Configuration();
        configuration.configure();

        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        instance = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void setInstance(SessionFactory newInstance) {
        instance = newInstance;
    }

}
