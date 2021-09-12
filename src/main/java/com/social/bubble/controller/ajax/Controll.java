package com.social.bubble.controller.ajax;

import com.social.bubble.model.Mensagem;
import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Msg;
import com.social.bubble.service.MensagemService;
import com.social.bubble.service.PrincipalUserService;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/ajax")
public class Controll {

    @Autowired
    PrincipalUserService principalUserService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    MensagemService mensagemService;

    //carregar icone navbar da mensagem (exibir numero de mensagens nao lidas)
    @RequestMapping(value = "/content/mensagem-icon", method = RequestMethod.GET)
    ModelAndView carregarContentIconMsgm(){
        ModelAndView modelAndView = new ModelAndView("replace/msg-content :: msg-icon");

        modelAndView.addObject("mensagens", mensagemService.getNumeroDeMensagensNaoLidas(principalUserService.get()));

        return modelAndView;
    }

    //carregar modal com todas as mensagens em caixa de entrada
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

    //metodo responsavel por enviar mensagem rapida entre dois usuario por meio ajax
    @RequestMapping(value = "/mensagemRapida", method = RequestMethod.POST)
    public void mensagemRapida(@RequestParam("mensagem") String mensagem, @RequestParam("username") String username){

        Usuario usuario = usuarioService.findByUsername(username);
        Usuario myUser = principalUserService.get();

        Mensagem mensagemObj = new Mensagem();
        mensagemObj.setMensagem(mensagem);
        mensagemObj.setTipoMensagem(Msg.MENSAGEMRAPIDA);

        mensagemService.save(mensagemObj);
    }

}
