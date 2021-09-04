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

@Controller
@RequestMapping(value = "/ajax")
public class Controll {

    @Autowired
    PrincipalUserService principalUserService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    MensagemService mensagemService;



    //metodo responsavel por enviar mensagem rapida entre dois usuario por meio ajax
    @RequestMapping(value = "/mensagemRapida", method = RequestMethod.POST)
    public void mensagemRapida(@RequestParam("mensagem") String mensagem, @RequestParam("username") String username){

        Usuario usuario = usuarioService.findByUsername(username);
        Usuario myUser = principalUserService.get();

        Mensagem mensagemObj = new Mensagem();
        mensagemObj.setMensagem(mensagem);
        mensagemObj.setUsuarioMsgm(usuario);
        mensagemObj.setTipoMensagem(Msg.MENSAGEMRAPIDA);

        mensagemService.save(mensagemObj);
    }

}
