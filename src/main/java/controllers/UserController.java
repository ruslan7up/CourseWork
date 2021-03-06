package controllers;

import data.impl.AccountServiceImpl;
import data.impl.GoodsServiceImpl;
import domains.Account;
import org.apache.commons.codec.digest.DigestUtils;
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
import java.util.Map;

/**
 * Created by admin on 12.09.2016.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    private GoodsServiceImpl goodsService;
    @RequestMapping(value = "/authPage", method = RequestMethod.POST)
    public ModelAndView authorizeTheUserPOST(@RequestParam Map<String,Object> map, HttpSession hsr, HttpServletResponse response)
    {
        if(!map.isEmpty()) {
                String userName = (String) map.get("user");
                String password = (String) map.get("pass");
                Account account = accountService.getAccountByLogin(userName);
                if(account!=null)
                {

                    if(DigestUtils.md5Hex(password).equals(account.getPass()))
                    {
                        hsr.setAttribute("user", account);
                        try {
                            response.sendRedirect("/goods/goodsPanel");
                        } catch (Exception e)
                        {

                        }
                        return new ModelAndView("goodsPage");
                    } else
                    {
                        ModelMap modelMap = new ModelMap();
                        modelMap.put("authresult","Ошибка! Пожалуйста, проверьте правильность написания логина и пароля.");
                        return new ModelAndView("AuthForm",modelMap);
                    }
                } else
                {
                    ModelMap modelMap = new ModelMap();
                    modelMap.put("authresult","Ошибка! Пожалуйста, проверьте правильность написания логина и пароля.");
                    return new ModelAndView("AuthForm",modelMap);
                }
        } else if(hsr.getAttribute("user")!=null)
        {
            ModelMap modelMap = new ModelMap();
            modelMap.put("goods",goodsService.getAllGoods());
            try {
                response.sendRedirect("/goods/goodsPanel");
            } catch (Exception e)
            {
            }
            return new ModelAndView("goodsPage",modelMap);
        }
        return new ModelAndView("page403");
    }
    @RequestMapping(value = "/authPage", method = RequestMethod.GET)
    public ModelAndView authorizeTheUserGET(@RequestParam Map<String,Object> map, HttpSession hsr, HttpServletResponse response)
    {
         if(hsr.getAttribute("user")!=null)
        {
            ModelMap modelMap = new ModelMap();
            modelMap.put("goods",goodsService.getAllGoods());
            try {
                response.sendRedirect("/goods/goodsPanel");
            } catch (Exception e)
            {
            }
            return new ModelAndView("goodsPage",modelMap);
        } else
         {
             return new ModelAndView("AuthForm");
         }
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession hsr, HttpServletResponse response)
    {
        Account account = (Account) hsr.getAttribute("user");
        if(account!=null)
        {
            hsr.removeAttribute("user");
            try {
                response.sendRedirect("/users/authPage");
            } catch (IOException e)
            {
                return new ModelAndView("AuthForm");
            }
            return new ModelAndView("AuthForm");
        } else return new ModelAndView("AuthForm");
    }

}