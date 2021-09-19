package com.social.bubble.controller.ajax;

import com.social.bubble.model.Mensagem;
import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Msg;
import com.social.bubble.service.MensagemService;
import com.social.bubble.service.PostagemService;
import com.social.bubble.service.PrincipalUserService;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {

    @Autowired
    private PrincipalUserService principalUserService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MensagemService mensagemService;

    @Autowired
    private PostagemService postagemService;

    //recarrecar tela referente à numero de mensagens na navbar
    @RequestMapping(value = "/content/mensagem-icon", method = RequestMethod.GET)
    ModelAndView carregarContentIconMsgm(){
        ModelAndView modelAndView = new ModelAndView("replace/msg-content :: msg-icon");

        modelAndView.addObject("mensagens", mensagemService.getNumeroDeMensagensNaoLidas(principalUserService.get()));

        return modelAndView;
    }

    //onload para construir mensagens
    @RequestMapping(value = "/content/mensagem-modal", method = RequestMethod.GET)
    ModelAndView carregarContentPanelMsgm(){
        ModelAndView modelAndView = new ModelAndView("replace/msg-content :: msg-modal");

        Usuario myUser = principalUserService.get();
        List<Mensagem> msgSolictacoes = mensagemService.getMensagemRecebida(Msg.SOLICITACAO, myUser);
        List<Mensagem> msgAvisos = mensagemService.getMensagemRecebida(Msg.AVISO, myUser);
        List<Mensagem> msgRapidas = mensagemService.getMensagemRecebida(Msg.MENSAGEMRAPIDA, myUser);

        modelAndView.addObject("msgSolicitacoes", msgSolictacoes);
        modelAndView.addObject("msgAvisos", msgAvisos);
        modelAndView.addObject("msgRapidas", msgRapidas);

        return modelAndView;
    }

    //recarregar tela com dados das mensagens do usuario
    @RequestMapping(value = "/content/mensagem-data", method = RequestMethod.GET)
    ModelAndView carregarPanelSolicit(){
        ModelAndView modelAndView = new ModelAndView("replace/msg-content :: msg-data");

        Usuario myUser = principalUserService.get();
        List<Mensagem> msgSolictacoes = mensagemService.getMensagemRecebida(Msg.SOLICITACAO, myUser);
        List<Mensagem> msgAvisos = mensagemService.getMensagemRecebida(Msg.AVISO, myUser);
        List<Mensagem> msgRapidas = mensagemService.getMensagemRecebida(Msg.MENSAGEMRAPIDA, myUser);

        modelAndView.addObject("msgSolicitacoes", msgSolictacoes);
        modelAndView.addObject("msgAvisos", msgAvisos);
        modelAndView.addObject("msgRapidas", msgRapidas);

        return modelAndView;
    }

    @RequestMapping(value = "/content/numcoment/{id}", method = RequestMethod.GET)
    ModelAndView carregarNumComentarios(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView("replace/pack-postagem :: comentario-num");
        modelAndView.addObject("post", postagemService.findById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/content/alert/{name}/{msg}", method = RequestMethod.GET)
    ModelAndView carregarAlert(@PathVariable("name") String name, @PathVariable("msg") String msg){
        ModelAndView modelAndView = new ModelAndView("replace/base :: alert");
        modelAndView.addObject(name, msg);
        return modelAndView;
    }
}
