package com.social.bubble.controller.content;

import com.social.bubble.service.PrincipalUserService;
import com.social.bubble.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PrincipalUserService principalUserService;

    //replace alert para ajax
    @RequestMapping(value = "/perfil", method = RequestMethod.POST)
    ModelAndView replaceAlertPerfil(@RequestParam("username") String username){
        ModelAndView modelAndView = new ModelAndView("replace/perfil-panel :: panel");

        modelAndView.addObject("usuario", usuarioService.findByUsername(username));
        modelAndView.addObject("principalUser", principalUserService.get());

        return modelAndView;
    }

    //replace panel perfil para ajax
    @RequestMapping(value = "/perfil-panel", method = RequestMethod.GET)
    String replacePanelPerfil(){
        return "replace/perfil-panel :: panel";
    }
}
