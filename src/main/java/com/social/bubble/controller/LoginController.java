package com.social.bubble.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView login(){
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView validacao(){
        return null;
    }
}
