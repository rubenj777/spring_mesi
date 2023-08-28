package com.project.mesi.repository;

import com.project.mesi.entity.Category;
import com.project.mesi.entity.Messagerie;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagerieRepository extends JpaRepository<Messagerie, Long> {

    List<Messagerie> findAllByIdMessage(Long id);

    Messagerie findOneByIdMessage(Long id);

    List<Messagerie> findAllByIdUser1(Long id);

    List<Messagerie> findAllByIdUser(Long id);

}
