import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SongsService {
  constructor(private http: HttpClient) {}

  url = 'http://localhost:4557/getAllSongs';

  public getallSongs() {
    return this.http.get(this.url);
  }
}
