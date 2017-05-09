/**
 * Created by Andra on 5/7/2017.
 */
import { Component, OnInit } from '@angular/core';
import {FormService} from './form.service'
@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
  providers: [FormService]
})
export class FormComponent {
  getData: string;
  postData: string;
  formValue: string;

  constructor(private _httpService: FormService) {
  }

  onTestGet() {
    this._httpService.getCurrentTime()
      .subscribe(
        data => this.getData = JSON.stringify(data),
        error => alert(error),
        () => console.log("Finished")
      );
  }

  submitForm(myform: any) {
    this._httpService.postJSON(myform)
      .subscribe(
        data => this.formValue = JSON.stringify(data),
        error => alert(error),
        () => console.log("Finished")
      );
  }

}
