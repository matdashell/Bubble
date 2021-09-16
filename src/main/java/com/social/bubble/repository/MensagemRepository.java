package com.social.bubble.repository;

import com.social.bubble.model.Mensagem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends CrudRepository<Mensagem, Long> {

    @Query(value = "SELECT * FROM mensagem " +
            "WHERE tipo_mensagem = ?1 " +
            "AND mensagem_do_usuario = ?2 " +
            "AND mensagem_para_usuario = ?3 " +
            "ORDER BY data", nativeQuery = true)
    List<Mensagem> getMensagens(String msg, String sender, String getter);

    @Query(value = "SELECT * FROM mensagem " +
            "WHERE tipo_mensagem = ?1 " +
            "AND mensagem_do_usuario = ?2 " +
            "ORDER BY data" , nativeQuery = true)
    List<Mensagem> getMensagensEnviado(String msg, String sender);

    @Query(value = "SELECT * FROM mensagem " +
            "WHERE tipo_mensagem = ?1 " +
            "AND mensagem_para_usuario = ?2 " +
            "ORDER BY data", nativeQuery = true)
    List<Mensagem> getMensagensRecebido(String msg, String getter);

    @Query(value = "SELECT COUNT(*) FROM mensagem " +
            "WHERE mensagem_para_usuario = ?1 " +
            "AND visualizada = false ", nativeQuery = true)
    long getNumeroMensagensNaoLidas(String getter);

}
