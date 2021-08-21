package com.social.bubble.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/bubbleChat")
public class BubbleChatController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView chats(){
        return null;
    }

    @RequestMapping(value = "/Publico", method = RequestMethod.GET)
    public ModelAndView globalChat(){
        return null;
    }

    @RequestMapping(value = "/Publico/{id}", method = RequestMethod.GET)
    public ModelAndView globalChatSala(){
        return null;
    }

    @RequestMapping(value = "/Autenticado", method = RequestMethod.GET)
    public ModelAndView authChat(){
        return null;
    }

    @RequestMapping(value = "/Autenticado/{id}", method = RequestMethod.GET)
    public ModelAndView authChatSala(){
        return null;
    }

    @RequestMapping(value = "/Privado/{id}", method = RequestMethod.GET)
    public ModelAndView privateChatSala(){
        return null;
    }
}
