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
 * Created by ruslan on 01.10.2016.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    private GoodsServiceImpl goodsService;
    @RequestMapping(value = "/allPanels", method = RequestMethod.GET)
    public ModelAndView viewAdminPanel(@RequestParam Map<String,Object> param, HttpSession hsr) {

        Account user = (Account) hsr.getAttribute("user");
        if (user != null) {
            ModelMap map = new ModelMap();
            map.put("accserivce",accountService);
            String id = (String) param.get("id");
            String login = (String) param.get("login");
            if (id != null) {
                try {
                    Account account = accountService.getAccountByID(Long.parseLong(id));
                    if (account != null) {
                        List<Account> list = new ArrayList<>();
                        list.add(account);
                        map = new ModelMap();
                        map.put("accounts", list);
                        return new ModelAndView("admPanel", map);
                    } else {
                        return new ModelAndView("admPanel",map);
                    }
                } catch (NumberFormatException e)
                {
                    return new ModelAndView("admPanel",map);
                }

            } else if (login != null) {
                Account account = accountService.getAccountByLogin(login);
                if (account != null) {
                    List<Account> list = new ArrayList<>();
                    list.add(account);
                    map = new ModelMap();
                    map.put("accounts", list);
                    return new ModelAndView("admPanel", map);
                } else {
                    return new ModelAndView("admPanel",map);
                }
            }
            map.put("accounts", accountService.getAllUsers());
            map.put("goods", goodsService.getAllGoods() );
            return new ModelAndView("admPanel", map);
        } else {
            return new ModelAndView("page403");
        }
    }

}
