package lk.ijse.pos.config;

import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class SessionFactoryConfig {
    private static SessionFactory sessionFactory;
    static {
        sessionFactory = new MetadataSources(new StandardServiceRegistryBuilder().loadProperties("hibernate.cfg.properties").build())
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderDetails.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build()
                .buildSessionFactory();
    }

    private SessionFactoryConfig() {
    }
    public static SessionFactory getInstance(){
        return sessionFactory;
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
