package data;

import domains.GoodsName;

import java.util.List;

/**
 * Created by ruslan on 08.11.2016.
 */
public interface OrderItemsService {
    public List<GoodsName> getItemByOrderID(Long id);
    public boolean addItem(domains.GoodsName item);
    public boolean removeItemsByOrderID(Long id);
}
