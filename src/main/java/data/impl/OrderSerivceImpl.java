package data.impl;

import data.OrderService;
import domains.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Created by User on 27.10.2016.
 */
@Repository
public class OrderSerivceImpl implements OrderService {

    private Session session;
    @Autowired
    public OrderSerivceImpl(SessionFactory session) {
        this.session = session.openSession();
    }

    @Override
    public List<Orders> getallOrders() {
        Query query = session.createQuery("FROM Orders");
        List<Orders> ordersList = query.list();
        Collections.reverse(ordersList);
        if(!ordersList.isEmpty())
        {
            return ordersList;
        }
        return null;
    }

    @Override
    public List<Orders> getOrderbyId(Long id) {
        Query query =session.createQuery("FROM Orders WHERE id=:ByID");
        query.setParameter("ByID",id);
        List<Orders> ordersList = query.list();
        if(!ordersList.isEmpty())
        {
            return ordersList;
        }
        return null;
    }

    @Override
    public boolean addOrder(Orders order) {
        Transaction tr = session.getTransaction();
        try
        {
            tr.begin();
            session.save(order);
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
    public boolean removeOrder(Long orderid) {
        Transaction tr = session.getTransaction();
        try
        {
            tr.begin();
            Query query = session.createQuery("DELETE Orders WHERE id=:OrderID");
            query.setParameter("OrderID",orderid);
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
    public boolean editOrder(Orders order) {
        Transaction tr = session.getTransaction();
        try
        {
            tr.begin();
            session.merge(order);
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
