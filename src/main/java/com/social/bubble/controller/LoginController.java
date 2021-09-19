package com.social.bubble.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    //tela de login com validação spring security

    @RequestMapping(value = "/login/", method = RequestMethod.GET)
    public ModelAndView loginGet(){
        return new ModelAndView("home/login");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView standard(){
        return new ModelAndView("redirect:/login/");
    }

    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public ModelAndView loginPost(){
        return new ModelAndView("redirect:/perfil/meuPerfil");
    }
}
