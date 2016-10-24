package domains;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ruslan on 24.10.2016.
 */
@Entity
public class Shop {
    @Id
    private Long id;
    @NotEmpty
    private String shopname;
    @NotEmpty
    private String address;
    @NotEmpty
    private String phonenumber;

    public Shop() {
    }

    public Shop(Long id, String shopname, String address, String phonenumber) {
        this.id = id;
        this.shopname = shopname;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
