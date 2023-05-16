export type userPlaylist = {
  playlistName: string;
  songs: [
    {
      songName: string;
      songPic: string;
      artistName: string;
      duration: string;
      songLocation: string;
    }
  ];
};

export type userPlaylistName = {
  playlistName: string;
};

export type userSong = {
  songName: string;
  songPic: string;
  artistName: string;
  duration: string;
  songLocation: string;
};
