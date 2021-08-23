package com.social.bubble.controller;

import com.social.bubble.service.PostagemService;
import com.social.bubble.service.PrincipalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/homePage")
public class HomeController {

    @Autowired
    PrincipalUserService principalUserService;

    @Autowired
    PostagemService postagemService;

    /*Necessário criar query para pesquisa de postagens de acordo com a privacidad de cada user*/
    //home timeline
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("timeline/home");

        return null;
    }

    /*Necessário criar query para pesquisa de postagens de acordo com a privacidad de cada user*/
    //apresentar tela de match entre usuários de acordo com as especificações
    @RequestMapping(value = "/match", method = RequestMethod.GET)
    public ModelAndView procurarAmigos(){
        return null;
    }
}
