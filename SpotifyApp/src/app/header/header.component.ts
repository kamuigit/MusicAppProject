import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  constructor(
    private auth: AuthenticationService,
    private jwtHelper: JwtHelperService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    //this code is used to refresh the component when we route to different component
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe(() => {
        this.loadData();
      });
  }
  loadData() {
    if (this.auth.getLoggingStatus()) {
      this.log = false;
      localStorage.getItem('jwt');
      this.decodedToken = this.jwtHelper.decodeToken(this.token);
      this.loginmail = this.decodedToken.userName;
    } else {
      this.log = true;
    }
  }
  loginmail: any;
  log = true;
  token: any;
  decodedToken: any;

  logOut() {
    let con: boolean = confirm('Do You Really Want to LogOut Of Your Account?');
    if (con) {
      this.router.navigateByUrl('/home');
      this.auth.loggedOut();
      localStorage.clear();
    } else {
    }
  }
}
