package com.social.bubble.repository;

import com.social.bubble.model.Mensagem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagemRepository extends CrudRepository<Mensagem, Long> {
}
