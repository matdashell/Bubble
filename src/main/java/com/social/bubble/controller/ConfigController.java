package com.social.bubble.controller;

import com.social.bubble.service.PrincipalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/config")
public class ConfigController {

    @Autowired
    private PrincipalUserService principalUserService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView config(){

        return new ModelAndView("config/home");
    }

    @RequestMapping(value = "/conta", method = RequestMethod.GET)
    public ModelAndView configContaGet(){

        ModelAndView modelAndView = new ModelAndView("config/conta");
        modelAndView.addObject("usuario", principalUserService.get());
        return modelAndView;
    }

    @RequestMapping(value = "/alterarSenha", method = RequestMethod.GET)
    public ModelAndView configSenha(){
        return new ModelAndView("config/senha");
    }

    @RequestMapping(value = "/privacidade", method = RequestMethod.GET)
    public ModelAndView configPrivacidade(){
        ModelAndView modelAndView = new ModelAndView("config/privacidade");
        modelAndView.addObject("usuario", principalUserService.get());
        return modelAndView;
    }
}
