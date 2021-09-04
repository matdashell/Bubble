package com.social.bubble.model;
import com.social.bubble.model.enums.Animais;
import com.social.bubble.model.enums.Cores;
import com.social.bubble.model.enums.EstMusical;
import com.social.bubble.model.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @NotNull
    @ElementCollection(targetClass = Cores.class)
    @CollectionTable(name = "rel_cores", joinColumns = @JoinColumn(name = "id_user"))
    @Enumerated(EnumType.STRING)
    private List<Cores> coresFavoritas;

    @NotNull
    @ElementCollection(targetClass = Animais.class)
    @CollectionTable(name = "rel_animaisfav",joinColumns = @JoinColumn(name = "id_user"))
    @Enumerated(EnumType.STRING)
    private List<Animais> animaisFavoritos;

    @NotNull
    @ElementCollection(targetClass = EstMusical.class)
    @CollectionTable(name = "rel_estmusical", joinColumns = @JoinColumn(name = "id_user"))
    @Enumerated(EnumType.STRING)
    private List<EstMusical> estiloMusical;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @NotNull
    private int idade;

    @Lob
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

    /*lista de postagens curtidas pelo usuario*/
    @ManyToMany
    @JoinTable(name = "rel_likes",
        joinColumns = @JoinColumn(name = "usuario", referencedColumnName = "username"),
        inverseJoinColumns = @JoinColumn(name = "postagem", referencedColumnName = "post_id")
    )
    private List<Postagem> postagensCurtidas;

    //privacidade do usuario
    boolean perfilPublico;

    boolean chatPublico;

    boolean confirmarSolicitacoes;

    boolean perfilMatch;

    public boolean friendWith(Usuario usuario){
        return this.listAmigosUsuarios.contains(usuario);
    }

    public boolean containsCorFav(Cores cor){
        return this.coresFavoritas.contains(cor);
    }

    public boolean containsMscFav(EstMusical musica){
        return this.estiloMusical.contains(musica);
    }

    public boolean containsAnmFav(Animais animal){
        return this.animaisFavoritos.contains(animal);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new AbstractList<GrantedAuthority>() {
            @Override
            public GrantedAuthority get(int index) {
                return () -> ("USURIOUS") ;
            }

            @Override
            public int size() {
                return 1;
            }
        };
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
