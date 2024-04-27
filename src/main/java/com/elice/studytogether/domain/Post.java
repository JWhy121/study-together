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

    private Date due_date;
    private String nickname;
    private String dev_lang;
    private String title;
    private String content;
    private int watched_cnt;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Board board;
}
