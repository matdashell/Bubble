package com.social.bubble.model;

import com.social.bubble.model.enums.Msg;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private long ordem;

    boolean visualizada;

    @Enumerated(EnumType.STRING)
    Msg tipoMensagem;

    @NotNull
    @NotBlank
    private String mensagem;

    @Lob
    private byte[] imagemMsgm;

    @ManyToOne
    @JoinColumn(name = "rel_mensagem")
    private Usuario usuarioMsgm;

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
