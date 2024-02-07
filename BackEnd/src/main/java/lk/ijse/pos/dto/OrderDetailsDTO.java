package lk.ijse.pos.dto;

import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    private String oid;
    private String itmCode;
    private int itmQTY;
    private double itmPrice;

    public OrderDetails toEntity(){
        OrderDetails details = new OrderDetails();
        Item item = new Item();
        item.setItmCode(this.itmCode);
        item.setItmPrice(this.itmPrice);
        details.setItem(item);
        details.setItmQTY(this.itmQTY);

        Order order = new Order();
        order.setOid(this.oid);
        details.setOrder(order);
        return details;
    }
}
