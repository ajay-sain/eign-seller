package api.yourmart.servicies;

import org.hibernate.SessionFactory;
//import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import api.yourmart.models.Product;
import api.yourmart.models.Seller;


public class HibernetConnector {
	private static SessionFactory sessionFactory;
	private static void setUp(){
		Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(Seller.class);
        ServiceRegistry srvcReg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory( srvcReg);
	}
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null ) {
			try {
				setUp();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sessionFactory;
	}
}
