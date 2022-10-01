package com.sda.mierloiubogdan.petclinic.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public abstract class AbstractSessionManager
{
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
    private static final String DATABASE_CURRENT_SESSION_CONTEXT_CLASS = "thread";

    private static final String DATABASE_HOST = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";


    private static final String DATABASE_SHOW_SQL = "true";

    //private static final String DATABASE_HBM2DDL_AUTO = "create-drop";
    private static final String DATABASE_HBM2DDL_AUTO = "update";

    private SessionFactory sessionFactory;

    protected abstract void setAnnotatedClasses(Configuration configuration);

    protected SessionFactory getSessionFactory(String databaseName)
    {
        if (sessionFactory == null)
        {
            try
            {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DATABASE_DRIVER);

                settings.put(Environment.URL, DATABASE_HOST.concat(databaseName));
                settings.put(Environment.USER, DATABASE_USERNAME);
                settings.put(Environment.PASS, DATABASE_PASSWORD);

                settings.put(Environment.DIALECT, DATABASE_DIALECT);

                settings.put(Environment.SHOW_SQL, DATABASE_SHOW_SQL);
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, DATABASE_CURRENT_SESSION_CONTEXT_CLASS);
                settings.put(Environment.HBM2DDL_AUTO, DATABASE_HBM2DDL_AUTO);

                configuration.setProperties(settings);

                setAnnotatedClasses(configuration);

                ServiceRegistry serviceRegistry =
                        new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties())
                                .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public void shutdownSessionManager()
    {
        // Close caches and connection pools
        if (sessionFactory != null)
        {
            sessionFactory.close();
        }
        sessionFactory = null;
    }
}
