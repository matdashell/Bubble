package com.social.bubble.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
public class Comentario {

    @Id
    private long id;

    private String comentario;

    private Date dataComentario;

    @ManyToOne
    private Usuario comentarioUsuario;

    @ManyToOne
    private Postagem postComentario;
}
