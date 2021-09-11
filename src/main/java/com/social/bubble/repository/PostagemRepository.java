package com.social.bubble.repository;

import com.social.bubble.model.Postagem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostagemRepository extends CrudRepository<Postagem, Long> {

    @Query(value = "SELECT * FROM postagem WHERE descricao LIKE %?1% ORDER BY data_postagem", nativeQuery = true)
    Iterable<Postagem> searchByDescricao(String descricao);

    @Query(value = "SELECT TOP 100 * FROM postagem ORDER BY data_postagem", nativeQuery = true)
    Iterable<Postagem> searchByDescricao();

    @Query(value = "SELECT TOP 100 DISTINCT p.* FROM postagem AS p, usuario AS u, rel_amigos as r " +
            "WHERE u.username = p.usuario_post_username " +
            "AND r.usuario = ?1 " +
            "AND r.amigo = u.username " +
            "AND (u.perfil_publico = true OR (r.usuario = u.username AND r.amigo = ?1)) " +
            "ORDER BY data_postagem",nativeQuery = true)
    List<Postagem> getPostagensAmigos(String username);
}
