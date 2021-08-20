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

    @RequestMapping(value = "/global", method = RequestMethod.GET)
    public ModelAndView globalChat(){
        return null;
    }

    @RequestMapping(value = "/global/{id}", method = RequestMethod.GET)
    public ModelAndView globalChatSala(){
        return null;
    }

    @RequestMapping(value = "/autenticado", method = RequestMethod.GET)
    public ModelAndView authChat(){
        return null;
    }

    @RequestMapping(value = "/autenticado/{id}", method = RequestMethod.GET)
    public ModelAndView authChatSala(){
        return null;
    }

    @RequestMapping(value = "/privado/{id}", method = RequestMethod.GET)
    public ModelAndView privateChatSala(){
        return null;
    }
}
