package data;

import domains.Shop;

import java.util.List;

/**
 * Created by ruslan on 24.10.2016.
 */
public interface ShopService {
    public List<Shop> getAllShops();
    public List<Shop> getShopByID(long id);
    public List<Shop> getShopByName(String name);
    public boolean removeShop(long id);
    public boolean addShop(Long id,String shopname,String address,String phonenumber);
}
