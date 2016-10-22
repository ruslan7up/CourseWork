package data;

import domains.Goods;

import java.util.List;

/**
 * Created by ruslan on 01.10.2016.
 */
public interface GoodsService {
    public List<Goods> getGoodsByID(long id);
    public List<Goods> getGoodsByName(String name);
    public List<Goods> getAllGoods();
    public boolean removeGoods(long id) throws Exception;
    public boolean addGoods(int vc,String category,String name, long quantity, double retailPrice, double wholesalePrice);
}
