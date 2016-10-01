package data.impl;

import data.GoodsService;
import domains.Goods;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Goods getGoodsByID(long id) {
        Query query = session.createQuery("FROM Goods WHERE id=:GoodsID");
        query.setParameter("GoodsID",id);
        List<Goods> list = query.list();
        if(!list.isEmpty())
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Goods getGoodsByName(String name) {
        Query query = session.createQuery("FROM Goods WHERE name=:GoodsName");
        query.setParameter("GoodsName",name);
        List<Goods> list = query.list();
        if(!list.isEmpty())
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Goods> getAllGoods() {
        Query query = session.createQuery("FROM Goods");
        List<Goods> list = query.list();
        return list;
    }
}
