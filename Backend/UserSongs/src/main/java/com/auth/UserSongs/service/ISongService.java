package com.auth.UserSongs.service;

import com.auth.UserSongs.domain.Song;


import java.util.List;

public interface ISongService {
    public Song addSong(Song song) ;
    public Song getSongByName(String song);

    public List<Song> getAllSongs();

}
