package com.social.bubble.service;

import com.social.bubble.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    Iterable<Usuario> findAll();
    Usuario findByUsername(String username);
    Usuario findByNickname(String nickname);
    Iterable<Usuario> searchByUsername(String usernam);
    Iterable<Usuario> searchByNickname(String nickname);
    void save(Usuario usuario);
    void delete(Usuario usuario);
}
