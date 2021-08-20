package com.social.bubble.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/postagem")
public class PostagemController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView postagem(){
        return null;
    }

    @RequestMapping(value = "/{id}/likes", method = RequestMethod.GET)
    public ModelAndView exibirUserLikes(){
        return null;
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastroPostagem(){
        return null;
    }
}
