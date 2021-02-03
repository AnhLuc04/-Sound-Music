package com.example.demo.model.likePlaylist;


import com.example.demo.model.PlayList;
import com.example.demo.model.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LikePlaylist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "boolean default false")
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private PlayList playList;
}
