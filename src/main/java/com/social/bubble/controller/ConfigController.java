package com.social.bubble.controller;

import com.social.bubble.model.enums.Animais;
import com.social.bubble.model.enums.Cores;
import com.social.bubble.model.enums.EstMusical;
import com.social.bubble.model.enums.Genero;
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

    //apresentar tela inicial de configurações
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView config(){

        ModelAndView modelAndView = new ModelAndView("config/home");
        modelAndView.addObject("generos", Genero.values());
        modelAndView.addObject("cores", Cores.values());
        modelAndView.addObject("musicas", EstMusical.values());
        modelAndView.addObject("animais", Animais.values());

        return modelAndView;
    }

    //apresentar tela de configurações da conta
    @RequestMapping(value = "/conta", method = RequestMethod.GET)
    public ModelAndView configContaGet(){

        ModelAndView modelAndView = new ModelAndView("config/conta");
        modelAndView.addObject("usuario", principalUserService.get());
        return modelAndView;
    }

    //apresentar tela de configurações da senha
    @RequestMapping(value = "/alterarSenha", method = RequestMethod.GET)
    public ModelAndView configSenha(){
        return new ModelAndView("config/senha");
    }

    //apresentar tela de configurações de privacidade
    @RequestMapping(value = "/privacidade", method = RequestMethod.GET)
    public ModelAndView configPrivacidade(){
        ModelAndView modelAndView = new ModelAndView("config/privacidade");
        modelAndView.addObject("usuario", principalUserService.get());
        return modelAndView;
    }
}
