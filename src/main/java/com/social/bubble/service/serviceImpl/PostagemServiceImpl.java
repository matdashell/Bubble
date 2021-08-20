package com.social.bubble.service.serviceImpl;

import com.social.bubble.model.Postagem;
import com.social.bubble.repository.PostagemRepository;
import com.social.bubble.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostagemServiceImpl implements PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Override
    public Iterable<Postagem> findAll() {
        return postagemRepository.findAll();
    }

    @Override
    public Iterable<Postagem> findByDescricao(String descricao) {
        return postagemRepository.findByDescricao(descricao);
    }

    @Override
    public Postagem findById(Long id) {
        return postagemRepository.findById(id).isPresent() ?
                postagemRepository.findById(id).get() : null;
    }

    @Override
    public void save(Postagem postagem) {
        postagemRepository.save(postagem);
    }

    @Override
    public void delete(Postagem postagem) {
        postagemRepository.delete(postagem);
    }
}
