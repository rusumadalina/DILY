/**
 * Created by Andra on 5/7/2017.
 */
import { Component, OnInit } from '@angular/core';
import {FormService} from './form.service';
import {User} from "../model/user.model";
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

                 this.formValue = JSON.stringify(data);
                 this.isLoggedIn = true;
                 this.user=new User(data['id'],data['username'],data['name'],data['password'],data['email'],data['dateOfBirth'], data['country'], data['city'],data['profilePicture'], data['gender']);
                localStorage.setItem('user',this.formValue);

                 },
        error => {alert(error); this.isLoggedIn = false; },
        () => console.log('finish')
      );
  }

}
//ca pt teste se pun parole usoaren unu..nu.n unununu stai
//de ceee formValue are si id si tot? ca nu inteleg /. ca il trimiti cu totul?eh in fine..
