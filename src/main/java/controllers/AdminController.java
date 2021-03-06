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
            map.put("accounts", accountService.getAllUsers());
            return new ModelAndView("admPanel", map);
        } else {
            ModelMap modelMap = new ModelMap();
            modelMap.put("authresult","Чтобы получить доступ к этой странице необходимо авторизоваться!");
            return new ModelAndView("AuthForm",modelMap);
        }

    }

}
