package com.elice.studytogether.domain;

import com.elice.studytogether.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.SimpleTimeZone;


@Entity
@Table(name = "post")
@Getter
@Setter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dueDate;
    private String nickname;
    private String devLang;
    private String title;
    private String content;
    private int watchedCnt;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "boardId", referencedColumnName = "id")
    private Board board;
}
