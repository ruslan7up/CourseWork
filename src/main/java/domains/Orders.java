package domains;



import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by User on 27.10.2016.
 */
@Entity
public class Orders {
    @GeneratedValue
    @Id
    private Long id;
    @NotEmpty
    private String status;


    @OneToMany(mappedBy = "order")
    private List<GoodsName> orderUnitList;
    @NotNull
    private LocalDate date;
    public Orders() {
    }

    public Orders(List<GoodsName> orderUnitList, LocalDate date) {
        this.orderUnitList = orderUnitList;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GoodsName> getOrderUnitList() {
        return orderUnitList;
    }

    public void setOrderUnitList(List<GoodsName> orderUnitList) {
        this.orderUnitList = orderUnitList;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
