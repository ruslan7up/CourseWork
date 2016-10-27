package data.impl;

import data.OrderService;
import domains.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 27.10.2016.
 */
@Repository
public class OrderSerivceImpl implements OrderService {
    @Override
    public List<Orders> getallOrders() {
        return null;
    }

    @Override
    public List<Orders> getOrderbyId(Long id) {
        return null;
    }

    @Override
    public boolean addOrder() {
        return false;
    }

    @Override
    public boolean removeOrder() {
        return false;
    }
}
