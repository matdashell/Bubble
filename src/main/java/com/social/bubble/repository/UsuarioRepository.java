package com.social.bubble.repository;

import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Animais;
import com.social.bubble.model.enums.Cores;
import com.social.bubble.model.enums.EstMusical;
import com.social.bubble.model.enums.Genero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    @Query(value = "SELECT * FROM usuario WHERE nickname = ?1", nativeQuery = true)
    Usuario findByNickname(String nickname);

    @Query(value = "SELECT * FROM usuario WHERE username LIKE %?1%", nativeQuery = true)
    Iterable<Usuario> searchByUsername(String username);

    @Query(value = "SELECT * FROM usuario WHERE nickname LIKE %?1%", nativeQuery = true)
    Iterable<Usuario> searchByNickname(String nickname);

    @Query(value = "SELECT " +
            "(" +
                "u.foto_perfil," +
                "u.username," +
                "u.descricao_perfil," +
                "u.nickname" +
            ") " +
            "FROM " +
                "usuario AS u," +
                "rel_cores AS c," +
                "rel_estmusical AS e," +
                "rel_animaisfav AS a " +
            "WHERE " +
                "u.genero LIKE %?1% AND " +
                "(u.idade BETWEEN ?2 AND ?3) AND " +
                "u.username = e.id_user AND e.estilo_musical LIKE %?5% AND " +
                "u.username = c.id_user AND c.cores_favoritas LIKE %?4% AND " +
                "u.username = a.id_user AND a.animais_favoritos LIKE %?6%", nativeQuery = true)
    Iterable<Usuario> matches(
            Genero genero,
            int menorIdade,
            int maiorIdade,
            Cores cores,
            EstMusical estMusical,
            Animais animais
    );
}
