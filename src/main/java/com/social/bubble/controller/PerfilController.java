package com.social.bubble.controller;

import com.social.bubble.model.Usuario;
import com.social.bubble.service.PrincipalUserService;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/perfil")
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PrincipalUserService principalUserService;

    //encaminhar para perfil pessoal
    @RequestMapping(value = "/meuPerfil", method = RequestMethod.GET)
    public String redirecPerfilUser(){
        return "redirect:/perfil/"+principalUserService.get().getUsername();
    }

    //verificar perfil de usuairo
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ModelAndView perfil(@PathVariable("username") String username){

        return new ModelAndView("timeline/perfil");

//        Usuario usuario = usuarioService.findByUsername(username);
//
//        if(usuario != null){
//            Usuario myUser = principalUserService.get();
//
//            ModelAndView modelAndView = new ModelAndView("timeline/perfil");
//            if(!myUser.getListAmigosUsuarios().contains(usuario)){
//                modelAndView.addObject("info", "Envie uma solicitação de amizade para "+usuario.getNickname()+" :)");
//            }
//            modelAndView.addObject("usuario", usuario);
//            modelAndView.addObject("principalUser", myUser);
//
//            return modelAndView;
//        }
//
//        ModelAndView modelAndView = new ModelAndView("redirect:/perfil/meuPerfil");
//        modelAndView.addObject("warning", "Perfil não encontrado!");
//        return modelAndView;
    }
}
