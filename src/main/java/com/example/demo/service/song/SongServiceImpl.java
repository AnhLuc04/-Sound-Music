package com.example.demo.service.song;

import com.example.demo.model.Song;
import com.example.demo.repository.song.ISongRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongServiceImpl implements ISongService {
    @Autowired
    private ISongRepository iSongRepository;

    @Override
    public Iterable<Song> findAll() {
        return iSongRepository.findAll();
    }

    @Override
    public Optional<Song> findById(Long id) throws NotFoundException {
        return iSongRepository.findById(id);
    }

    @Override
    public Song save(Song model) {
        return iSongRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        iSongRepository.deleteById(id);
    }
}
