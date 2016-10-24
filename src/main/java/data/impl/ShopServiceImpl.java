package data.impl;

import data.ShopService;
import domains.Shop;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ruslan on 24.10.2016.
 */
@Repository
public class ShopServiceImpl implements ShopService {
    private Session session;


    @Autowired
    public ShopServiceImpl(SessionFactory session) {
        this.session = session.openSession();
    }

    @Override
    public List<Shop> getAllShops() {
        Query query = session.createQuery("FROM Shop");
        List<Shop> list = query.list();
        if(!list.isEmpty())
        {
            return list;
        }
        return null;
    }

    @Override
    public Shop getShopByID(long id) {
        Query query = session.createQuery("FROM Shop WHERE id=:ShopID");
        query.setParameter("ShopID",id);
        return (Shop) query.getSingleResult();
    }

    @Override
    public List<Shop> getShopByName(String name) {
        Query query = session.createQuery("FROM Shop WHERE shopname=:ShopName");
        query.setParameter("ShopName",name);
        List<Shop> list = query.list();
        if(!list.isEmpty())
        {
            return list;
        }
        return null;
    }

    @Override
    public boolean removeShop(long id) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Query query = session.createQuery("Delete Shop WHERE id=:ShopID");
            query.setParameter("ShopID",id);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e)
        {
            transaction.rollback();
            return false;
        }

    }

    @Override
    public boolean addShop(String shopname, String address, String phonenumber) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Shop shop = new Shop();
            shop.setShopname(shopname);
            shop.setAddress(address);
            shop.setPhonenumber(phonenumber);
            session.save(shop);
            transaction.commit();
            return true;
        } catch (Exception e)
        {
            transaction.rollback();
            return false;
        }

    }

}
