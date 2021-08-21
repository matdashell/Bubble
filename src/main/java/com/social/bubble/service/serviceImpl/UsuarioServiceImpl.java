package com.social.bubble.service.serviceImpl;

import com.social.bubble.model.Usuario;
import com.social.bubble.repository.UsuarioRepository;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Iterable<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findById(username).isEmpty() ?
                usuarioRepository.findById(username).get() : null;
    }

    @Override
    public Iterable<Usuario> searchByUsername(String username) {
        return usuarioRepository.searchByUsername(username);
    }

    @Override
    public Iterable<Usuario> searchByNickname(String nickname) {
        return usuarioRepository.searchByNickname(nickname);
    }

    @Override
    public Usuario findByNickname(String nickname) {
        return usuarioRepository.findByNickname(nickname);
    }

    @Override
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}
