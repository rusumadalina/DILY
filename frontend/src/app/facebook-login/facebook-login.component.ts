import { Component, OnInit } from '@angular/core';
import {FacebookService, InitParams, LoginResponse, LoginOptions} from 'ngx-facebook';
import {FacebookSignUpService} from "./facebook-signup.service";
import {error} from "util";
import {Http} from "@angular/http";
import {FbMode} from "../model/fb.model";
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
  name: string;
  hometown: string;
  birthday: string;
  email:string;
  gender: string;
  profilePicture: string;
  pictures=[];

  constructor(private fb: FacebookService, private facebookSign: FacebookSignUpService,private http: Http) {

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
    const options: LoginOptions = {
      scope: 'public_profile,user_friends,email,pages_show_list',
      return_scopes: true,
      enable_profile_selector: true
    };
    this.fb.login(options)
    //this.sendInformation(response.authResponse.userID, response.authResponse.accessToken)
      .then((response: LoginResponse) => console.log(response))
      .catch((error: any) => console.error(error));

  }
  getProfile() {
    this.fb.api('/me?fields=id,name,birthday,hometown,gender,email,picture{url}')
      .then((res: any) => {
        this.name=res.name;
        this.birthday=res.birthday;
        this.hometown=res.hometown.name;
        this.email=res.email;
        this.profilePicture=res.picture.data.url;
        this.gender=res.gender;

      })
      .catch((error: any)=> console.error(error));
  }
  sendAllData(){
    this.facebookSign.facebookSendData(this.id, this.name, this.birthday, this.hometown, this.email, this.profilePicture, this.gender)
      .subscribe(
        (data) => console.log(data),
        (error) => console.log(error)
      )
  }
  getPhotos(){
    this.fb.api('me/photos?fields=picture&limit=300&type=uploaded')
      .then((res: any) => {
        this.retrieveData(res.data);
      })
      .catch((error: any)=> console.error(error));
  }
  retrieveData(response:any) {
    for (let index of response) {
      let item=new FbMode(index);
      this.pictures.push(item);
    }
    console.log(this.pictures);
  }
  sendInformation(id: string, token:string){
     // this.facebookSign.sendlogin(id,token).subscribe(
     //   (data)=> console.log(data),
     //   (error)=> alert(error)
     // );
  }
}
