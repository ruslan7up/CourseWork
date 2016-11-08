package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.impl.OrderSerivceImpl;
import domains.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 27.10.2016.
 */
@Controller
@RequestMapping(value = "/orders")
public class OrderController {
    @Autowired
    private OrderSerivceImpl orderSerivce;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView viewOrdersPage(HttpSession hsr)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null) {
            return new ModelAndView("orders");
        } else
        {
            ModelMap modelMap = new ModelMap();
            modelMap.put("authresult","Чтобы получить доступ к этой странице необходимо авторизоваться!");
            return new ModelAndView("AuthForm",modelMap);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addOrder(@RequestParam String orderRows, HttpServletResponse response){
        List<Map<String,String>> ordersList =null;
        try {
            ordersList= new ObjectMapper().readValue(orderRows, ArrayList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(ordersList==null||orderRows.isEmpty()){
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            for(Map<String,String> m : ordersList){
                System.out.println(m.get("name")+" "+m.get("quantity"));
            }
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
