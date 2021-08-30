package com.social.bubble.service.serviceImpl;

import com.social.bubble.model.BubbleChat;
import com.social.bubble.model.enums.Chat;
import com.social.bubble.repository.BubbleChatRepository;
import com.social.bubble.service.BubbleChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BubbleChatServiceImpl implements BubbleChatService {

    @Autowired
    private BubbleChatRepository bubbleChatRepository;

    @Override
    public Iterable<BubbleChat> findAll() {
        return bubbleChatRepository.findAll();
    }

    @Override
    public Iterable<BubbleChat> findByTipo(Chat tipo) {
        return bubbleChatRepository.findByTipo(tipo);
    }

    @Override
    public Iterable<BubbleChat> findByNome(String nome) {
        return bubbleChatRepository.findByNome(nome);
    }

    @Override
    public BubbleChat findById(long id) {
        return bubbleChatRepository.findById(id).isPresent() ?
                bubbleChatRepository.findById(id).get() : null;
    }

    @Override
    public void save(BubbleChat bubbleChat) {
        bubbleChatRepository.save(bubbleChat);
    }

    @Override
    public void delte(BubbleChat bubbleChat) {
        bubbleChatRepository.delete(bubbleChat);
    }
}
