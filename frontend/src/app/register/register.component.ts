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
  isEqual: boolean = true;
 
  constructor(private _httpService: RegisterService) { }

  ngOnInit() {
  }

  registerFunction(register: any) {
    console.log(register.gender);
    this._httpService.registerJSON(register)
      .subscribe(
        data => {this.registerValue = JSON.stringify(data) },
        error => {alert(error) },
        () => console.log('Finished')
      );
  }

  confirmPassword(register:any){
    if(register.password != register.confpassword){
      this.isEqual = false;
    }else{
      this.isEqual = true;
    }
  }

}
