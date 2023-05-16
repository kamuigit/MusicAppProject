package com.auth.UserTrack.service;


import com.auth.UserTrack.Exception.PlaylistAlreadyExists;
import com.auth.UserTrack.Exception.SongAlreadyExistsException;
import com.auth.UserTrack.Repository.UserRegisterDataRepository;
import com.auth.UserTrack.Repository.UserRepository;
import com.auth.UserTrack.domain.*;
import com.auth.UserTrack.proxy.UserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class userServ implements UserService{

    private UserRepository userRepository;

//    private MongoOperations operations;
    private UserProxy userProxy;

    private UserRegisterDataRepository userRegisterDataRepository;

    @Autowired
    userServ(UserRepository userRepository ,UserProxy userProxy,UserRegisterDataRepository userRegisterDataRepository) {
        this.userRepository = userRepository;
        this.userProxy=userProxy;
        this.userRegisterDataRepository =userRegisterDataRepository;
    }

//    @Override
//    public User addUser(User user) {
////        System.out.println("user"+user);
//        return userRepository.save(user);
//    }

//    @Override
//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }

    @Override
    public List<Playlist> getUserByUserName(String userName) {
        return userRepository.findById(userName).get().getPlaylists();
        //get method is used here to check and get value,if present else throw no suchElementException rather than using Optional return type
    }

    @Override
    public void addPlaylist(String userName, String playlistName) throws PlaylistAlreadyExists {
        if(userRepository.checkTrack(userName,playlistName).isEmpty()) {
            userRepository.addTrack(userName, playlistName);
        }
        else{
            throw new PlaylistAlreadyExists();
        }
    }

    @Override
    public void deletePlaylist(String userName, String playlistName) {
        userRepository.deletePlaylistByName(userName,playlistName);
    }

    @Override
    public void register(UserRegister userRegister) {
        UserDto userDto=new UserDto();
        userDto.setUserName(userRegister.getEmail());
        userDto.setPassword(userRegister.getPassword());
        //System.out.println("userDto"+userDto);
        //thisproxy willadd data to authetication app
        userProxy.registerAccount(userDto);
        User user = new User();
        user.setUserName(userRegister.getEmail());
        user.setPassword(userRegister.getPassword());
        //this save is to save data in mongodb document usertrack
        userRepository.save(user);
        //this will save all register data to the register data document
        userRegisterDataRepository.save(userRegister);

    }

    @Override
    public void addNewSong(User user) throws SongAlreadyExistsException {
        List<Playlist> playlist = userRepository.findById(user.getUserName()).get().getPlaylists();
        for (int i =0;i<playlist.size();i++) {
            if(Objects.equals(playlist.get(i).getPlaylistName(), user.getPlaylists().get(0).getPlaylistName())){
                System.out.println(playlist.get(i).getPlaylistName());
                List<Song> son = playlist.get(i).getSongs();
                if(son == null){
                    List<Song> song = new ArrayList<>();
                    song.add(user.getPlaylists().get(0).getSongs().get(0));
                    playlist.get(i).setSongs(song);
                }
                else {
                    for (int j =0;j<son.size();j++) {
                        if (Objects.equals(son.get(i).getSongName(), user.getPlaylists().get(0).getSongs().get(0).getSongName())) {
                            throw new SongAlreadyExistsException();
                        }
                    }
                    son.add(user.getPlaylists().get(0).getSongs().get(0));
                    System.out.println(son);
                    playlist.get(i).setSongs(son);
                }
            }
        }
        User us = new User(user.getUserName(),user.getPassword(),playlist);
        userRepository.save(us);
    }

    @Override
    public void addDeleteSong(User user) {
        List<Playlist> playlist = userRepository.findById(user.getUserName()).get().getPlaylists();
        for (int i =0;i<playlist.size();i++) {
            if(playlist.get(i).getPlaylistName() == user.getPlaylists().get(0).getPlaylistName()){
                List<Song> son = playlist.get(i).getSongs();
                for (int j =0;j<son.size();j++){
                    if(son.get(i).getSongName() == user.getPlaylists().get(0).getSongs().get(0).getSongName()){
                        son.remove(j);
                    }
                }
                playlist.get(i).setSongs(son);
            }
        }
        User us = new User(user.getUserName(),user.getPassword(),playlist);
        userRepository.save(us);
    }




}
