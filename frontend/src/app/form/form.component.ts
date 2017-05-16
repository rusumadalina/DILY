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
  username: string;
  constructor(private _httpService: FormService , private _router: Router) {
    this.isLoggedIn = false;
    this.submited = false;
  }

  // onTestGet() {
  //   this._httpService.getCurrentTime()
  //     .subscribe(
  //       data => this.getData = JSON.stringify(data),
  //       error => alert(error),
  //       () => console.log('Finished')
  //     );
  // }

  submitForm(myform: any) {
    this.submited = true;
    this._httpService.postJSON(myform)
      .subscribe(
        data => {
          console.log(data);
          this.formValue = JSON.stringify(data);
          this.isLoggedIn = true;
          localStorage.setItem('user', this.formValue);
          this._router.navigate(['dashboard']);
          },
        error => {
          this.isLoggedIn = false;
        },
        () => console.log('finish')
      );
  }

}
