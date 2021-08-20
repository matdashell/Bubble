package com.social.bubble.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/config")
public class ConfigController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView config(){
        return null;
    }

    @RequestMapping(value = "/conta", method = RequestMethod.GET)
    public ModelAndView configConta(){
        return null;
    }

    @RequestMapping(value = "/alterarSenha", method = RequestMethod.GET)
    public ModelAndView configSenha(){
        return null;
    }

    @RequestMapping(value = "/privacidade", method = RequestMethod.GET)
    public ModelAndView configPrivacidade(){
        return null;
    }
}
