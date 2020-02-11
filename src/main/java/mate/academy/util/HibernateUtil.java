package mate.academy.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    //XML based configuration
    private static SessionFactory sessionFactory = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Error creating session factory", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
