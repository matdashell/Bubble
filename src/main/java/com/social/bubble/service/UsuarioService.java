package com.social.bubble.service;

import com.social.bubble.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    Iterable<Usuario> findAll();
    Usuario findUser(String username);
    Iterable<Usuario> findByUsername(String usernam);
    Iterable<Usuario> findByNickname(String nickname);
    void save(Usuario usuario);
    void delete(Usuario usuario);
}
