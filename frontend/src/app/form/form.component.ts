import { Component, OnInit } from '@angular/core';
import {FormService} from './form.service';
import {User} from '../model/user.model';
@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
  providers: [FormService]
})
export class FormComponent {
  isLoggedIn: boolean;
  formValue: string;
  user: User;
  constructor(private _httpService: FormService) {
    this.isLoggedIn = false;
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
    this._httpService.postJSON(myform)
      .subscribe(
        data => {
          console.log(data);
          this.formValue = JSON.stringify(data);
          if ( data['id'] === 0 ){
            this.isLoggedIn = false;
          }else {
            this.isLoggedIn = true;
            this.user = new User(data['id'], data['username'], data['name'], data['password'],
                          data['email'], data['dateOfBirth'], data['country'], data['city'], data['profilePicture'], data['gender']);
            localStorage.setItem('user', this.formValue);
          }
          console.log(this.isLoggedIn);


        },
        error => {
          alert(error); },
        () => console.log('finish')
      );
  }

}
