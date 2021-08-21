package com.social.bubble.controller;

import com.social.bubble.service.PostagemService;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/provedor")
public class ProviderController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PostagemService postagemService;

    @RequestMapping(value = "/nickImg/{username}", method = RequestMethod.GET)
    public byte[] getNickImg(@PathVariable("username") String username){
        return usuarioService.findByUsername(username).getFotoPerfil();
    }

    @RequestMapping(value = "/postImg/{id}", method = RequestMethod.GET)
    public byte[] getPostImg(@PathVariable("id") long id){
        return postagemService.findById(id).getImagemPost();
    }
}
