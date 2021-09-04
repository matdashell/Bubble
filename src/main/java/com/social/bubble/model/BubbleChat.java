package com.social.bubble.model;

import com.social.bubble.model.enums.Chat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class BubbleChat {

    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nomeChat;

    @Enumerated(EnumType.STRING)
    private Chat tipoChat;

    private Date dataCriacao;

    @ManyToMany(mappedBy = "bubbleChats", fetch = FetchType.EAGER)
    private List<Usuario> usuariosChat;

    @OneToMany(mappedBy = "usuarioMsgm")
    @ToString.Exclude
    private List<Mensagem> mensagensChat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BubbleChat that = (BubbleChat) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1253242342;
    }
}
