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

        ModelAndView modelAndView = new ModelAndView("replace/pack-postagem :: btn-curtir-postagem");

        //caso o usuairo principal não tenha curtido, sera curtida
        if(!postagem.getUsuariosCurtiram().contains(myUser)){

            myUser.getPostagensCurtidas().add(postagem);
            postagem.getUsuariosCurtiram().add(myUser);
            postagemService.save(postagem);
            usuarioService.save(myUser);

            modelAndView.addObject("post", postagemService.findById(id));
            modelAndView.addObject("principalUser", principalUserService.get());

            return modelAndView;
        }

        myUser.getPostagensCurtidas().remove(postagem);
        postagem.getUsuariosCurtiram().remove(myUser);
        postagemService.save(postagem);
        usuarioService.save(myUser);

        modelAndView.addObject("post", postagemService.findById(id));
        modelAndView.addObject("principalUser", principalUserService.get());

        return modelAndView;
    }

    //comentar em uma postagem
    @RequestMapping(value = "/comentar", method = RequestMethod.POST)
    public ModelAndView comentarPostagem(@RequestParam("id") long id, String comentario){

        ModelAndView modelAndView = new ModelAndView("replace/pack-postagem :: comentarios-list");

        Usuario myUser = principalUserService.get();
        Postagem postagem = postagemService.findById(id);

        Comentario coment = new Comentario();

        //caso todas as informações estajam corretas
        if(postagem != null){

            //relacionamento e save
            coment.setComentario(comentario);
            coment.setDataComentario(LocalDate.now());
            coment.setPostComentario(postagem);
            coment.setComentarioUsuario(myUser);

            postagem.getComentariosUsers().add(coment);

            myUser.getComentariosPost().add(coment);

            comentarioService.save(coment);
            postagemService.save(postagem);
            usuarioService.save(myUser);

            modelAndView.addObject("post", postagemService.findById(id));

            return modelAndView;
        }

        modelAndView.addObject("post", postagemService.findById(id));

        return modelAndView;
    }

    @RequestMapping(value = "/deletar", method = RequestMethod.POST)
    ModelAndView deletarPostagem(long id){

        ModelAndView modelAndView = new ModelAndView("timeline/home");

        Usuario myUser = principalUserService.get();
        Postagem postagem = postagemService.findById(id);

        if(postagem != null && myUser.getPostagens().contains(postagem)){

            postagem.getUsuariosCurtiram().forEach(u -> u.getPostagensCurtidas().remove(postagem));
            postagemService.save(postagem);
            postagem.getUsuariosCurtiram().clear();
            postagemService.save(postagem);

            postagemService.delete(postagemService.findById(id));

            modelAndView.addObject("sucess", "Postagem deletada com sucesso!");
        }

        return modelAndView;
    }
}
