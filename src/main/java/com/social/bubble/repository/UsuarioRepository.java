package com.social.bubble.repository;

import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Animais;
import com.social.bubble.model.enums.Cores;
import com.social.bubble.model.enums.EstMusical;
import com.social.bubble.model.enums.Genero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    @Query(value = "SELECT * FROM usuario WHERE nickname = ?1", nativeQuery = true)
    Usuario findByNickname(String nickname);

    @Query(value = "SELECT * FROM usuario WHERE username LIKE %?1%", nativeQuery = true)
    Iterable<Usuario> searchByUsername(String username);

    @Query(value = "SELECT * FROM usuario WHERE nickname LIKE %?1%", nativeQuery = true)
    Iterable<Usuario> searchByNickname(String nickname);

    @Query(value = "SELECT TOP 30 u.username " +
            "FROM usuario AS u, rel_cores AS c, rel_estmusical AS e, rel_animaisfav AS a " +
            "WHERE u.perfil_match = true " +
            "AND u.username LIKE %?6% " +
            "AND u.nickname LIKE %?7% " +
            "AND (u.idade BETWEEN ?1 AND ?2) " +
            "AND u.username = e.id_user " +
            "AND e.estilo_musical LIKE %?4% " +
            "AND u.username = c.id_user " +
            "AND c.cores_favoritas LIKE %?3% " +
            "AND u.username = a.id_user " +
            "AND a.animais_favoritos LIKE %?5% " +
            "ORDER BY RANDOM()", nativeQuery = true)
    List<String> matches(
            int menorIdade,
            int maiorIdade,
            String cores,
            String estMusical,
            String animais,
            String username,
            String nickname
    );
}
