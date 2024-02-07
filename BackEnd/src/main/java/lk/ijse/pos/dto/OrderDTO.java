package lk.ijse.pos.dto;

import lk.ijse.pos.embedded.OrderDetailPK;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String oid;
    private LocalDate date;
    private String cusID;
    List<OrderDetailsDTO> orderDetails;

    public OrderDTO(String oid, LocalDate date, String cusID) {
        this.oid = oid;
        this.date = date;
        this.cusID = cusID;
    }

    public Order toEntity() {
        Order order = new Order();
        order.setOid(this.oid);
        order.setDate(this.date);
        Customer customer = new Customer();
        customer.setCusID(this.cusID);
        order.setCustomer(customer);
        System.out.println(orderDetails);
        List<OrderDetails> list = new ArrayList<>();
        for (OrderDetailsDTO or: orderDetails) {
            OrderDetailPK orderDetailPK = new OrderDetailPK(or.getOid(), or.getItmCode());
            Order order1 = new Order();
            order1.setOid(or.getOid());
            Item item = new Item();
            item.setItmCode(or.getItmCode());
            list.add(new OrderDetails(orderDetailPK,or.getItmQTY(),order1,item));
        }
        order.setOrderDetails(list);
        return order;
    }
}
