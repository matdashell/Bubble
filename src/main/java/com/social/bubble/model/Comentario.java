package com.social.bubble.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
public class Comentario {

    @Id
    private long id;

    @NotNull
    @NotBlank
    private String comentario;

    private LocalDate dataComentario;

    @ManyToOne
    private Usuario comentarioUsuario;

    @ManyToOne
    private Postagem postComentario;
}
