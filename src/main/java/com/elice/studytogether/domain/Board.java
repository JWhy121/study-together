package com.elice.studytogether.domain;


import com.elice.studytogether.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name = "board")
@Getter
@Setter
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardTitle;
    private String description;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Post> posts;

}
