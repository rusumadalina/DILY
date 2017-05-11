import { Component, OnInit } from '@angular/core';
import {RegisterService} from './register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [RegisterService]
})
export class RegisterComponent implements OnInit {
  registerValue : string;
  constructor(private _httpService: RegisterService) { }

  ngOnInit() {
  }

  registerFunction(register: any) {
    this._httpService.registerJSON(register)
      .subscribe(
        data => {this.registerValue = JSON.stringify(data) },
        error => {alert(error) },
        () => console.log('Finished')
      );
  }
}
