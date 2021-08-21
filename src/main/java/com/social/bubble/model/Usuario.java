package com.social.bubble.model;
import com.social.bubble.model.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class Usuario implements UserDetails {

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

    private String descricaoPerfil;

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
    private List<Postagem> postagens;

    /*lista de comentarios do ususario em posts*/
    @OneToMany
    @JoinTable(name = "rel_comentarios_users")
    private List<Comentario> comentariosPost;

    /*lista de amigos do usuario*/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rel_amigos",
        joinColumns = @JoinColumn(name = "usuario"),
        inverseJoinColumns = @JoinColumn(name = "amigo")
    )
    private List<Usuario> listAmigosUsuarios;

    /*lista de usuario que o tem como amigo*/
    @ManyToMany(mappedBy = "listAmigosUsuarios")
    private List<Usuario> listUsuariosAmigos;

    /*chats do usuario*/
    @ManyToMany
    @JoinTable(name = "rel_chat",
        joinColumns = @JoinColumn(name = "usuario", referencedColumnName = "username"),
        inverseJoinColumns = @JoinColumn(name = "chat", referencedColumnName = "chat_id")
    )
    private List<BubbleChat> bubbleChats;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
