package controllers;

import data.comparators.byID;
import data.comparators.byName;
import data.comparators.byQuantity;
import data.impl.AccountServiceImpl;
import data.impl.GoodsServiceImpl;
import domains.Account;
import domains.Goods;
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
                List<Account> list = new ArrayList<>();
                try {
                    list.add(accountService.getAccountByID(Long.parseLong(id)));
                } catch (NumberFormatException e)
                {
                    return new ModelAndView("userTable");
                }
                ModelMap modelMap = new ModelMap();
                modelMap.put("accounts",list);
                return new ModelAndView("userTable",modelMap);
            } else
            if(login!=null)
            {
                List<Account> list = new ArrayList<>();
                list.add(accountService.getAccountByLogin(login));
                ModelMap modelMap = new ModelMap();
                modelMap.put("accounts",list);
                return new ModelAndView("userTable",modelMap);
            }
            if(login==null && id==null)
            {
                ModelMap modelMap = new ModelMap();
                modelMap.put("accounts", accountService.getAllUsers());
                return new ModelAndView("userTable",modelMap);
            }
        }
        return new ModelAndView("page403");
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
            if(vc!=null && category!=null && name!=null && quantity!=null && rp!=null && wp!=null)
            {
                boolean addStatus;
                addStatus=goodsService.addGoods(Integer.parseInt(vc),category,name,Long.parseLong(quantity),Double.parseDouble(rp),Double.parseDouble(wp));
                if(addStatus==true)
                {
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
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
            if(vc!=null && category!=null && name!=null && quantity!=null && rp!=null && wp!=null)
            {
                boolean addStatus;
                addStatus=goodsService.updateGoods(Integer.parseInt(vc),category,name,Long.parseLong(quantity),Double.parseDouble(rp),Double.parseDouble(wp));
                if(addStatus==true)
                {
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            }
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

}