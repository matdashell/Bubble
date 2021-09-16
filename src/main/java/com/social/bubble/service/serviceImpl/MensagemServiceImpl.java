package com.social.bubble.service.serviceImpl;

import com.social.bubble.model.Mensagem;
import com.social.bubble.model.Usuario;
import com.social.bubble.model.enums.Msg;
import com.social.bubble.repository.MensagemRepository;
import com.social.bubble.service.MensagemService;
import com.social.bubble.service.PrincipalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MensagemServiceImpl implements MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private PrincipalUserService principalUserService;

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

    @Override
    public List<Mensagem> getMensagem(Msg msg, Usuario sender, Usuario getter) {
         return mensagemRepository.getMensagens(msg.name(), sender.getUsername(), getter.getUsername());
    }

    @Override
    public List<Mensagem> getMensagemEnviada(Msg msg, Usuario sender) {
        return mensagemRepository.getMensagensEnviado(msg.name(), sender.getUsername());
    }

    @Override
    public List<Mensagem> getMensagemRecebida(Msg msg, Usuario getter) {
        return mensagemRepository.getMensagensRecebido(msg.name(), getter.getUsername());
    }

    @Override
    public long getNumeroDeMensagensNaoLidas(Usuario getter) {
        return mensagemRepository.getNumeroMensagensNaoLidas(getter.getUsername());
    }

    @Override
    public void setNewSendMessage(Usuario getter, Msg tipoMsg, String mensagem) {
        Mensagem mensagemObj = new Mensagem();
        mensagemObj.setTipoMensagem(tipoMsg);
        mensagemObj.setData(LocalDate.now());
        mensagemObj.setMensagemDoUsuario(principalUserService.get().getUsername());
        mensagemObj.setMensagemParaUsuario(getter.getUsername());
        mensagemObj.setMensagem(mensagem);

        mensagemRepository.save(mensagemObj);
    }

    @Override
    public void excluirMensagensRecebidas(Usuario sender, Msg tipoMsg) {
        getMensagem(Msg.SOLICITACAO, sender, principalUserService.get()).forEach(m -> mensagemRepository.delete(m));
    }

    @Override
    public void excluirMensagensEnviadas(Usuario getter, Msg tipoMsg) {
        getMensagem(Msg.SOLICITACAO, principalUserService.get(), getter).forEach(m -> mensagemRepository.delete(m));
    }
}
