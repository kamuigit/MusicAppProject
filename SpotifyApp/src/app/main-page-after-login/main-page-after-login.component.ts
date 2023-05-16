import { Component, OnInit } from '@angular/core';
import { SongsService } from '../service/songs.service';
import { FormBuilder } from '@angular/forms';
import { UserDataService } from '../service/user-data.service';
import { userPlaylist, userPlaylistName, userSong } from 'src/model/UserData';

@Component({
  selector: 'app-main-page-after-login',
  templateUrl: './main-page-after-login.component.html',
  styleUrls: ['./main-page-after-login.component.css'],
})
export class MainPageAfterLoginComponent implements OnInit {
  constructor(
    private songserv: SongsService,
    private fb: FormBuilder,
    private serv: UserDataService
  ) {}
  ngOnInit(): void {
    this.songs = this.songserv.getallSongs();
    this.loadData();
  }

  songs: any;
  playlist: any;
  loadData() {
    this.serv.getUserData().subscribe((response) => {
      this.playlist = response;
      console.log(this.playlist);
    });
  }

  playlog = this.fb.group({
    playlistName: [''],
  });

  get playlistName() {
    return this.playlog.get('playlistName');
  }
  adPlaylist(data: any) {
    console.log(data.value.playlistName);

    let addplay: userPlaylistName = {
      playlistName: data.value.playlistName,
    };

    this.serv.addPlaylistName(addplay).subscribe(
      (response) => {
        alert('playlist Created' + data.value.playlistName);
        this.loadData();
      },
      (err) => {
        alert('no playlist added');
      }
    );
  }
  playlistTobeDeleted(data: any) {
    let deleteplay: userPlaylistName = {
      playlistName: data,
    };

    this.serv.deletePlaylist(deleteplay).subscribe(
      (resp) => {
        alert('playlist deleted');
        this.loadData();
      },
      (err) => {
        alert('unable to delete');
        console.log(err);
      }
    );
  }

  sname: userSong | any;
  getSongdata(data: any) {
    this.sname = data;
  }

  addNewSong(playlistName: any) {
    let addsong: userPlaylist = {
      playlistName: playlistName,
      songs: [
        {
          songName: this.sname.songName,
          songPic: this.sname.songPic,
          artistName: this.sname.artistName,
          duration: this.sname.duration,
          songLocation: this.sname.songLocation,
        },
      ],
    };
    this.serv.addNewSongToPlaylist(addsong).subscribe(
      (resp) => {
        alert('song added to playlist : ' + playlistName);
        this.loadData();
      },
      (err) => {
        alert('song unable to add');
      }
    );
  }

  playSong() {
    alert('song is playing...');
  }
}
