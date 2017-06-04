import { Component, OnInit } from '@angular/core';
import {RegisterService} from './register.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  providers: [RegisterService]
})
export class RegisterComponent implements OnInit {
  registerValue : string;
  isEqual: boolean = true;

  constructor(private _httpService: RegisterService, private _router: Router) { }

  ngOnInit() {
  }

  registerFunction(register: any) {
    console.log(register.gender);
    this._httpService.registerJSON(register)
      .subscribe(
        data => {
          this.registerValue = JSON.stringify(data);
          this._router.navigate(['../']);
          },
        error => {alert(error) },
        () => console.log('Finished')
      );
    window.location.reload();
  }

  confirmPassword(register:any){
    if(register.password != register.confpassword){
      this.isEqual = false;
    }else{
      this.isEqual = true;
    }
  }

}
