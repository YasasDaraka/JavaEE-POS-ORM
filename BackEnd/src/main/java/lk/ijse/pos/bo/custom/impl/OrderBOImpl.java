package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.config.SessionFactoryConfig;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDetailsDAO orderDetailsDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        System.out.println("Bo save method");
        Session session = SessionFactoryConfig.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            System.out.println(dto);
            session.save(dto.toEntity());
            for (OrderDetailsDTO dtos: dto.getOrderDetails()) {
                Item item = session.get(Item.class, dtos.getItmCode());
                if (item != null) {
                    int current = item.getItmQTY();
                    int ordered = dtos.getItmQTY();
                    item.setItmQTY(current - ordered);
                } else {
                    System.out.println("Item not found");
                    session.getTransaction().rollback();
                    return false;
                }
                session.update(item);
            }

            transaction.commit();
            System.out.println("order added");
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error add order: " + e.getMessage());
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }


    @Override
    public OrderDTO searchOrder(String id) throws SQLException, ClassNotFoundException {
        Order order = (Order) orderDAO.search(id);
        if (order != null) {
            return order.toDTO();
        }
        return null;
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException {
        ArrayList<Order> all = orderDAO.getAll();
        ArrayList<OrderDTO> orderAr = new ArrayList<>();

        for (Order order : all) {
            OrderDTO dto = order.toDTO();
            orderAr.add(dto);
        }
        return orderAr;
    }

    @Override
    public boolean OrderExist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
