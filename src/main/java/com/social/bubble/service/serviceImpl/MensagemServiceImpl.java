package com.social.bubble.service.serviceImpl;

import com.social.bubble.model.Mensagem;
import com.social.bubble.repository.MensagemRepository;
import com.social.bubble.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MensagemServiceImpl implements MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    @Override
    public Iterable<Mensagem> findAll() {
        return mensagemRepository.findAll();
    }

    @Override
    public Mensagem findById(long id) {
        return mensagemRepository.findById(id).isPresent() ?
                mensagemRepository.findById(id).get() : null;
    }

    @Override
    public void save(Mensagem mensagem) {
        mensagemRepository.save(mensagem);
    }

    @Override
    public void delete(Mensagem mensagem) {
        mensagemRepository.delete(mensagem);
    }
}
