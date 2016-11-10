package data.impl;

import data.AccountService;
import domains.Account;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        }
        return null;
    }

    @Override
    public Account getAccountByLogin(String name) {
        Query query = session.createQuery("FROM Account WHERE login=:AccountLogin");
        query.setParameter("AccountLogin", name);
        List<Account> list = query.list();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Account> getAllUsers() {
        Query query = session.createQuery("FROM Account");
        List<Account> list = query.list();
        if(!list.isEmpty()) {
            return list;
        }
        return null;
    }

    @Override
    public boolean removeAccount(long id) {
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            Query query = session.createQuery("Delete Account WHERE id=:AccID");
            query.setParameter("AccID", id);
            query.executeUpdate();
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            return false;
        }
    }

    @Override
    public boolean addAccount(String fullname,String login, String pass) {
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            Account account = new Account();
            account.setFullname(fullname);
            account.setLogin(login);
            account.setPass(DigestUtils.md5Hex(pass));
            session.save(account);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            System.out.println(e.getMessage());
            return false;
        }
    }
}