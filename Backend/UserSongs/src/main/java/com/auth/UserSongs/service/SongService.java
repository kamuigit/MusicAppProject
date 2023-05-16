package com.auth.UserSongs.service;

import com.auth.UserSongs.domain.Song;
import com.auth.UserSongs.repository.SongsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService implements ISongService {
    private SongsRepository songsRepository;

    @Autowired
    SongService(SongsRepository songsRepository){
        this.songsRepository =songsRepository;
    }

    @Override
    public Song addSong(Song song) {
        return songsRepository.save(song);
    }

    @Override
    public Song getSongByName(String songName) {
        return songsRepository.findById(songName).get();
    }

    @Override
    public List<Song> getAllSongs() {
        return songsRepository.findAll();
    }
}
