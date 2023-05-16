import { Component, OnInit } from '@angular/core';
import { SongsService } from '../service/songs.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css'],
})
export class MainPageComponent implements OnInit {
  constructor(private songserv: SongsService) {}
  ngOnInit(): void {
    this.songs = this.songserv.getallSongs();
  }
  songs: any;

  message() {
    alert('user has to login to play.');
  }
}
