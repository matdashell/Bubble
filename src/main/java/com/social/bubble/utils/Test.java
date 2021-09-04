package com.social.bubble.utils;

import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Animais;
import com.social.bubble.model.enums.Cores;
import com.social.bubble.model.enums.EstMusical;
import com.social.bubble.model.enums.Genero;
import com.social.bubble.repository.BubbleChatRepository;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;

@Component
public class Test {

    @Autowired
    private UsuarioService usuarioService;

    void teste() {

        Usuario usuario = new Usuario();
        byte[] bt = ("0").getBytes();
        usuario.setNickname("teste");
        usuario.setUsername("teste");
        usuario.setSenha(new BCryptPasswordEncoder(8).encode("senha"));
        usuario.setAnimaisFavoritos(Collections.singletonList(Animais.CACHORRO));
        usuario.setCoresFavoritas(Collections.singletonList(Cores.AMARELO));
        usuario.setEstiloMusical(Collections.singletonList(EstMusical.BLUES));
        usuario.setFotoPerfil(bt);
        usuario.setGenero(Genero.MASCULINO);
        usuario.setIdade(18);
        usuario.setDescricaoPerfil("Perfil com descrição teste");

        usuarioService.save(usuario);
    }
}
