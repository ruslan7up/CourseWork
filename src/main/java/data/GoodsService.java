package data;

import domains.Goods;

import java.util.List;

/**
 * Created by ruslan on 01.10.2016.
 */
public interface GoodsService {
    public Goods getGoodsByID(long id);
    public Goods getGoodsByName(String name);
    public List<Goods> getAllGoods();
}
