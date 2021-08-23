package com.social.bubble.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
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
}
