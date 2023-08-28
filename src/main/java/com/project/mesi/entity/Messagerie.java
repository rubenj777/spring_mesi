package com.project.mesi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Messagerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMessage;

    private String content;

    private String object;

    @JoinColumn(name = "idUser", nullable = false)
    private Long idUser;

    @JoinColumn(name = "idUser1")
    private Long idUser1;

}
