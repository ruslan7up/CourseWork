package domains;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by User on 27.10.2016.
 */
@Entity
public class Orders {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;
    @NotEmpty
    String status;

    public Orders() {
    }
}
