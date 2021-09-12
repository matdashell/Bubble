package com.social.bubble.service;

import com.social.bubble.model.Mensagem;
import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Msg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MensagemService {

    Iterable<Mensagem> findAll();
    Mensagem findById(long id);
    void save(Mensagem mensagem);
    void delete(Mensagem mensagem);
    List<Mensagem> getMensagem(Msg msg, Usuario sender, Usuario getter);
    List<Mensagem> getMensagemEnviada(Msg msg, Usuario sender);
    List<Mensagem> getMensagemRecebida(Msg msg, Usuario getter);
    long getNumeroDeMensagensNaoLidas(Usuario getter);
}
