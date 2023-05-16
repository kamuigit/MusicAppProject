import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../service/login.service';
import { UserDataService } from '../service/user-data.service';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css'],
})
export class RegistrationFormComponent {
  constructor(private fb: FormBuilder, private loginserv: UserDataService) {}

  register = this.fb.group({
    userName: ['', Validators.required],
    password: [
      '',
      [
        Validators.pattern(
          /(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z])(?=\D*\d)(?=.*?[\!\#\@\$\%\&\/\(\)\=\?\*\-\+\-\_\.\:\;\,\]\[\{\}\^])[A-Za-z0-9\!\#\@\$\%\&\/\(\)\=\?\*\-\+\-\_\.\:\;\,\]\[\{\}\^]{8,}$/
        ),
        Validators.required,
      ],
    ],
    email: ['', [Validators.required]],
    gender: ['', Validators.required],
    age: ['', [Validators.required]],
    phone: ['', Validators.required],
    address: ['', Validators.required],
  });
  get userName() {
    return this.register.get('userName');
  }
  get email() {
    return this.register.get('email');
  }
  get password() {
    return this.register.get('password');
  }

  get age() {
    return this.register.get('age');
  }

  get phone() {
    return this.register.get('phone');
  }

  registerData(data: any) {
    this.loginserv.registerAccount(data.value).subscribe((response) => {
      alert('register successfull');
    });
  }
}
