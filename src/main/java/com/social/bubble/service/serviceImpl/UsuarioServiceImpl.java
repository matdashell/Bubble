package com.social.bubble.service.serviceImpl;

import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Animais;
import com.social.bubble.model.enums.Cores;
import com.social.bubble.model.enums.EstMusical;
import com.social.bubble.model.enums.Genero;
import com.social.bubble.repository.UsuarioRepository;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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
        return usuarioRepository.findById(username).isPresent() ?
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
    public Iterable<Usuario> searchByMatch(
            String idade,
            String cores,
            String estMusical,
            String animais,
            String username,
            String nickname) {

        String[] val = idade.split("-");

        int menorIdade = Integer.parseInt(val[0].trim());
        int maiorIdade = Integer.parseInt(val[1].trim());

        return (usuarioRepository.matches(menorIdade, maiorIdade, cores, estMusical, animais, username, nickname))
                .stream()
                .filter(u -> usuarioRepository.findById(u).isPresent())
                .map(u -> usuarioRepository.findById(u).get())
                .collect(Collectors.toList());
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
