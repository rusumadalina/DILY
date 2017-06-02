import { Component, OnInit } from '@angular/core';
import {FacebookService, InitParams, LoginResponse} from 'ngx-facebook';
import {FacebookSignUpService} from "./facebook-signup.service";
import {error} from "util";
@Component({
  selector: 'app-facebook-login',
  templateUrl: './facebook-login.component.html',
  styleUrls: ['./facebook-login.component.css'],
  providers:[FacebookSignUpService]
})
export class FacebookLoginComponent implements OnInit {
  id: string;
  token: string;
  response: any;
  constructor(private fb: FacebookService, private facebookSign: FacebookSignUpService) {

    let initParams: InitParams = {
      appId: '826899354145289',
      xfbml: true,
      version: 'v2.8'
    };

    fb.init(initParams);

  }

  ngOnInit() {
  }
  loginWithFacebook(): void {
    this.fb.login()
      .then((response: LoginResponse) =>this.sendInformation(response.authResponse.userID, response.authResponse.accessToken) )
      .catch((error: any) => console.error(error));

  }
  getProfile() {
    this.fb.api('/me/accounts')
      .then((res: any) => {
        console.log('Got the users profile', res.name);
      })
      .catch((error: any)=> console.error(error));
  }
  getFriends() {
    this.fb.api('/me/photos')
      .then((res: any) => {
        console.log('Got the users friends', res);
      })
      .catch((error: any)=> console.error(error));
  }


  sendInformation(id: string, token:string){
     this.facebookSign.sendlogin(id,token).subscribe(
       (data)=> console.log(data),
       (error)=> alert(error)
     );
  }
}
