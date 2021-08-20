package com.social.bubble.service.serviceImpl;

import com.social.bubble.model.Comentario;
import com.social.bubble.repository.ComentarioRepository;
import com.social.bubble.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Iterable<Comentario> findAll() {
        return comentarioRepository.findAll();
    }

    @Override
    public Comentario findById(long id) {
        return comentarioRepository.findById(id).isPresent() ?
                comentarioRepository.findById(id).get() : null;
    }

    @Override
    public void save(Comentario comentario) {
        comentarioRepository.save(comentario);
    }

    @Override
    public void delete(Comentario comentario) {
        comentarioRepository.delete(comentario);
    }
}
