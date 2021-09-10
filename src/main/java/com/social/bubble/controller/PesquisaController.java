package com.social.bubble.controller;

import com.social.bubble.model.Postagem;
import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Animais;
import com.social.bubble.model.enums.Cores;
import com.social.bubble.model.enums.EstMusical;
import com.social.bubble.service.PostagemService;
import com.social.bubble.service.PrincipalUserService;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/pesquisa")
public class PesquisaController {

    @Autowired
    private PrincipalUserService principalUserService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PostagemService postagemService;

    /*Necessario adicionar funcionalidade de validação das privacidades do usuario*/
    //pesquisar postagens
    @RequestMapping(value = "/postagem", method = RequestMethod.POST)
    public ModelAndView ModelAndView(String pesquisa){

        ModelAndView modelAndView = new ModelAndView("timeline/home");

        Iterable<Postagem> postagemList = postagemService.searchByDescricao(pesquisa);

        //caso hajam pesquisas com os termos descritos
        if(postagemList != null){
            modelAndView.addObject("postagem", postagemList);
            return modelAndView;
        }

        //caso não hajam pesquisas com os termos descritos
        modelAndView.addObject("erro", "Termo de pesquisa não encontrado!");
        modelAndView.addObject("postagem", postagemService.searchByDataPostagem());
        return modelAndView;
    }

    //pesquisa por preferencias de usuario
    @RequestMapping(value = "/preferencias", method = RequestMethod.POST)
    public ModelAndView pesquisarPreferencia(String cor, String animal, String musica, String idade, String username, String nickname){

        ModelAndView modelAndView = new ModelAndView("timeline/match");

        Usuario myUser = principalUserService.get();

        modelAndView.addObject("principalUser", myUser);
        modelAndView.addObject("usuarios", usuarioService.searchByMatch(idade, cor, musica, animal, username, nickname));

        return modelAndView;
    }
}
