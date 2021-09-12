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

    //encaminhar para perfil pessoal
    @RequestMapping(value = "/meuPerfil", method = RequestMethod.GET)
    public String redirecPerfilUserGet(){
        return "redirect:/perfil/"+principalUserService.get().getUsername();
    }

    //verificar perfil de usuairo
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ModelAndView perfil(@PathVariable("username") String username){

        Usuario usuario = usuarioService.findByUsername(username);

        if(usuario != null){
            Usuario myUser = principalUserService.get();

            ModelAndView modelAndView = new ModelAndView("timeline/perfil");
            if(myUser != usuario && !myUser.getListAmigosUsuarios().contains(usuario)){
                modelAndView.addObject("info", "Conecte com '"+usuario.getNickname()+"' Para Aumentar Sua Rede De Amizades :)");
            }
            modelAndView.addObject("usuario", usuario);
            modelAndView.addObject("principalUser", myUser);
            modelAndView.addObject("solicitacoes", mensagemService.getMensagemRecebida(Msg.SOLICITACAO, myUser));

            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/perfil/meuPerfil");
        modelAndView.addObject("warning", "Perfil não encontrado!");
        return modelAndView;
    }

    //metodo responsavel por atualizar estado de amizade entre dois usuario por meio ajax
    @RequestMapping(value = "/altAmizade", method = RequestMethod.GET)
    public ModelAndView method(@RequestParam("username") String username){

        Usuario usuario = usuarioService.findByUsername(username);
        Usuario myUser = principalUserService.get();

        ModelAndView modelAndView = new ModelAndView("timeline/perfil");

        if(usuario != null){

            //verificar se já é amigo para alterar status amizade
            if(myUser.getListAmigosUsuarios().contains(usuario)){

                //caso ja seja amigo a conexão sera quebrada
                myUser.getListAmigosUsuarios().remove(usuario);
                modelAndView.addObject("sucess", "Usuario Desconectado com Sucesso!");

                //criar mensagem para notificar usuario da desconexão
                Mensagem mensagem = new Mensagem();
                mensagem.setTipoMensagem(Msg.AVISO);
                mensagem.setMensagem(String.format("Usuario '%s' Desconectou da sua Rede de Amizades.", myUser.getUsername()));
                mensagem.setDate(LocalDate.now());
                mensagem.setMensagemDoUsuario(myUser.getUsername());
                mensagem.setMensagemParaUsuario(usuario.getUsername());

            }else{

                //caso usuario esteja com a opção de confirmar solicitação marcada
                if(usuario.isConfirmarSolicitacoes()) {

                    //caso o usuario do outro lado ja o tenha na lista de amigos
                    if(usuario.getListAmigosUsuarios().contains(myUser)){

                        myUser.getListAmigosUsuarios().add(usuario);
                        modelAndView.addObject("sucess", "Usuario Conectado com Sucesso!");

                        Mensagem mensagem = new Mensagem();
                        mensagem.setTipoMensagem(Msg.AVISO);
                        mensagem.setMensagem(String.format("Usuario '%s' Conectou na sua Rede de Amizades.", myUser.getUsername()));
                        mensagem.setDate(LocalDate.now());
                        mensagem.setMensagemDoUsuario(myUser.getUsername());
                        mensagem.setMensagemParaUsuario(usuario.getUsername());

                    }
                    //caso o usuario do outro lado ainda nao o tenha na lista de amigos
                    else {

                        //verificar se o outro usuario ja tinha enviado uma solicitação de amizade
                        List<Mensagem> mensagemList =
                                mensagemService.getMensagem(Msg.SOLICITACAO, usuario, myUser);

                        //caso nao tenha enviado uma nova solicitacao sera enviada
                        if(mensagemList.isEmpty()){

                            Mensagem mensagem = new Mensagem();
                            mensagem.setTipoMensagem(Msg.SOLICITACAO);
                            mensagem.setDate(LocalDate.now());
                            mensagem.setMensagemDoUsuario(myUser.getUsername());
                            mensagem.setMensagemParaUsuario(usuario.getUsername());

                            mensagemService.save(mensagem);

                            modelAndView.addObject("Sucess", "Solicitação enviada com sucesso!");

                        }
                        //caso ja tenha enviado a mensagem sera apagada e a conexão sera criada
                        else{

                            mensagemList.forEach(m -> mensagemService.delete(m));
                            myUser.getListAmigosUsuarios().add(usuario);
                            usuario.getListAmigosUsuarios().add(myUser);
                            modelAndView.addObject("sucess", "Usuario Conectado com Sucesso!");

                            Mensagem mensagem = new Mensagem();
                            mensagem.setTipoMensagem(Msg.AVISO);
                            mensagem.setMensagem(String.format("Usuario '%s' Conectou na sua Rede de Amizades.", myUser.getUsername()));
                            mensagem.setDate(LocalDate.now());
                            mensagem.setMensagemDoUsuario(myUser.getUsername());
                            mensagem.setMensagemParaUsuario(usuario.getUsername());
                        }
                    }
                }
                //caso usuario nao esteja marcado para confirmar solicitação o usuario ira conectar
                else{
                    myUser.getListAmigosUsuarios().add(usuario);

                    Mensagem mensagem = new Mensagem();
                    mensagem.setTipoMensagem(Msg.AVISO);
                    mensagem.setMensagem(String.format("Usuario '%s' Conectou na sua Rede de Amizades.", myUser.getUsername()));
                    mensagem.setDate(LocalDate.now());
                    mensagem.setMensagemDoUsuario(myUser.getUsername());
                    mensagem.setMensagemParaUsuario(usuario.getUsername());

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
