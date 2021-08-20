package com.social.bubble.service;

import com.social.bubble.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface PrincipalUserService {

    Usuario get();
}
