package com.project.mesi.dto;

import com.project.mesi.entity.Category;
import com.project.mesi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessagerieDto {

    private Long idMessage;

    private String content;

    private String object;

    private Long idUser;

    private Long idUser1;
}
