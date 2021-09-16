package com.social.bubble.controller;

import com.social.bubble.model.Mensagem;
import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Msg;
import com.social.bubble.service.MensagemService;
import com.social.bubble.service.PrincipalUserService;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/perfil")
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PrincipalUserService principalUserService;

    @Autowired
    private MensagemService mensagemService;

    // encaminhar para perfil pessoal
    @RequestMapping(value = "/meuPerfil", method = RequestMethod.GET)
    public String redirecPerfilUserGet() {
        return "redirect:/perfil/" + principalUserService.get().getUsername();
    }

    // verificar perfil de usuairo
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ModelAndView perfil(@PathVariable("username") String username) {

        Usuario usuario = usuarioService.findByUsername(username);

        if (usuario != null) {
            Usuario myUser = principalUserService.get();

            ModelAndView modelAndView = new ModelAndView("timeline/perfil");
            if (myUser != usuario && !myUser.getListAmigosUsuarios().contains(usuario)) {
                modelAndView.addObject("info",
                        "Conecte com '" + usuario.getNickname() + "' Para Aumentar Sua Rede De Amizades :)");
            }
            modelAndView.addObject("solicitEnviada",
                    mensagemService.getMensagem(Msg.SOLICITACAO, myUser, usuario).size() > 0);
            modelAndView.addObject("usuario", usuario);
            modelAndView.addObject("principalUser", myUser);

            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/perfil/meuPerfil");
        modelAndView.addObject("warning", "Perfil não encontrado!");
        return modelAndView;
    }

    // metodo responsavel por atualizar estado de amizade entre dois usuario por
    @RequestMapping(value = "/altAmizade", method = RequestMethod.GET)
    public ModelAndView method(@RequestParam("username") String username) {

        Usuario usuario = usuarioService.findByUsername(username);
        Usuario myUser = principalUserService.get();

        ModelAndView modelAndView = new ModelAndView("timeline/perfil");

        // reverter envio de solicitação caso o usuairo ja tenha enviado uma solicitação
        // anterior
        if (mensagemService.getMensagem(Msg.SOLICITACAO, myUser, usuario).size() > 0) {

            mensagemService.excluirMensagensRecebidas(usuario, Msg.SOLICITACAO);
            modelAndView.addObject("usuario", usuario);
            modelAndView.addObject("principalUser", myUser);
            modelAndView.addObject("solicitEnviada", false);

            return modelAndView;
        }

        if (usuario != null) {

            // Verificar se o usuario já é amigo do usuario principal
            if (myUser.getListAmigosUsuarios().contains(usuario)) {

                mensagemService.excluirMensagensRecebidas(usuario, Msg.SOLICITACAO);

                // caso ja seja amigo a conexão sera quebrada
                myUser.getListAmigosUsuarios().remove(usuario);
                modelAndView.addObject("sucess", "Usuario Desconectado com Sucesso!");

                // criar mensagem para notificar usuario da desconexão
                String mensagem = "Usuario "+usuario.getUsername()+" Desconectou da sua Rede de Amizades.";
                mensagemService.setNewSendMessage(usuario, Msg.AVISO, mensagem);

            } else {

                // caso usuario esteja com a opção de confirmar solicitação marcada
                if (usuario.isConfirmarSolicitacoes()) {

                    // caso o usuario do outro lado ja o tenha na lista de amigos
                    if (usuario.getListAmigosUsuarios().contains(myUser)) {

                        mensagemService.excluirMensagensRecebidas(usuario, Msg.SOLICITACAO);

                        myUser.getListAmigosUsuarios().add(usuario);
                        modelAndView.addObject("sucess", "Usuario Conectado com Sucesso!");

                        String mensagem = "Usuario "+usuario.getUsername()+" Conectou em sua Rede de Amizades.";
                        mensagemService.setNewSendMessage(usuario, Msg.AVISO, mensagem);

                    }
                    // caso o usuario do outro lado ainda nao o tenha na lista de amigos
                    else {

                        // verificar se o outro usuario ja tinha enviado uma solicitação de amizade
                        List<Mensagem> mensagemList = mensagemService.getMensagem(Msg.SOLICITACAO, usuario, myUser);

                        // caso o usuario nao tenha enviado uma nova solicitacao sera enviada
                        if (mensagemList.isEmpty()) {

                            mensagemService.setNewSendMessage(usuario, Msg.SOLICITACAO, "");

                            modelAndView.addObject("Sucess", "Solicitação enviada com sucesso!");

                        }
                        // caso o usuario ja tenha enviado a mensagem sera apagada e a conexão sera
                        // criada
                        else {

                            mensagemService.excluirMensagensRecebidas(usuario, Msg.SOLICITACAO);
                            myUser.getListAmigosUsuarios().add(usuario);
                            usuario.getListAmigosUsuarios().add(myUser);
                            modelAndView.addObject("sucess", "Usuario Conectado com Sucesso!");

                            String mensagem = "Usuario "+usuario.getUsername()+" Conectou em sua Rede de Amizades.";
                            mensagemService.setNewSendMessage(usuario, Msg.AVISO, mensagem);
                        }
                    }
                }
                // caso usuario nao esteja marcado para confirmar solicitação o usuario ira
                // conectar
                else {

                    mensagemService.excluirMensagensRecebidas(usuario, Msg.SOLICITACAO);

                    myUser.getListAmigosUsuarios().add(usuario);

                    String mensagem = "Usuario "+usuario.getUsername()+" Conectou em sua Rede de Amizades.";
                    mensagemService.setNewSendMessage(usuario, Msg.AVISO, mensagem);

                    modelAndView.addObject("sucess", "Usuario Conectado com Sucesso!");
                }
            }
            usuarioService.save(myUser);
            usuarioService.save(usuario);
        }

        modelAndView.addObject("usuario", usuario);
        modelAndView.addObject("principalUser", myUser);

        return modelAndView;
    }
}
