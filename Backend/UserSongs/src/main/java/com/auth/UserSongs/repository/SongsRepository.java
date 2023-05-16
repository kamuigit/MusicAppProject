package com.auth.UserSongs.repository;

import com.auth.UserSongs.domain.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongsRepository extends MongoRepository<Song,String> {

}
