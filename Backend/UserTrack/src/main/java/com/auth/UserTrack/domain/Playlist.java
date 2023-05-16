package com.auth.UserTrack.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
    @Id
    private String playlistName;
    private List<Song> songs;

}
