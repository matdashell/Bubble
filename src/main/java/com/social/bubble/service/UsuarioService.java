package com.social.bubble.service;

import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Animais;
import com.social.bubble.model.enums.Cores;
import com.social.bubble.model.enums.EstMusical;
import com.social.bubble.model.enums.Genero;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    Iterable<Usuario> findAll();
    Usuario findByUsername(String username);
    Usuario findByNickname(String nickname);
    Iterable<Usuario> searchByUsername(String usernam);
    Iterable<Usuario> searchByNickname(String nickname);
    Iterable<Usuario> searchByMatch(
            Genero genero,
            int menorIdade,
            int maiorIdade,
            Cores cores,
            EstMusical estMusical,
            Animais animais
    );
    void save(Usuario usuario);
    void delete(Usuario usuario);
}
