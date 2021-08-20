package com.social.bubble.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Entity
public class Postagem {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Usuario usuarioPost;

    @Lob
    private byte[] imagemPost;

    @NotNull @NotBlank
    private String descricao;

    @OneToMany
    @JoinTable(name = "rel_comentarios_post")
    private Iterable<Comentario> comentariosUsers;

}