package com.social.bubble.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/principalUser")
public class UsuarioController {

    @RequestMapping(value = "/addAmigo", method = RequestMethod.GET)
    public ModelAndView adicionarAmigo(){
        return null;
    }

    @RequestMapping(value = "/delAmigo", method = RequestMethod.GET)
    public ModelAndView deletarAmigo(){
        return null;
    }

    @RequestMapping(value = "/addPostagem", method = RequestMethod.POST)
    public ModelAndView adicionarPostagem(){
        return null;
    }

    @RequestMapping(value = "/delPostagem", method = RequestMethod.GET)
    public ModelAndView deletarPostagem(){
        return null;
    }

    @RequestMapping(value = "/addComentario", method = RequestMethod.GET)
    public ModelAndView adicionarComentario(){
        return null;
    }

    @RequestMapping(value = "/delComentario", method = RequestMethod.GET)
    public ModelAndView deletarComentairo(){
        return null;
    }

    @RequestMapping(value = "/enviarMensagem", method = RequestMethod.POST)
    public ModelAndView enviarMensagem(){
        return null;
    }
}
