package domains;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by User on 03.11.2016.
 */
@Entity
public class GoodsName {
    @GeneratedValue
    @Id
    private Long id;
    @NotNull
    @ManyToOne
    private Orders order;
    @NotEmpty
    private String goodsname;

    private Integer quantity;

    public GoodsName() {
    }

    public GoodsName(Orders order, String goodsname, Integer quantity) {
        this.order = order;
        this.goodsname = goodsname;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
