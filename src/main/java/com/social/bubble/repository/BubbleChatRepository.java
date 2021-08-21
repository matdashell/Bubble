package com.social.bubble.repository;

import com.social.bubble.model.BubbleChat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BubbleChatRepository extends CrudRepository<BubbleChat, Long> {

    @Query(value = "SELECT * FROM bubble_chat WHERE tipo = ?1", nativeQuery = true)
    Iterable<BubbleChat> findByTipo(String tipo);

    @Query(value = "SELECT * FROM bubble_chat WHERE nome_chat LIKE %?1%", nativeQuery = true)
    Iterable<BubbleChat> findByNome(String nome);
}
