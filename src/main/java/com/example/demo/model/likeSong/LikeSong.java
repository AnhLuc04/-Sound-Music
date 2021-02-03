package com.example.demo.model.likeSong;


import com.example.demo.model.Song;
import com.example.demo.model.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LikeSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "boolean default false")
    private boolean status;
    @ManyToOne
    private User user;

    @ManyToOne
    private Song song;
}
