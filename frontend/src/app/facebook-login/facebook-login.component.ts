import { Component, OnInit } from '@angular/core';
import {FacebookService, InitParams, LoginResponse, LoginOptions} from 'ngx-facebook';
import {FacebookSignUpService} from "./facebook-signup.service";
import {Http} from "@angular/http";
import {Router} from "@angular/router";

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
  items=[];
  send=[];
  id2:string;
  constructor(private fb: FacebookService, private facebookSign: FacebookSignUpService,private http: Http, private _router: Router) {

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
    this.fb.api('/me?fields=id,name,birthday,hometown,gender,email,picture.height(2048){url}')
      .then((res: any) => {
        this.id = res.id;
        console.log(res.id);
        this.name=res.name;
        this.birthday=res.birthday;
        this.hometown=res.hometown.name;
        this.email=res.email;
        this.profilePicture=res.picture.data.url;
        this.gender=res.gender;
        this.sendAllData();

      })
      .catch((error: any)=> console.error(error));
  }
  sendAllData(){
    this.facebookSign.facebookSendData(this.id, this.name, this.birthday, this.hometown, this.email, this.profilePicture, this.gender)
      .subscribe(
        (data) => {console.log(data)
          localStorage.setItem('user', JSON.stringify(data));
          localStorage.setItem('loggedIn', 'true');
          localStorage.setItem('fbId', this.id);
          this.id2=data['user_id'];
          console.log(this.id2);
          this.getPhotos();
          this._router.navigate(['dashboard']);
        },
        (error) => console.log(error)
      )
  }
  getPhotos(){
    this.fb.api('me/posts?fields=attachments{title,media},privacy,description,place,created_time&limit=20')
      .then((res: any) => {
        this.retrieveData(res.data);
      })
      .catch((error: any)=> console.error(error));
  }
  retrieveData(response:any) {
    for (let index of response) {
      //let item=new FbMemory(index);
      //this.items.push(item);
      let title = 'undefined';
      let description = 'undefined';
      let memoryLocation = 'undefined';
      let date = 'undefined';
      let privacy = 'undefined';
      let mainPicture = 'undefined';
      if (index['attachments'].data[0].title != null) {
        title = index['attachments'].data[0].title;
      }
      if (index.description != null) {
        description = index.description;
      }
      if (index.place != null) {
        memoryLocation = index.place['name'];
      }
      if (index.created_time != null) {
        date = index.created_time;
      }
      if (index.privacy != null) {
        privacy = index.privacy['value'];
      }
      if (index['attachments'].data[0].media != null) {
        mainPicture = index['attachments'].data[0].media.image.src;
      }
      this.send.push({
        "title": title,
        "description": description,
        "memoryLocation": memoryLocation,
        "date": date,
        "privacy": privacy,
        "mainPicture": mainPicture
      });
    }
    this.facebookSign.postJSON(this.send, this.id2).subscribe(
      (data)=> console.log(data),
      (error)=>console.log(error)
    )
  }
    sendFbMemory(){

    }

  sendInformation(id: string, token:string){
    this.facebookSign.sendlogin(id,token).subscribe(
      (data)=> console.log(data),
      (error)=> alert(error)
    );
 }
}
