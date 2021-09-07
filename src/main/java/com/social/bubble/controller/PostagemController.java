package com.social.bubble.controller;

import com.social.bubble.model.Postagem;
import com.social.bubble.model.Usuario;
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

@Controller
@RequestMapping(value = "/postagem")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @Autowired
    private PrincipalUserService principalUserService;

    @Autowired
    private UsuarioService usuarioService;

    //apresentar postagem maximizada
    @RequestMapping(value = "/{idPost}", method = RequestMethod.GET)
    public ModelAndView postagem(@PathVariable("idPost") long id){

        ModelAndView modelAndView = new ModelAndView("timeline/postagem");
        modelAndView.addObject("amigos", postagemService.findById(id));

        return modelAndView;
    }

    //curtir postagem
    @RequestMapping(value = "/altCurtir", method = RequestMethod.POST)
    public ModelAndView curtirPostagem(@RequestParam("id") long id){

        Postagem postagem = postagemService.findById(id);
        Usuario myUser = principalUserService.get();

        ModelAndView modelAndView = new ModelAndView("timeline/home");

        //caso o usuairo principal não tenha curtido, sera curtida
        if(!postagem.getUsuariosCurtiram().contains(myUser)){

            myUser.getPostagensCurtidas().add(postagem);
            usuarioService.save(myUser);

            modelAndView.addObject("sucess" ,
                    "Postagem de "+postagem.getUsuarioPost().getNickname()+" favoritada!");
            modelAndView.addObject("postagens", postagemService.searchByPostAmigos());
            modelAndView.addObject("principalUser", myUser);

            return modelAndView;
        }

        myUser.getPostagensCurtidas().remove(postagem);
        usuarioService.save(myUser);
        modelAndView.addObject("sucess",
                "Postagem de "+postagem.getUsuarioPost().getNickname()+" Desfavoritada!");
        modelAndView.addObject("postagens", postagemService.searchByPostAmigos());
        modelAndView.addObject("principalUser", myUser);

        return modelAndView;
    }
}
