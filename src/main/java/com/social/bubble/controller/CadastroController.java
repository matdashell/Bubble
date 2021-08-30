package com.social.bubble.controller;

import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Animais;
import com.social.bubble.model.enums.Cores;
import com.social.bubble.model.enums.EstMusical;
import com.social.bubble.model.enums.Genero;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping(value = "/cadastro")
public class CadastroController {

    @Autowired
    private UsuarioService usuarioService;

    //apresentar tela de cadastro
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView cadastro(){

        //apresentar itens disponivei na tela de cadastro
        ModelAndView modelAndView = new ModelAndView("home/cadastro");
        modelAndView.addObject("cores", Cores.values());
        modelAndView.addObject("animais", Animais.values());
        modelAndView.addObject("musicas", EstMusical.values());
        modelAndView.addObject("genero", Genero.values());

        return modelAndView;
    }

    //validador de cadastro
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView validacao(Usuario usuario, BindingResult bindingResult, @RequestParam("file") MultipartFile multipartFile){

        //apresentar itens disponivei na tela de cadastro e informações do usuario anteriores
        ModelAndView modelAndView = new ModelAndView("home/cadastro");
        modelAndView.addObject("cores", Cores.values());
        modelAndView.addObject("animais", Animais.values());
        modelAndView.addObject("musicas", EstMusical.values());
        modelAndView.addObject("genero", Genero.values());

        //caso haja erros nos campos
        if(bindingResult.hasErrors()){
            if(
                    !(
                    bindingResult.hasFieldErrors("perfilPublico") ||
                    bindingResult.hasFieldErrors("chatPublico") ||
                    bindingResult.hasFieldErrors("confirmarSolicitacoes") ||
                    bindingResult.hasFieldErrors("perfilMatch")
                    )
            ) {
                modelAndView.addObject("warning", "Campos preenchidos de forma incorreta ou incompleta!");
                return modelAndView;
            }
        }

        //caso seja menor de idade
        if(usuario.getIdade() < 18){
            modelAndView.addObject("danger", "Permitido apenas para maiores de 18 anos!");
            return modelAndView;
        }

        //caso username ja exista no sistema
        if(usuarioService.findByUsername(usuario.getUsername()) != null){
            usuario.setUsername(null);
            modelAndView.addObject("warning", "Username selecionado já existe!");
            return modelAndView;
        }

        //caso nickname ja exista no sistema
        if(usuarioService.findByNickname(usuario.getNickname()) != null){
            usuario.setNickname(null);
            modelAndView.addObject("warning", "Nickname selecionado já existe!");
            return modelAndView;
        }

        //adicionar imagem do usuario
        try{
            usuario.setFotoPerfil(multipartFile.getBytes());
        }catch(Exception ignored){ }

        //caso todas as informações estejam corretas a senha sera criptografada e usuario salvo
        usuario.setSenha(new BCryptPasswordEncoder(8).encode(usuario.getSenha()));
        usuarioService.save(usuario);

        modelAndView = new ModelAndView("home/login");
        modelAndView.addObject("sucess","Conta criada com sucesso!, realize o login.");
        return modelAndView;
    }
}
