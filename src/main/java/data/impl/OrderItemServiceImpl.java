package data.impl;

import data.OrderItemsService;
import domains.GoodsName;

import java.util.List;

/**
 * Created by ruslan on 08.11.2016.
 */
public class OrderItemServiceImpl implements OrderItemsService {
    @Override
    public List<GoodsName> getItemByOrderID(Long id) {
        return null;
    }

    @Override
    public boolean addItem(GoodsName item) {
        return false;
    }

    @Override
    public boolean removeItemsByOrderID(Long id) {
        return false;
    }
}
