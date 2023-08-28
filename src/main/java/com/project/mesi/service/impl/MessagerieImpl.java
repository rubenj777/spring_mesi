package com.project.mesi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mesi.entity.Messagerie;
import com.project.mesi.repository.MessagerieRepository;
import com.project.mesi.service.MessagerieService;
import java.util.List;

@Service
public class MessagerieImpl implements MessagerieService {

    @Autowired
    MessagerieRepository messagerieRepository;

    public MessagerieImpl(MessagerieRepository messagerieRepository) {
        this.messagerieRepository = messagerieRepository;
    }

    @Override
    public void save(Messagerie message) {
        messagerieRepository.save(message);
    }

    @Override
    public Messagerie findOneById(Long idMessage) {
        return messagerieRepository.findOneByIdMessage(idMessage);
    }

    @Override
    public List<Messagerie> findAllByIdMessagerie(Long IdMessage) {
        return messagerieRepository.findAllByIdMessage(IdMessage);
    }

    @Override
    public List<Messagerie> findAllByIdUser1(Long idUser1) {
        return findAllByIdUser1(idUser1);
    }

    @Override
    public List<Messagerie> findAllByIdUser(Long idUser) {
        return findAllByIdUser(idUser);
    }

}
