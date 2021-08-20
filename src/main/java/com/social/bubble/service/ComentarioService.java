package com.social.bubble.service;

import com.social.bubble.model.Comentario;
import org.springframework.stereotype.Service;

@Service
public interface ComentarioService {

    Iterable<Comentario> findAll();
    Comentario findById(long id);
    void save(Comentario comentario);
    void delete(Comentario comentario);
}
