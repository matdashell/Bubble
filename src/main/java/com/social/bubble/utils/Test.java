package com.social.bubble.utils;

import com.social.bubble.repository.BubbleChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test {

    @Autowired
    private BubbleChatRepository bubbleChatRepository;

    // @PostConstruct
    void teste() {

    }
}
