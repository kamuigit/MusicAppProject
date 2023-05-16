import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';
import { user } from 'src/model/User';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent {
  constructor(
    private fb: FormBuilder,
    private serv: LoginService,
    private route: Router,
    private auth: AuthenticationService
  ) {}

  login = this.fb.group({
    userName: ['', Validators.required],
    password: ['', Validators.required],
  });

  get userName() {
    return this.login.get('userName');
  }

  get password() {
    return this.login.get('password');
  }
  responsedata: any;

  loginCheck(data: any) {
    console.log(data.value.userName + ' ' + data.value.password);
    let logindata: user = {
      userName: data.value.userName,
      password: data.value.password,
    };
    this.serv.loginAccount(logindata).subscribe(
      (response) => {
        this.responsedata = response; //in response we get username + password
        console.log(this.responsedata.Token);
        alert(this.responsedata.Message);

        ///to store token in bbrowser storage
        localStorage.setItem('jwt', this.responsedata.Token);
        this.auth.loggedIn();
        this.route.navigateByUrl('/userHasLoggedIn');
      },
      (err) => {
        alert('invalid login details');
        location.reload();
      }
    );
  }
}
