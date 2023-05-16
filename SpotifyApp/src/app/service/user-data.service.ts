import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserDataService {
  constructor(private http: HttpClient) {}

  url = 'http://localhost:4555/api/Tracks/';

  public getRequestOptions() {
    let httpHeader = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('jwt'),
    });
    let requestOptions = { headers: httpHeader };
    return requestOptions;
  }

  public getUserData() {
    return this.http.get(this.url + 'getUserTrack', this.getRequestOptions());
  }

  public getUserData2() {
    return this.http.get(this.url);
  }
  public addPlaylistName(data: any) {
    return this.http.post(
      this.url + 'addPlaylistName',
      data,
      this.getRequestOptions()
    );
  }

  public registerAccount(data: any) {
    return this.http.post(
      this.url + 'register',
      data,
      this.getRequestOptions()
    );
  }

  public deletePlaylist(data: any) {
    return this.http.post(
      this.url + 'deletePlaylistName',
      data,
      this.getRequestOptions()
    );
  }
  public addNewSongToPlaylist(data: any) {
    return this.http.post(
      this.url + 'addNewSong',
      data,
      this.getRequestOptions()
    );
  }
}
