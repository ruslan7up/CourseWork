package data.impl;

import data.GoodsService;
import domains.Account;
import domains.Goods;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ruslan on 01.10.2016.
 */
@Repository
public class GoodsServiceImpl implements GoodsService{

    private Session session;

    @Autowired
    public GoodsServiceImpl(SessionFactory session) {
        this.session = session.openSession();
    }

    @Override
    public List<Goods> getGoodsByID(long id) {
        Query query = session.createQuery("FROM Goods WHERE id=:GoodsID");
        query.setParameter("GoodsID",id);
        List<Goods> list = query.list();
        if(!list.isEmpty())
        {
            return list;
        }
        return null;
    }

    @Override
    public List<Goods> getGoodsByName(String name) {
        Query query = session.createQuery("FROM Goods WHERE name=:GoodsName");
        query.setParameter("GoodsName",name);
        List<Goods> list = query.list();
        if(!list.isEmpty())
        {
            return list;
        }
        return null;
    }

    @Override
    public List<Goods> getAllGoods() {
        Query query = session.createQuery("FROM Goods");
        List<Goods> list = query.list();
        return list;
    }

    @Override
    public boolean removeGoods(long id) {
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            Query query = session.createQuery("Delete Goods WHERE id=:GoodsID");
            query.setParameter("GoodsID", id);
            query.executeUpdate();
            tr.commit();
            return true;
        } catch (Exception e)
        {
            tr.rollback();
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addGoods(int vc,String category, String name, long quantity, double retailPrice, double wholesalePrice) {
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            Goods goods = new Goods();
            goods.setId(vc);
            goods.setCategory(category);
            goods.setName(name);
            goods.setQuantity(quantity);
            goods.setRetailPrice(retailPrice);
            goods.setWholesalePrice(wholesalePrice);
            session.save(goods);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            System.out.println(e.getMessage());
            return false;
        }
    }
}
