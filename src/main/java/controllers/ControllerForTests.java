package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by ruslan on 01.10.2016.
 */
@Controller
@RequestMapping("/tests")
public class ControllerForTests {
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public ModelAndView viewTest1()
    {
        return new ModelAndView("jstestpage");
    }
    @RequestMapping(value = "/test2GET", method = RequestMethod.GET)
    public ModelAndView viewTest2GET(@RequestParam Map<String,Object> map)
    {
        String param1 = (String) map.get("param1");
        String param2 = (String) map.get("param2");
        ModelMap modelMap = new ModelMap();
        modelMap.put("param1",param1);
        modelMap.put("param2",param2);
        return new ModelAndView("jsreturnmessage");
    }
    @RequestMapping(value = "/test2POST", method = RequestMethod.POST)
    public ModelAndView viewTest2POST(@RequestParam Map<String,Object> map)
    {
        String param1 = (String) map.get("param1");
        String param2 = (String) map.get("param2");
        ModelMap modelMap = new ModelMap();
        modelMap.put("param1",param1);
        modelMap.put("param2",param2);
        return new ModelAndView("jsreturnmessage");
    }
}
