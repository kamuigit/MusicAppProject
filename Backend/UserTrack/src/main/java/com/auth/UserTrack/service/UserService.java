package com.auth.UserTrack.service;




import com.auth.UserTrack.Exception.PlaylistAlreadyExists;
import com.auth.UserTrack.Exception.SongAlreadyExistsException;
import com.auth.UserTrack.domain.Playlist;
import com.auth.UserTrack.domain.Song;
import com.auth.UserTrack.domain.User;
import com.auth.UserTrack.domain.UserRegister;

import java.util.List;

public interface UserService {
//    User addUser(User user);
//    List<User>  getUsers();
    List<Playlist> getUserByUserName(String userName);
    void addPlaylist(String userName,String playlistName) throws PlaylistAlreadyExists;
    void deletePlaylist(String userName,String playlistName);
    void register(UserRegister userRegister);
    void addNewSong(User user) throws SongAlreadyExistsException;
    public void addDeleteSong(User user);
}
