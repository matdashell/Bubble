package com.social.bubble.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Entity
public class Mensagem{

    @Id
    @Column(name = "msgm_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long ordem;

    @NotNull
    @NotBlank
    private String mensagem;

    @Lob
    private byte[] imagemMsgm;

    @ManyToOne
    @JoinColumn(name = "rel_mensagem")
    private Usuario usuarioMsgm;
}
