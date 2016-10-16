package data;

import domains.Account;

import java.util.List;

/**
 * Created by admin on 15.09.2016.
 */
public interface AccountService {
    public Account getAccountByID(long id);
    public Account getAccountByLogin(String name);
    public List<Account> getAllUsers();
    public void removeAccount(long id);
    public void addAccount(long login,String pass);
}
