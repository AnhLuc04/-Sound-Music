package com.example.demo.controller.song;

import com.example.demo.model.Song;
import com.example.demo.service.song.ISongService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private ISongService iSongService;
    //Thêm một bài hát
    @PostMapping()
    public ResponseEntity<Song> saveNewSong(@Valid @RequestBody Song song, BindingResult bindingResult) {
        if (!bindingResult.hasFieldErrors()) {
            song.setDateCreate(LocalDateTime.now());
            return new ResponseEntity<Song>(iSongService.save(song), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    // Xóa một bài hát

    @DeleteMapping("/{id}")
    public ResponseEntity<Song> deleteSong(@PathVariable("id") Long id) throws NotFoundException {
     Optional<Song> song = iSongService.findById(id);
        if (song == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        iSongService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
