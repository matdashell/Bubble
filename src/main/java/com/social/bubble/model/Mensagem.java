package com.social.bubble.model;

import com.social.bubble.model.enums.Msg;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Mensagem{

    @Id
    @Column(name = "msgm_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate data;

    boolean visualizada;

    @Enumerated(EnumType.STRING)
    Msg tipoMensagem;

    private String assinatura;
    private String mensagemDoUsuario;
    private String mensagemParaUsuario;

    private String mensagem;

    @Lob
    private byte[] imagemMsgm;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Mensagem mensagem = (Mensagem) o;

        return Objects.equals(id, mensagem.id);
    }

    @Override
    public int hashCode() {
        return 1499004904;
    }
}
