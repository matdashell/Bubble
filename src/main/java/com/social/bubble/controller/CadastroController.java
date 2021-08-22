package com.social.bubble.controller;

import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Animais;
import com.social.bubble.model.enums.Cores;
import com.social.bubble.model.enums.EstMusical;
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

        //caso haja erros nos campos
        if(bindingResult.hasErrors()){
            modelAndView.addObject("erro", "Campos preenchidos de forma incorreta ou incompleta!");
            modelAndView.addObject("usuario", usuario);
            return modelAndView;
        }

        //caso username ja exista no sistema
        if(usuarioService.findByUsername(usuario.getUsername()) != null){
            usuario.setUsername(null);
            modelAndView.addObject("erro", "Username selecionado já existe!");
            modelAndView.addObject("usuario", usuario);
            return modelAndView;
        }

        //caso nickname ja exista no sistema
        if(usuarioService.findByNickname(usuario.getNickname()) != null){
            usuario.setNickname(null);
            modelAndView.addObject("erro", "Nickname selecionado já existe!");
            modelAndView.addObject("usuario", usuario);
            return modelAndView;
        }

        //adicionar imagem do usuario
        try{
            usuario.setFotoPerfil(multipartFile.getBytes());
        }catch(Exception ignored){ }

        //caso todas as informações estejam corretas a senha sera criptografada e usuario salvo
        usuario.setSenha(new BCryptPasswordEncoder(8).encode(usuario.getSenha()));
        usuarioService.save(usuario);

        return new ModelAndView("redirect:/login/");
    }
}
