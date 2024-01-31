
package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.config.SessionFactoryConfig;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.entity.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl<T,ID> implements OrderDAO<Order,String> {
    @Override
    public boolean save(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfig.getSession();
        try {
            Order order = session.get(Order.class, id);
            session.close();
            return order;
        } catch (Exception e) {
            System.out.println("Error Search Order: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfig.getSession();
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Query<Order> query = session.createQuery("SELECT O FROM Order AS O");
            orders.addAll(query.getResultList());
            session.close();
            return orders;
        } catch (Exception e) {
            System.out.println("Error load all orders: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }
}

