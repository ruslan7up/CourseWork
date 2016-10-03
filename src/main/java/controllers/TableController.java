package controllers;

import data.impl.AccountServiceImpl;
import data.impl.GoodsServiceImpl;
import domains.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
}
