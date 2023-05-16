package com.auth.UserSongs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Song {
    @Id
    private String songName;
    private String songPic;
    private String artistName;
    private String duration;
    private String songLocation;
}
