import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  constructor(private http: HttpClient) {}

  loggingStatus = false;

  loggedIn() {
    this.loggingStatus = true;
  }
  loggedOut() {
    this.loggingStatus = false;
  }
  getLoggingStatus() {
    return this.loggingStatus;
  }
}
