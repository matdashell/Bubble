package com.social.bubble.controller;

import com.social.bubble.model.BubbleChat;
import com.social.bubble.model.enums.Chat;
import com.social.bubble.service.BubbleChatService;
import com.social.bubble.service.PrincipalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/bubbleChat")
public class BubbleChatController {

    @Autowired
    private BubbleChatService bubbleChatService;

    @Autowired
    private PrincipalUserService principalUserService;

    //apresentar telas de chats
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView chats(){

        return new ModelAndView("bubble/home");
    }

    //chat global
    @RequestMapping(value = "/global", method = RequestMethod.GET)
    public ModelAndView globalChat(){

        ModelAndView modelAndView = new ModelAndView("bubble/chat");
        modelAndView.addObject("chat", bubbleChatService.findByTipo(Chat.GLOBAL));

        return modelAndView;
    }

    //chats com entradas publicas
    @RequestMapping(value = "/publico", method = RequestMethod.GET)
    public ModelAndView globalChatSala(){

        ModelAndView modelAndView = new ModelAndView("bubble/chats");
        modelAndView.addObject("chats", bubbleChatService.findByTipo(Chat.PUBLIC));

        return modelAndView;
    }

    //chat publico em especifico
    @RequestMapping(value = "/publico/{idChat}", method = RequestMethod.GET)
    public ModelAndView globalChatSala(@PathVariable("idChat") long id){

        ModelAndView modelAndView = new ModelAndView("bubble/chat");
        modelAndView.addObject("chat", bubbleChatService.findById(id));

        return modelAndView;
    }

    //chats com necessidade de autenticação pelo dono da sala
    @RequestMapping(value = "/autenticado", method = RequestMethod.GET)
    public ModelAndView authChat(){

        ModelAndView modelAndView = new ModelAndView("bubble/globalChat");
        modelAndView.addObject("chats", bubbleChatService.findByTipo(Chat.AUTH));

        return modelAndView;
    }

    //chat autenticado em especifico
    @RequestMapping(value = "/autenticado/{idChat}", method = RequestMethod.GET)
    public ModelAndView authChatSala(@PathVariable("idChat") long id){

        BubbleChat bubbleChat = bubbleChatService.findById(id);

        //validar se o usuario esta autorizado a entrar no chat
        if(bubbleChat.getUsuariosChat().contains(principalUserService.get())) {
            ModelAndView modelAndView = new ModelAndView("bubble/chat");
            modelAndView.addObject("chat", bubbleChat);
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("bubble/chat");
        modelAndView.addObject("chats", bubbleChatService.findByTipo(Chat.AUTH));
        modelAndView.addObject("erro", "Usuario não autenticado para o chat.");
        return modelAndView;
    }

    //chat privado
    @RequestMapping(value = "/privado/{idChat}", method = RequestMethod.GET)
    public ModelAndView privateChatSala(@PathVariable("idChat") long id){

        BubbleChat bubbleChat = bubbleChatService.findById(id);

        //validar se o usuario esta autorizado a entrar na sala
        if(bubbleChat.getUsuariosChat().contains(principalUserService.get())) {
            ModelAndView modelAndView = new ModelAndView("bubble/chat");
            modelAndView.addObject("chat", bubbleChat);
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("timeline/home");
        modelAndView.addObject("erro", "Usuario não autenticado para o chat.");

        return modelAndView;
    }
}
