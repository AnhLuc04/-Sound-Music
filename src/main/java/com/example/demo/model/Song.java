package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "trường này không được để trống")
    private String name;
    private String description;
    @NotEmpty(message = "trường này không được để trống")
    private String urlMp3;
    private String avatar;
    @NotEmpty(message = "trường này không được để trống")
    private String musician;
    private long views;
    private LocalDateTime dateCreate;
    @ManyToOne
    private Singer singer;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Album album;
}
