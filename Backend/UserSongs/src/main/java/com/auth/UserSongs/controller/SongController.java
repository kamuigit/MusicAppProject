package com.auth.UserSongs.controller;

import com.auth.UserSongs.domain.Song;
import com.auth.UserSongs.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200/")
public class SongController {
    private ISongService iSongService;

    @Autowired
    SongController(ISongService iSongService){
        this.iSongService=iSongService;
    }

    @GetMapping("/getSong/{songName}")
    public ResponseEntity<?> getOneSongById(@PathVariable String songName){
        return new ResponseEntity<>(iSongService.getSongByName(songName), HttpStatus.OK);
    }

    @PostMapping("/addNewSong")
    public  ResponseEntity<?> addNewSong(@RequestBody Song song){
        return new ResponseEntity<>(iSongService.addSong(song),HttpStatus.OK);
    }

    @GetMapping("/getAllSongs")
    public ResponseEntity<?> getAllSongs(){
        return new ResponseEntity<>(iSongService.getAllSongs(),HttpStatus.OK);
    }
}
