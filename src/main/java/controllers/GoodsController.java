package controllers;

import data.impl.GoodsServiceImpl;
import domains.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by ruslan on 01.10.2016.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsServiceImpl goodsService;

    @RequestMapping(value = "/goodsPanel", method = RequestMethod.GET)
    public ModelAndView viewGoodsTable(HttpSession hsr)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account !=null ) {
            ModelMap modelMap = new ModelMap();
            modelMap.put("goods", goodsService.getAllGoods());
            return new ModelAndView("goodsPage", modelMap);
        } else
        {
            ModelMap modelMap = new ModelMap();
            modelMap.put("authresult","Чтобы получить доступ к этой странице необходимо авторизоваться!");
            return new ModelAndView("AuthForm",modelMap);
        }
    }
}
