package domains;



import javax.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by admin on 14.09.2016.
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"login"})})

public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String login;
    @NotEmpty
    private String pass;
    @NotEmpty
    private String fullname;
    public Account(Long id, String login, String pass,String fullname) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.fullname = fullname;
    }

    public Account() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}