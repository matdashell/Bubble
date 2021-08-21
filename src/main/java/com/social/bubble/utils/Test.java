package com.social.bubble.utils;
import com.social.bubble.model.BubbleChat;
import com.social.bubble.repository.BubbleChatRepository;
import com.social.bubble.service.BubbleChatService;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Test {

    @Autowired
    private BubbleChatRepository bubbleChatRepository;

//    @PostConstruct
    void teste(){

    }
}
