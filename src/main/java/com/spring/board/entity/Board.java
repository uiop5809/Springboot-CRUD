package com.spring.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "myboard")
public class Board {
    @Id
    private int no;
    private String title;
    private String author;
    private String content;
    private Date regdate;
}
