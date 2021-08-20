package com.social.bubble.repository;

import com.social.bubble.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    @Query(value = "SELECT * FROM usuario WHERE username LIKE %?1%", nativeQuery = true)
    Iterable<Usuario> findByUsername(String username);

    @Query(value = "SELECT * FROM usuario WHERE nickname LIKE %?1%", nativeQuery = true)
    Iterable<Usuario> findByNickname(String nickname);
}
