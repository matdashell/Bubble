package com.social.bubble.controller;

import com.social.bubble.model.Comentario;
import com.social.bubble.model.Postagem;
import com.social.bubble.model.Usuario;
import com.social.bubble.service.ComentarioService;
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

import java.time.LocalDate;

@Controller
@RequestMapping(value = "/postagem")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @Autowired
    private PrincipalUserService principalUserService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    ComentarioService comentarioService;

    //alterar estado curtir da postagem
    @RequestMapping(value = "/altCurtir", method = RequestMethod.POST)
    public ModelAndView curtirPostagem(@RequestParam("id") long id){

        Postagem postagem = postagemService.findById(id);
        Usuario myUser = principalUserService.get();

        ModelAndView modelAndView = new ModelAndView("timeline/home");

        //caso o usuairo principal não tenha curtido, sera curtida
        if(!postagem.getUsuariosCurtiram().contains(myUser)){

            myUser.getPostagensCurtidas().add(postagem);
            usuarioService.save(myUser);

            modelAndView.addObject("sucess" , "Postagem de "+postagem.getUsuarioPost().getNickname()+" favoritada!");
            modelAndView.addObject("postagens", postagemService.searchByPostAmigos());
            modelAndView.addObject("principalUser", principalUserService.get());

            return modelAndView;
        }

        postagemService.delete(postagem);

        modelAndView.addObject("sucess", "Postagem de "+postagem.getUsuarioPost().getNickname()+" Desfavoritada!");
        modelAndView.addObject("postagens", postagemService.searchByPostAmigos());
        modelAndView.addObject("principalUser", principalUserService.get());

        return modelAndView;
    }

    //comentar em uma postagem
    @RequestMapping(value = "/comentar", method = RequestMethod.POST)
    public ModelAndView comentarPostagem(@RequestParam("id") long id, @RequestParam("comentario") String coment){

        ModelAndView modelAndView = new ModelAndView("timeline/home");
        Usuario myUser = principalUserService.get();
        Postagem postagem = postagemService.findById(id);
        Comentario comentario = new Comentario();

        //caso todas as informações estajam corretas
        if(postagem != null){

            //relacionamento e save
            comentario.setComentario(coment);
            comentario.setDataComentario(LocalDate.now());
            comentario.setPostComentario(postagem);
            comentario.setComentarioUsuario(myUser);

            postagem.getComentariosUsers().add(comentario);

            myUser.getComentariosPost().add(comentario);

            comentarioService.save(comentario);
            postagemService.save(postagem);
            usuarioService.save(myUser);

            modelAndView.addObject("postagens", postagemService.searchByPostAmigos());
            modelAndView.addObject("principalUser", myUser);

            return modelAndView;
        }

        modelAndView.addObject("postagens", postagemService.searchByPostAmigos());
        modelAndView.addObject("principalUser", myUser);

        return modelAndView;
    }
}
