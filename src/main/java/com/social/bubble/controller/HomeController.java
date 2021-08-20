package com.social.bubble.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/homePage")
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(){
        return null;
    }

    @RequestMapping(value = "/timeline", method = RequestMethod.GET)
    public ModelAndView timeline(){
        return null;
    }

    @RequestMapping(value = "/match", method = RequestMethod.GET)
    public ModelAndView procurarAmigos(){
        return null;
    }
}
