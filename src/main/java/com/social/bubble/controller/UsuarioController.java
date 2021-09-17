package com.social.bubble.controller;

import com.social.bubble.model.*;
import com.social.bubble.model.enums.Msg;
import com.social.bubble.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/principalUser")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PrincipalUserService principalUserService;

    @Autowired
    private PostagemService postagemService;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private BubbleChatService bubbleChatService;

    @Autowired
    private MensagemService mensagemService;

    // opção somente para perfil
    // adicionar uma nova postagem no perfil pessoal
    @RequestMapping(value = "/addPostagem", method = RequestMethod.POST)
    public ModelAndView adicionarPostagem(Postagem postagem, BindingResult bindingResult,
            @RequestParam("file") MultipartFile multipartFile) {

        Usuario myUser = principalUserService.get();

        ModelAndView modelAndView = new ModelAndView("timeline/perfil");
        modelAndView.addObject("usuario", myUser);
        modelAndView.addObject("principalUser", myUser);

        // em caso de erros retornar erro ao usuario
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("warning", "Erro nos campos da postagem!.");
            return modelAndView;
        }

        // configurar dados da postagem
        postagem.setDataPostagem(LocalDate.now());
        postagem.setUsuarioPost(myUser);
        postagem.setPostagemPublica(true);

        // adicionar imagem na postagem
        if (multipartFile != null) {
            try {
                postagem.setImagemPost(multipartFile.getBytes());
            } catch (Exception ignored) {
            }
        }

        postagemService.save(postagem);

        modelAndView.addObject("sucess","Postagem salva com sucesso!");
        return new ModelAndView("redirect:/perfil/" + myUser.getUsername());
    }

    //responder solicitação de amizade
    @RequestMapping(value = "/respSolicitacao", method = RequestMethod.POST)
    ModelAndView respSolicitacao(String username, boolean resposta){

        ModelAndView modelAndView = new ModelAndView("replace/base :: null");

        Usuario myUser = principalUserService.get();
        Usuario usuario = usuarioService.findByUsername(username);
        List<Mensagem> mensagem = mensagemService.getMensagem(Msg.SOLICITACAO, usuario, myUser);

        if(!mensagem.isEmpty()){
            mensagem.forEach(m -> mensagemService.delete(m));

            String msg;

            if(resposta){
                msg = String.format("@%s Aceitou Seu Convite de Amizade!", myUser.getUsername());

                myUser.getListAmigosUsuarios().add(usuario);
                usuario.getListAmigosUsuarios().add(myUser);
            }else{
                msg = String.format("@%s Recusou Seu Convite de Amizade!", myUser.getUsername());
            }

            mensagemService.setNewSendMessage(usuario, Msg.AVISO, msg);

            usuarioService.save(myUser);
            usuarioService.save(usuario);
        }

        return modelAndView;
    }
}
