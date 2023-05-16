import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private http: HttpClient) {}

  url = 'http://localhost:4500/api/user/';

  loginAccount(data: any) {
    return this.http.post(this.url + 'login', data);
  }
}
