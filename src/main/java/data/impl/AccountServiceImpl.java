package data.impl;

import data.AccountService;
import domains.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15.09.2016.
 */
@Repository
public class AccountServiceImpl implements AccountService {
    private Session session;

    @Autowired
    public AccountServiceImpl(SessionFactory factory) {
        session = factory.openSession();
    }


    @Override
    public Account getAccountByID(long id) {
        Query query = session.createQuery("FROM Account WHERE id=:AccountID");
        query.setParameter("AccountID",id);
        List<Account> list = query.list();
        if(!list.isEmpty())
        {
            return list.get(0);
        } else
        {
            return null;
        }
    }

    @Override
    public Account getAccountByLogin(String name) {
        Query query = session.createQuery("FROM Account WHERE login=:AccountLogin");
        query.setParameter("AccountLogin",name);
        List<Account> list = query.list();
        if(!list.isEmpty())
        {
            return list.get(0);
        } else
        {
            return null;
        }

    }

    @Override
    public List<Account> getAllUsers() {
        Query query = session.createQuery("FROM Account");
        List<Account> list = query.list();
        return list;
    }

    @Override
    public void removeAccount(long id) {
        Query query = session.createQuery("DELETE from Account WHERE id=:AccountID");
        query.setParameter("AccountID",id);
    }

    @Override
    public void addAccount(long login, String pass) {

    }
}
