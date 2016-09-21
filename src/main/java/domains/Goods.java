package domains;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by admin on 20.09.2016.
 */
@Entity
public class Goods {
    @Id
    private long id;
    private String category;
    private String name;
    private long quantity;
    private double retailPrice;
    private double wholesalePrice;
}
