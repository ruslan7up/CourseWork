package data;

import domains.Orders;

import java.util.List;

/**
 * Created by User on 27.10.2016.
 */
public interface OrderService {
    public List<Orders> getallOrders();
    public List<Orders> getOrderbyId(Long id);
    public boolean addOrder();
    public boolean removeOrder();
}
