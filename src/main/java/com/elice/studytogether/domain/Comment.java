package com.elice.studytogether.domain;

import com.elice.studytogether.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long post_id;
    private String nickname;
    private String content;
}
