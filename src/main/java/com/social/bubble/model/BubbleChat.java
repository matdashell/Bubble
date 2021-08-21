package com.social.bubble.model;

import com.social.bubble.model.enums.Chat;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class BubbleChat {

    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nomeChat;

    private Chat tipoChat;

    private Date dataCriacao;

    @ManyToMany(mappedBy = "bubbleChats", fetch = FetchType.EAGER)
    private List<Usuario> usuariosChat;

    @OneToMany(mappedBy = "usuarioMsgm")
    private List<Mensagem> mensagensChat;
}
