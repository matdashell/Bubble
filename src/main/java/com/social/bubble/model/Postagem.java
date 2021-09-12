package com.social.bubble.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Usuario usuarioPost;

    @Lob
    private byte[] imagemPost;

    private LocalDate dataPostagem;

    private boolean postagemPublica;

    @NotNull
    @NotBlank
    private String descricao;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "rel_comentarios_post")
    @ToString.Exclude
    private List<Comentario> comentariosUsers;

    @ManyToMany(fetch = FetchType.LAZY ,mappedBy = "postagensCurtidas")
    @ToString.Exclude
    private List<Usuario> usuariosCurtiram;

    public byte[] getImagemPost(){
        return imagemPost != null ? this.imagemPost : null;
    }

    public long getNumCurtidas(){
        return usuariosCurtiram.size();
    }

    public long getNumeroComentarios(){
        return comentariosUsers.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Postagem postagem = (Postagem) o;

        return Objects.equals(id, postagem.id);
    }

    @Override
    public int hashCode() {
        return 1630887403;
    }
}
