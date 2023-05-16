package com.auth.UserTrack.Repository;



import com.auth.UserTrack.domain.Song;
import com.auth.UserTrack.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User,String> {

    @Query("{userName:?0}")
    @Update("{$push:{ playlists:{$each:[{playlistName : ?1}]}}}")
    public void addTrack(String userName, String playListName);

    @Query("{userName: ?0}")
    @Update("{$pull:{playlists:{playlistName: ?1}}}")
    void deletePlaylistByName(String userName, String playlistName);

    @Query("{userName:?0 , playlists:{$elemMatch:{ playlistName: ?1}}}")
    public Optional<User> checkTrack(String userName, String playListName);
}
