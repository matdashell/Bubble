package com.social.bubble.service;

import com.social.bubble.model.BubbleChat;
import com.social.bubble.model.enums.Chat;
import org.springframework.stereotype.Service;

@Service
public interface BubbleChatService {

    Iterable<BubbleChat> findAll();
    Iterable<BubbleChat> findByTipo(Chat tipo);
    Iterable<BubbleChat> findByNome(String nome);
    BubbleChat findById(long id);
    void save(BubbleChat bubbleChat);
    void delte(BubbleChat bubbleChat);
}
