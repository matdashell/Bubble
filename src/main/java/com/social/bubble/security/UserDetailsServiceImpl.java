package com.social.bubble.security;

import com.social.bubble.model.Usuario;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario myUser = usuarioService.findByUsername(username);

        if(myUser == null){
            throw new UsernameNotFoundException("Usuario não encontrado.");
        }

        return new User(myUser.getUsername(), myUser.getPassword(), myUser.getAuthorities());
    }
}
