package com.social.bubble.controller;

import com.social.bubble.model.DTO.UsuarioDTOConta;
import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Animais;
import com.social.bubble.model.enums.Cores;
import com.social.bubble.model.enums.EstMusical;
import com.social.bubble.model.enums.Genero;
import com.social.bubble.service.PrincipalUserService;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping(value = "/config")
public class ConfigController {

    @Autowired
    private PrincipalUserService principalUserService;

    @Autowired
    private UsuarioService usuarioService;

    //apresentar tela inicial de configurações
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView config(){

        ModelAndView modelAndView = new ModelAndView("config/home");

        modelAndView.addObject("principalUser", principalUserService.get());
        modelAndView.addObject("generos", Genero.values());
        modelAndView.addObject("cores", Cores.values());
        modelAndView.addObject("musicas", EstMusical.values());
        modelAndView.addObject("animais", Animais.values());

        return modelAndView;
    }

    //apresentar tela de configurações da conta
    @RequestMapping(value = "/conta", method = RequestMethod.POST)
    public String configContaGet(UsuarioDTOConta usuarioDTO, MultipartFile file){

        Usuario myUser = principalUserService.get();

        myUser.setNickname(usuarioDTO.getNickname());
        myUser.setGenero(usuarioDTO.getGenero());
        myUser.setCoresFavoritas(usuarioDTO.getCoresFavoritas());
        myUser.setAnimaisFavoritos(usuarioDTO.getAnimaisFavoritos());
        myUser.setEstiloMusical(usuarioDTO.getEstiloMusical());

        if(!file.isEmpty()){
            try {
                myUser.setFotoPerfil(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        usuarioService.save(myUser);

        return "redirect:/config/";
    }

    //apresentar tela de configurações da senha
    @RequestMapping(value = "/alterarSenha", method = RequestMethod.POST)
    public String configSenha(String antigaSenha, String novaSenha){

        Usuario myUser = principalUserService.get();

        if(new BCryptPasswordEncoder().matches(myUser.getPassword(), antigaSenha)){

            myUser.setSenha(new BCryptPasswordEncoder(8).encode(novaSenha));

            usuarioService.save(myUser);
        }

        return "redirect:/config/";
    }

    //apresentar tela de configurações de privacidade
    @RequestMapping(value = "/privacidade", method = RequestMethod.POST)
    public String configPrivacidade(boolean perfil, boolean chat, boolean solicit, boolean match){

        Usuario myUser = principalUserService.get();

        myUser.setPerfilPublico(perfil);
        myUser.setChatPublico(chat);
        myUser.setConfirmarSolicitacoes(solicit);
        myUser.setPerfilMatch(match);

        usuarioService.save(myUser);

        return "redirect:/config/";
    }
}
