package controllers;

import data.impl.OrderSerivceImpl;
import domains.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by User on 27.10.2016.
 */
@Controller
@RequestMapping(value = "/orders")
public class OrderController {
    @Autowired
    private OrderSerivceImpl orderSerivce;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView viewOrdersPage(HttpSession hsr) {
        Account account = (Account) hsr.getAttribute("user");
        if (account != null) {
            ModelMap modelMap = new ModelMap();
            modelMap.put("orders",orderSerivce.getallOrders());
            return new ModelAndView("orders",modelMap);
        } else {
            ModelMap modelMap = new ModelMap();
            modelMap.put("authresult", "Чтобы получить доступ к этой странице необходимо авторизоваться!");
            return new ModelAndView("AuthForm", modelMap);
        }
    }
}