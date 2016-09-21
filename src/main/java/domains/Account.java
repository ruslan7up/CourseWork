package domains;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by admin on 14.09.2016.
 */
@Entity
public class Account{
    @Id
    private Long id;
    private String login;
    private String pass;
    public Account(Long id, String login, String pass) {
        this.id = id;
        this.login = login;
        this.pass = pass;
    }

    public Account() {
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