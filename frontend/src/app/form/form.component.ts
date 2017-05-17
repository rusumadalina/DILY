import { Component, OnInit } from '@angular/core';
import {FormService} from './form.service';
import {User} from '../model/user.model';
import {Router} from '@angular/router';
@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
  providers: [FormService]
})
export class FormComponent {
  isLoggedIn: boolean;
  formValue: string;
  submited: boolean;
  d_username: string;
  d_password: string;

  constructor(private _httpService: FormService , private _router: Router) {
    this.isLoggedIn = true;
    this.submited = true;
    if (localStorage.getItem('loggedIn').toString() === 'true' ) {
        this.d_username = JSON.parse(localStorage.getItem('user'))('username');
        this.d_password = JSON.parse(localStorage.getItem('user'))('password');
    }else {
      this.d_username = '';
      this.d_password = '';
    }
  }
  submitForm(myform: any) {
    this.submited = true;
    this._httpService.postJSON(myform)
      .subscribe(
        data => {
          console.log(data);
          this.formValue = JSON.stringify(data);
          this.isLoggedIn = true;
          localStorage.setItem('user', this.formValue);
          localStorage.setItem('loggedIn', 'true');
          this._router.navigate(['dashboard']);
          },
        error => {
          this.isLoggedIn = false;
          localStorage.setItem('loggedIn', 'false');
        },
        () => console.log('finish')
      );
  }

}

// onTestGet() {
//   this._httpService.getCurrentTime()
//     .subscribe(
//       data => this.getData = JSON.stringify(data),
//       error => alert(error),
//       () => console.log('Finished')
//     );
// }
