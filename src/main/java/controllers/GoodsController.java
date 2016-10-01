package controllers;

import data.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsServiceImpl goodsService;

    @RequestMapping(value = "/goodsPanel", method = RequestMethod.GET)
    public ModelAndView viewGoodsTable(@RequestParam Map<String,String> map, HttpSession hsr)
    {
        return new ModelAndView("goodsPage");
    }
}
