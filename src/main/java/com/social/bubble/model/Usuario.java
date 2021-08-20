package com.social.bubble.model;
import com.social.bubble.model.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class Usuario{

    @Id
    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String nickname;

    @NotNull
    @NotBlank
    protected String senha;

    @ElementCollection
    @CollectionTable(name = "rel_cores", 
        joinColumns = @JoinColumn(name = "id_user")
    )
    @NotNull
    @NotBlank
    private List<String> coresFavoritas;

    @ElementCollection
    @CollectionTable(name = "rel_animaisfav",
        joinColumns = @JoinColumn(name = "id_user")
    )
    @NotNull
    @NotBlank
    private List<String> animaisFavoritos;

    @ElementCollection
    @CollectionTable(name = "rel_estmusical",
        joinColumns = @JoinColumn(name = "id_user")
    )
    @NotNull
    @NotBlank
    private List<String> estiloMusical;

    @NotNull
    @NotBlank
    private Genero genero;

    private int idade;

    @NotNull
    @NotBlank
    private Date dataNasc;

    @Lob
    @NotNull
    @NotBlank
    private byte[] fotoPerfil;

    @OneToMany
    @JoinTable(name = "rel_postagem")
    private Iterable<Postagem> postagens;

    /*lista de comentarios do ususario em posts*/
    @OneToMany
    @JoinTable(name = "rel_comentarios_users")
    private Iterable<Comentario> comentariosPost;

    /*lista de amigos do usuario*/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rel_amigos",
        joinColumns = @JoinColumn(name = "usuario"),
        inverseJoinColumns = @JoinColumn(name = "amigo")
    )
    private Iterable<Usuario> listAmigosUsuarios;

    /*lista de usuario que o tem como amigo*/
    @ManyToMany(mappedBy = "listAmigosUsuarios")
    private Iterable<Usuario> listUsuariosAmigos;

    /*chats do usuario*/
    @ManyToMany
    @JoinTable(name = "rel_chat",
        joinColumns = @JoinColumn(name = "usuario", referencedColumnName = "username"),
        inverseJoinColumns = @JoinColumn(name = "chat", referencedColumnName = "chat_id")
    )
    private Iterable<BubbleChat> bubbleChats;
}
