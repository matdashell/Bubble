package com.social.bubble.repository;

import com.social.bubble.model.Postagem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends CrudRepository<Postagem, Long> {

    @Query(value = "SELECT * FROM postagem WHERE descricao LIKE %?1% ORDER BY data_postagem", nativeQuery = true)
    Iterable<Postagem> searchByDescricao(String descricao);

    @Query(value = "SELECT TOP 100 * FROM postagem ORDER BY data_postagem", nativeQuery = true)
    Iterable<Postagem> searchByDescricao();
}
