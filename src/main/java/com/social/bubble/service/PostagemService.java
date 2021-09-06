package com.social.bubble.service;

import com.social.bubble.model.Postagem;
import org.springframework.stereotype.Service;

@Service
public interface PostagemService {

    Iterable<Postagem> findAll();
    Iterable<Postagem> searchByDataPostagem();
    Iterable<Postagem> searchByDescricao(String descricao);
    Iterable<Postagem> searchByPostAmigos();
    Postagem findById (Long id);
    void save(Postagem postagem);
    void delete(Postagem postagem);
}
