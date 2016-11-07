package controllers;

import data.comparators.byID;
import data.comparators.byName;
import data.comparators.byQuantity;
import data.impl.AccountServiceImpl;
import data.impl.GoodsServiceImpl;
import data.impl.OrderSerivceImpl;
import data.impl.ShopServiceImpl;
import domains.Account;
import domains.Goods;
import domains.GoodsName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ruslan on 03.10.2016.
 */
@Controller
@RequestMapping("/table")
public class TableController {
    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    private GoodsServiceImpl goodsService;
    @Autowired
    private ShopServiceImpl shopService;
    @Autowired
    private OrderSerivceImpl orderSerivce;
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getUserTable(@RequestParam Map<String,Object> param, HttpSession hsr)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {
            String id = (String) param.get("byid");
            String login = (String) param.get("byname");
            if(id!=null)
            {
                ModelMap modelMap = new ModelMap();
                Account result = accountService.getAccountByID(Long.parseLong(id));
                if(result!=null) {
                    List<Account> list = new ArrayList<>();
                    list.add(result);
                    modelMap.put("accounts", list);
                    return new ModelAndView("accountsTable", modelMap);
                } else
                {
                    List<Account> list = null;
                    modelMap.put("accounts",list);
                    return new ModelAndView("accountsTable",modelMap);
                }
            } else
            if(login!=null)
            {
                ModelMap modelMap = new ModelMap();
                Account result = accountService.getAccountByLogin(login);
                if(result!=null) {
                    List<Account> list = new ArrayList<>();
                    list.add(accountService.getAccountByLogin(login));
                    modelMap.put("accounts", list);
                    return new ModelAndView("accountsTable", modelMap);
                } else
                {
                    List<Account> list = null;
                    modelMap.put("accounts",list);
                    return new ModelAndView("accountsTable",modelMap);
                }
            }
            if(login==null && id==null)
            {
                ModelMap modelMap = new ModelMap();
                modelMap.put("accounts", accountService.getAllUsers());
                return new ModelAndView("accountsTable",modelMap);
            }
        }
        return new ModelAndView("page403");
    }
    @RequestMapping(value = "/userAdd", method = RequestMethod.GET)
    public void addUser(@RequestParam Map<String, Object> param, HttpSession hsr, HttpServletResponse response)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {
            String fullname = (String) param.get("fn");
            String login = (String) param.get("login");
            String pass = (String) param.get("password");
            if(!login.isEmpty() && !login.isEmpty() && !fullname.isEmpty())
            {
                boolean addStatus;
                addStatus=accountService.addAccount(fullname,login, pass);
                if(addStatus==true)
                {
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @RequestMapping(value = "/userRemove", method = RequestMethod.GET)
    public void removeUser(@RequestParam String param,HttpSession hsr, HttpServletResponse response)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {
            int id = Integer.parseInt(param);
            boolean removeStatus;
            removeStatus=accountService.removeAccount(id);
            if(removeStatus==true)
            {
                response.setStatus(HttpServletResponse.SC_OK);
            } else
            {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public ModelAndView getGoodsTable(@RequestParam Map<String,Object> param,HttpSession hsr)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {
            String id = (String) param.get("byid");
            String name = (String) param.get("byname");
            String sorttype = (String) param.get("sort");
            if(id!=null)
            {
                ModelMap modelMap = new ModelMap();
                modelMap.put("goods",goodsService.getGoodsByID(Long.parseLong(id)));
                return new ModelAndView("goodsTable",modelMap);
            }
            if(name!=null)
            {
                List<Goods> list = goodsService.getGoodsByName(name);
                ModelMap modelMap = new ModelMap();
                modelMap.put("goods",list);
                return new ModelAndView("goodsTable",modelMap);
            }
            if(sorttype!=null)
            {
                List<Goods> list = goodsService.getAllGoods();
                ModelMap modelMap = new ModelMap();
                if(sorttype.equals("byid"))
                {
                    list.sort(new byID());
                    modelMap.put("goods",list);
                    return new ModelAndView("goodsTable",modelMap);
                }
                if(sorttype.equals("byiddesc"))
                {
                    list.sort(new byID());
                    Collections.reverse(list);
                    modelMap.put("goods",list);
                    return new ModelAndView("goodsTable",modelMap);
                }
                if(sorttype.equals("byname"))
                {
                    list.sort(new byName());
                    modelMap.put("goods",list);
                    return new ModelAndView("goodsTable",modelMap);
                }
                if(sorttype.equals("bynamedesc"))
                {
                    list.sort(new byName());
                    Collections.reverse(list);
                    modelMap.put("goods",list);
                    return new ModelAndView("goodsTable",modelMap);
                }
                if(sorttype.equals("byquantity"))
                {
                    list.sort(new byQuantity());
                    modelMap.put("goods",list);
                    return new ModelAndView("goodsTable",modelMap);
                }
                if(sorttype.equals("byquantitydesc"))
                {
                    list.sort(new byQuantity());
                    Collections.reverse(list);
                    modelMap.put("goods",list);
                    return new ModelAndView("goodsTable",modelMap);
                }
                modelMap.put("goods",list);
                return new ModelAndView("goodsTable",modelMap);
            }
            if(name==null && id==null && sorttype==null)
            {
                ModelMap modelMap = new ModelMap();
                modelMap.put("goods",goodsService.getAllGoods());
                return new ModelAndView("goodsTable",modelMap);
            }
        }
        return new ModelAndView("page403");
    }
    @RequestMapping(value = "/goodsAdd", method = RequestMethod.GET)
    public void addGoods(@RequestParam Map<String,Object> param,HttpSession hsr,HttpServletResponse response)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {
            String vc = (String) param.get("vc");
            String category = (String) param.get("category");
            String name = (String) param.get("name");
            String quantity = (String) param.get("quantity");
            String rp = (String) param.get("rp");
            String wp = (String) param.get("wp");
            if(!vc.isEmpty() && !category.isEmpty() && !name.isEmpty() && !quantity.isEmpty() && !rp.isEmpty() && !wp.isEmpty())
            {
                boolean addStatus;
                addStatus=goodsService.addGoods(Integer.parseInt(vc),category,name,Long.parseLong(quantity),Double.parseDouble(rp),Double.parseDouble(wp));
                if(addStatus==true)
                {
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
    @RequestMapping(value = "/goodsRemove", method = RequestMethod.GET)
    public void removeGoods(@RequestParam String param, HttpSession hsr, HttpServletResponse response)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {
            int vc = Integer.parseInt(param);
            boolean removeStatus;
            removeStatus=goodsService.removeGoods(vc);
            if(removeStatus==true)
            {
                response.setStatus(HttpServletResponse.SC_OK);
            } else
            {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
    @RequestMapping(value = "/goodsUpdate", method = RequestMethod.GET)
    public void updateGoods(@RequestParam Map<String, Object> param, HttpSession hsr, HttpServletResponse response)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {
            String vc = (String) param.get("vc");
            String category = (String) param.get("category");
            String name = (String) param.get("name");
            String quantity = (String) param.get("quantity");
            String rp = (String) param.get("rp");
            String wp = (String) param.get("wp");
            if(!vc.isEmpty() && !category.isEmpty() && !name.isEmpty() && !quantity.isEmpty() && !rp.isEmpty() && !wp.isEmpty())
            {
                boolean addStatus;
                addStatus=goodsService.updateGoods(Integer.parseInt(vc),category,name,Long.parseLong(quantity),Double.parseDouble(rp),Double.parseDouble(wp));
                if(addStatus)
                {
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
    @RequestMapping(value = "/shops", method = RequestMethod.GET)
    public ModelAndView getShopsTable(@RequestParam Map<String, Object> param, HttpSession hsr)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {
            String id = (String) param.get("byid");
            String name = (String) param.get("byname");
            if(id!=null)
            {
                ModelMap modelMap = new ModelMap();
                modelMap.put("shops",shopService.getShopByID(Long.parseLong(id)));
                return new ModelAndView("shopsTable",modelMap);
            } else if (name!=null)
            {
                ModelMap modelMap = new ModelMap();
                modelMap.put("shops",shopService.getShopByName(name));
                return new ModelAndView("shopsTable",modelMap);
            } else
            {
                ModelMap modelMap = new ModelMap();
                modelMap.put("shops",shopService.getAllShops());
                return new ModelAndView("shopsTable",modelMap);
            }
        } else
        {
            return new ModelAndView("page403");
        }
    }
    @RequestMapping(value = "/shopsAdd", method = RequestMethod.GET)
    public void addShop(@RequestParam Map<String, Object> param, HttpSession hsr,HttpServletResponse response)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {
            String id = (String) param.get("id");
            String shopname = (String) param.get("shopname");
            String address = (String) param.get("address");
            String phonenumber = (String) param.get("pn");
            if(id!=null && shopname != null && address != null && phonenumber != null)
            {
                boolean addStatus = shopService.addShop(Long.parseLong(id),shopname,address,phonenumber);
                if(addStatus)
                {
                    response.setStatus(HttpServletResponse.SC_OK);
                } else
                {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else
            {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else
        {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
    @RequestMapping(value = "/shopDelete", method = RequestMethod.GET)
    public void removeShop(@RequestParam String param,HttpSession hsr,HttpServletResponse response) {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null) {
            long id = Long.parseLong(param);
            boolean result = shopService.removeShop(id);
            if(result) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else
            {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else
        {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
    @RequestMapping(value="/orders", method = RequestMethod.GET)
    public ModelAndView getAllOrders(@RequestParam Map<String, Object> param, HttpSession hsr) {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {
            String id = (String) param.get("byid");
            if(id!=null)
            {
                ModelMap modelMap = new ModelMap();
                modelMap.put("orders",orderSerivce.getOrderbyId(Long.parseLong(id)));
                return new ModelAndView("orders",modelMap);
            } else
            {
                ModelMap modelMap = new ModelMap();
                modelMap.put("orders", orderSerivce.getallOrders());
                return new ModelAndView("orders", modelMap);
            }
        } else
        {
            return new ModelAndView("page403");
        }
    }
    @RequestMapping(value="/OrderAdd", method = RequestMethod.GET)
    public void addOrder(@RequestParam Map<String,Object> param, HttpSession hsr, HttpServletResponse response)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {

        } else
        {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @RequestMapping(value="/OrderDelete", method = RequestMethod.GET)
    public void removeOrder(@RequestParam String param,HttpSession hsr,HttpServletResponse response)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {
            long id = Long.parseLong(param);
            boolean result = orderSerivce.removeOrder(id);
            if(result)
            {
                response.setStatus(HttpServletResponse.SC_OK);
            } else
            {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else
        {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
    @RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
    public ModelAndView getorderlist(@RequestParam String param,HttpSession hsr)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {
            if(param!=null) {
                List<GoodsName> list = orderSerivce.getOrderbyId(Long.parseLong(param)).get(0).getOrderUnitList();
                ModelMap modelMap = new ModelMap();
                modelMap.put("list", list);
                return new ModelAndView("goodsList", modelMap);
            } else {
                ModelMap modelMap = new ModelMap();
                modelMap.put("list",null);
                return new ModelAndView("goodsList", modelMap);
            }
        } else {
            return new ModelAndView("page403");
        }
    }
}