package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Data
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    private String description;
    private LocalDateTime dateCreate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private LocalDateTime timeUpdate;
    private Long views;
}
