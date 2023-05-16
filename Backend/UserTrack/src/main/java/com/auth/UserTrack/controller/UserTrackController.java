package com.auth.UserTrack.controller;

import com.auth.UserTrack.Exception.PlaylistAlreadyExists;
import com.auth.UserTrack.Exception.SongAlreadyExistsException;
import com.auth.UserTrack.domain.Playlist;
import com.auth.UserTrack.domain.Song;
import com.auth.UserTrack.domain.User;
import com.auth.UserTrack.domain.UserRegister;
import com.auth.UserTrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/Tracks/")
public class UserTrackController {

    private UserService userService;

    @Autowired
    UserTrackController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getUserTrack")
    public ResponseEntity<?> getUserTrack(HttpServletRequest httpServletRequest) {
        String userName = (String) httpServletRequest.getAttribute("val1");

        return new ResponseEntity<>(userService.getUserByUserName(userName), HttpStatus.OK);
    }
//@PostMapping("/getUserTrack")
//    public ResponseEntity<?> getUserTrack(@RequestBody User user) {
//       return new ResponseEntity<>(userService.getUserByUserName(user.getUserName()),HttpStatus.OK);
//    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUserDetails(@RequestBody UserRegister userRegister){
        userService.register(userRegister);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @PostMapping("/addUserTrack")
//    public ResponseEntity<?> addUserDetails(@RequestBody User user){
//        return new ResponseEntity<>(userService.addUser(user),HttpStatus.OK);
//    }

    @PostMapping("/addPlaylistName")
    public ResponseEntity<?> addNewPlaylist(HttpServletRequest httpServletRequest,@RequestBody Playlist playlist) throws PlaylistAlreadyExists {
        String userName = (String) httpServletRequest.getAttribute("val1");
        userService.addPlaylist(userName, playlist.getPlaylistName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/deletePlaylistName")
    public ResponseEntity<?> deletePlaylist(HttpServletRequest httpServletRequest,@RequestBody Playlist playlist) {
        String userName = (String) httpServletRequest.getAttribute("val1");
        userService.deletePlaylist(userName, playlist.getPlaylistName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addNewSong")
    public ResponseEntity<?> addNewSongToPlaylist(HttpServletRequest httpServletRequest,@RequestBody Playlist playlist) throws SongAlreadyExistsException {
        String userName = (String) httpServletRequest.getAttribute("val1");
        String password = (String) httpServletRequest.getAttribute("val2");
        List<Playlist>play = new ArrayList<>();
        play.add(playlist);
        User user = new User(userName,password, play);
        userService.addNewSong(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/deleteOneSong")
    public ResponseEntity<?> deleteOneSongFromPlaylist(HttpServletRequest httpServletRequest,@RequestBody Playlist playlist){
        String userName = (String) httpServletRequest.getAttribute("val1");
        String password = (String) httpServletRequest.getAttribute("val2");
        List<Playlist>play = new ArrayList<>();
        play.add(playlist);
        User user = new User(userName,password, play);
        userService.addDeleteSong(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
