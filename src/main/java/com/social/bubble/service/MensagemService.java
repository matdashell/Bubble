package com.social.bubble.service;

import com.social.bubble.model.Mensagem;
import org.springframework.stereotype.Service;

@Service
public interface MensagemService {

    Iterable<Mensagem> findAll();
    Mensagem findById(long id);
    void save(Mensagem mensagem);
    void delete(Mensagem mensagem);
}
