package com.social.bubble.service.serviceImpl;

import com.social.bubble.model.Usuario;
import com.social.bubble.service.PrincipalUserService;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalUserServiceImpl implements PrincipalUserService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Usuario get() {
        return usuarioService.findByUsername(
                ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
        );
    }
}
