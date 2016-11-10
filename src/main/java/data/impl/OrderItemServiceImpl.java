package data.impl;

import data.OrderItemsService;
import domains.GoodsName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by ruslan on 08.11.2016.
 */
@Repository
public class OrderItemServiceImpl implements OrderItemsService {
    private Session session;

    public OrderItemServiceImpl(SessionFactory session) {
        this.session = session.openSession();
    }

    @Override
    public List<GoodsName> getItemByOrderID(Long id) {
        Query query = session.createQuery("FROM GoodsName where order_id=:OrderID");
        query.setParameter("OrderID",id);
        List<GoodsName> list = query.getResultList();
        if(!list.isEmpty())
        {
         return list;
        } else {
            return null;
        }
    }

    @Override
    public boolean addItem(GoodsName item) {
        Transaction tr = session.getTransaction();
        try
        {
            tr.begin();
            session.save(item);
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
    public boolean removeItemsByOrderID(Long id) {
        Transaction tr = session.getTransaction();
        try
        {
            tr.begin();
            Query query = session.createQuery("DELETE GoodsName WHERE order_id=:OrderID");
            query.setParameter("OrderID",id);
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
}
