package com.social.bubble.controller;

import com.social.bubble.model.*;
import com.social.bubble.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

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

    // opção somente para perfil
    // adicionar uma nova postagem no perfil pessoal
    @RequestMapping(value = "/addPostagem", method = RequestMethod.POST)
    public ModelAndView adicionarPostagem(Postagem postagem, BindingResult bindingResult,
            @RequestParam("file") MultipartFile multipartFile) {

        Usuario myUser = principalUserService.get();

        // em caso de erros retornar erro ao usuario
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("timeline/perfil");
            modelAndView.addObject("warning", "Erro nos campos da postagem!.");
            modelAndView.addObject("usuario", myUser);
            return modelAndView;
        }

        // configurar dados da postagem
        postagem.setDataPostagem(LocalDate.now());
        postagem.setUsuarioPost(myUser);

        // adicionar imagem na postagem
        if (multipartFile != null) {
            try {
                postagem.setImagemPost(multipartFile.getBytes());
            } catch (Exception ignored) {
            }
        }

        postagemService.save(postagem);

        return new ModelAndView("redirect:/perfil/" + myUser.getUsername());
    }

    // opção somente para perfil
    // deletar uma postagem
    @RequestMapping(value = "/delPostagem/{idPost}", method = RequestMethod.GET)
    public ModelAndView deletarPostagem(@PathVariable("idPost") long id) {

        Usuario myUser = principalUserService.get();
        Postagem postagem = postagemService.findById(id);

        // deletar postagem e redirecionar para a pagina do usuario
        if (postagem.getUsuarioPost() == myUser) {
            postagemService.delete(postagem);

            ModelAndView modelAndView = new ModelAndView("timeline/perfil");
            modelAndView.addObject("warning", "Postagem excluida com sucesso.");
            modelAndView.addObject("usuario", myUser);
            return modelAndView;
        }

        return new ModelAndView("redirect:/postagem/" + id);
    }

    // opção somente para postagem
    // adicionar comentario na postagem
    @RequestMapping(value = "/addComentario", method = RequestMethod.POST)
    public ModelAndView adicionarComentario(@RequestParam("idPost") long id, Comentario comentario,
            BindingResult bindingResult) {

        Postagem postagem = postagemService.findById(id);

        // em caso de erro na mensagem
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("timeline/postagem");
            modelAndView.addObject("warning", "Preencha o campo de mensagem antes de enviar!");
            modelAndView.addObject("postagem", postagem);
            return modelAndView;
        }

        // configurar mensagem
        Usuario myUser = principalUserService.get();
        comentario.setDataComentario(LocalDate.now());
        comentario.setComentarioUsuario(myUser);

        // vincular mensagem com postagem e salvar
        postagem.getComentariosUsers().add(comentario);
        postagemService.save(postagem);

        return new ModelAndView("redirect:/postagem/" + id);
    }

    // opção somente para postagem
    // deletar comentario de uma postagem
    @RequestMapping(value = "/delComentario/{idComentario}", method = RequestMethod.GET)
    public ModelAndView deletarComentairo(@PathVariable("idComentairo") long id) {

        Usuario myUser = principalUserService.get();
        Comentario comentario = comentarioService.findById(id);

        // ação tomada somente se o usuario for dono da postagem
        if (comentario.getComentarioUsuario() == myUser) {
            Postagem postagem = postagemService.findById(comentario.getPostComentario().getId());
            postagem.getComentariosUsers().remove(comentario);
            postagemService.save(postagem);
        }

        return new ModelAndView("redirect:/postagem/{id}");
    }

    // opção somente para bubbles
    // eviar mensagem para um bubblechat e indentificar qual tipo de chat o ususario
    // esta participando
    @RequestMapping(value = "/enviarMensagem/{idChat}", method = RequestMethod.POST)
    public ModelAndView enviarMensagemChat(@PathVariable("idChat") long id, Mensagem mensagem,
            @RequestParam("file") MultipartFile multipartFile) {

        Usuario myUser = principalUserService.get();
        BubbleChat bubbleChat = bubbleChatService.findById(id);

        // verificar se o usuario esta incluso no chat que deseja enviar a mensagem
        if (bubbleChat.getUsuariosChat().contains(myUser)) {

            mensagem.setUsuarioMsgm(principalUserService.get());
            bubbleChat.getMensagensChat().add(mensagem);

            // adicionar imagem na mensagem caso contenha uma
            if (multipartFile != null) {
                try {
                    mensagem.setImagemMsgm(multipartFile.getBytes());
                } catch (Exception ignored) {
                }
            }

            bubbleChatService.save(bubbleChat);

            return new ModelAndView("redirect:/bubbleChat/" + bubbleChat.getTipoChat() + "/{id}");
        }

        // caso nao esteja é enviado para a pagina inicial chat
        return new ModelAndView("redirec:/bubblechat/");
    }
}
