package com.social.bubble.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotBlank
    private String comentario;

    private LocalDate dataComentario;

    @ManyToOne
    private Usuario comentarioUsuario;

    @ManyToOne
    private Postagem postComentario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comentario that = (Comentario) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1590006718;
    }
}
