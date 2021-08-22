package com.social.bubble.controller;

import com.social.bubble.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/postagem")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @RequestMapping(value = "/{idPost}", method = RequestMethod.GET)
    public ModelAndView postagem(@PathVariable("idPost") long id){

        ModelAndView modelAndView = new ModelAndView("timeline/postagem");
        modelAndView.addObject("postagem", postagemService.findById(id));

        return modelAndView;
    }
}
