package com.social.bubble.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/perfil")
public class PerfilController {

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ModelAndView perfil(){
        return null;
    }

    @RequestMapping(value = "/{username}/info", method = RequestMethod.GET)
    public ModelAndView informacoes(){
        return null;
    }

    @RequestMapping(value = "/{username}/amigos", method = RequestMethod.GET)
    public ModelAndView exibirAmigos(){
        return null;
    }
}
