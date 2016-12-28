package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Utils {
	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();;

	public static Session getSession() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		}
		
		Session session = sessionFactory.openSession();
		return session;
	}
}
