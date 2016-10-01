package controllers;

import data.impl.AccountServiceImpl;
import domains.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 12.09.2016.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private AccountServiceImpl accountService;
    @RequestMapping(value = "/authPage", method = RequestMethod.POST)
    public ModelAndView authorizeTheUser(@RequestParam Map<String,Object> map, HttpSession hsr)
    {
        if(!map.isEmpty()) {
                String userName = (String) map.get("user");
                String password = (String) map.get("pass");
                Account account = accountService.getAccountByLogin(userName);
                if(account!=null)
                {
                    if(password.equals(account.getPass()))
                    {
                        ModelMap modelMap = new ModelMap();
                        modelMap.put("name", userName);
                        modelMap.put("password", password);
                        String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
                        modelMap.put("sessionID", sessionID);
                        hsr.setAttribute("user", new Account(1L, userName, password));
                        return new ModelAndView("hello", modelMap);
                    } else
                    {
                        return new ModelAndView("page403");
                    }
                }
        } else if(hsr.getAttribute("user")!=null)
        {
            Account account = (Account) hsr.getAttribute("user");
            ModelMap modelMap = new ModelMap();
            modelMap.put("name",account.getLogin());
            modelMap.put("password",account.getPass());
            modelMap.put("sessionID",RequestContextHolder.currentRequestAttributes().getSessionId());
            return new ModelAndView("hello",modelMap);
        }
        return new ModelAndView("page403");
    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView viewReqestTestPage(@RequestParam  Map<String,Object> param,HttpSession hsr) {
        String param1 = (String) param.get("test");
        return new ModelAndView("test",new ModelMap("test",param1));
    }
}