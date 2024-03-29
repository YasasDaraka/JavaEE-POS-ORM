
package lk.ijse.pos.entity;

import lk.ijse.pos.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "itmCode")
    private String itmCode;
    @Column(name = "itmName")
    private String itmName;
    @Column(name = "itmPrice")
    private double itmPrice;
    @Column(name = "itmQTY")
    private int itmQTY;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "item")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public ItemDTO toDTO(){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItmCode(this.itmCode);
        itemDTO.setItmName(this.itmName);
        itemDTO.setItmPrice(this.itmPrice);
        itemDTO.setItmQTY(this.itmQTY);
        return itemDTO;
    }
}

