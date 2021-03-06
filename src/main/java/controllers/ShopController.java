package controllers;

import data.impl.ShopServiceImpl;
import domains.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by ruslan on 24.10.2016.
 */
@Controller
@RequestMapping(value = "/shop")
public class ShopController {
    @Autowired
    private ShopServiceImpl shopService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView viewShopTable(HttpSession hsr)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {
            ModelMap modelMap = new ModelMap();
            modelMap.put("shops",shopService.getAllShops());
            return new ModelAndView("shopsinfo",modelMap);
        } else
        {
            ModelMap modelMap = new ModelMap();
            modelMap.put("authresult","Чтобы получить доступ к этой странице необходимо авторизоваться!");
            return new ModelAndView("AuthForm",modelMap);
        }
    }
}
