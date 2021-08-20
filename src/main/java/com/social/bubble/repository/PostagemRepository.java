package com.social.bubble.repository;

import com.social.bubble.model.Postagem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends CrudRepository<Postagem, Long> {

    @Query(value = "SELECT * FROM postagem WHERE descricao LIKE %?1%", nativeQuery = true)
    Iterable<Postagem> findByDescricao(String descricao);
}
