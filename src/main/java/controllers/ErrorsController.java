package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by admin on 20.09.2016.
 */
@Controller
@RequestMapping("/errors")
public class ErrorsController {
    @RequestMapping("/error404")
    public ModelAndView get404()
    {
        return new ModelAndView("page404");
    }
}
