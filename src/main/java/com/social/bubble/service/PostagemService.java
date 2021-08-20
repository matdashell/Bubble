package com.social.bubble.service;

import com.social.bubble.model.Postagem;
import org.springframework.stereotype.Service;

@Service
public interface PostagemService {

    Iterable<Postagem> findAll();
    Iterable<Postagem> findByDescricao(String descricao);
    Postagem findById (Long id);
    void save(Postagem postagem);
    void delete(Postagem postagem);
}
