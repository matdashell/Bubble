package com.social.bubble.controller;

import com.social.bubble.service.MensagemService;
import com.social.bubble.service.PostagemService;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/provedor")
public class ProvedorController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PostagemService postagemService;

    @Autowired
    MensagemService mensagemService;

    //prover imagem nick
    @RequestMapping(value = "/nickImg/{username}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getNickImg(@PathVariable("username") String username){
        return usuarioService.findByUsername(username).getFotoPerfil();
    }

    //prover imagem postagem
    @RequestMapping(value = "/postImg/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getPostImg(@PathVariable("id") long id){
        return postagemService.findById(id).getImagemPost();
    }

    //prover imagem da mensagem
    @RequestMapping(value = "/mensgImg/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getPostMsgm(@PathVariable("id") long id){
        return mensagemService.findById(id).getImagemMsgm();
    }
}
