package com.project.mesi.service;

import java.util.List;

import com.project.mesi.entity.Messagerie;

public interface MessagerieService {
    void save(Messagerie message);

    Messagerie findOneById(Long idMessage);

    List<Messagerie> findAllByIdMessagerie(Long IdMessage);

    List<Messagerie> findAllByIdUser1(Long idUser1);

    List<Messagerie> findAllByIdUser(Long idUser);

}
