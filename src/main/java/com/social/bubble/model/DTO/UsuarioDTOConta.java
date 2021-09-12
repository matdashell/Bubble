package com.social.bubble.model.DTO;

import com.social.bubble.model.BubbleChat;
import com.social.bubble.model.Comentario;
import com.social.bubble.model.Postagem;
import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Animais;
import com.social.bubble.model.enums.Cores;
import com.social.bubble.model.enums.EstMusical;
import com.social.bubble.model.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UsuarioDTOConta {

    private String username;

    private String nickname;

    protected String senha;

    private String descricaoPerfil;

    private List<Cores> coresFavoritas;

    private List<Animais> animaisFavoritos;

    private List<EstMusical> estiloMusical;

    private Genero genero;

    private byte[] fotoPerfil;

    private List<Postagem> postagens;

    private List<Comentario> comentariosPost;

    private List<Usuario> listAmigosUsuarios;

    private List<Usuario> listUsuariosAmigos;

    private List<BubbleChat> bubbleChats;

    private List<Postagem> postagensCurtidas;
}
