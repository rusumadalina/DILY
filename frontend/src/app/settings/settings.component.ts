import { Component, OnInit } from '@angular/core';
import {SettingsService} from 'app/settings/settings.service';
import {User} from '../model/user.model';
import {Http, Headers} from "@angular/http";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css'],
  providers: [SettingsService]
})

export class SettingsComponent implements OnInit {
  curent_user: User;
  settingValue: string;
  // public _user_id: number;
  // public _username: string;
  // public _name: string;
  // public _password: string;
  // public _email: string;
  // public _dateOfBirth: string;
  // public _country: string;
  // public _city: string;
  // public _profilePicture: string;
  // public _gender: string;

  constructor(private _httpService: SettingsService, private _http: Http ) {
    this.curent_user = JSON.parse(localStorage.getItem('user'));
    // this._user_id = JSON.parse(localStorage.getItem('user'))['user_id'];
    // this._username =  JSON.parse(localStorage.getItem('user'))['username'];
    // this._name =  JSON.parse(localStorage.getItem('user'))['name'];
    // this._password =  JSON.parse(localStorage.getItem('user'))['password'];
    // this._email = JSON.parse(localStorage.getItem('user'))['email'];
    // this._dateOfBirth = JSON.parse(localStorage.getItem('user'))['dateOfBirth'];
    // this._country = JSON.parse(localStorage.getItem('user'))['country'];
    // this._city =  JSON.parse(localStorage.getItem('user'))['city'];
    // this._profilePicture =  JSON.parse(localStorage.getItem('user'))['profilePicture'];
    // this._gender =  JSON.parse(localStorage.getItem('user'))['gender'];
  }

  ngOnInit() {

  }
  submitSettings(settingsForm: any) {

    this._httpService.postJSON(settingsForm)
      .subscribe(
        data => {

          this.settingValue = JSON.stringify(data);
          console.log(data);
        },
        error => {alert(error); },
        () => console.log('Settings changed')
      );
  }
  onChange(event) {
    console.log('onChange');
    var files = event.srcElement.files;
    console.log(files);
    this._httpService.makeFileRequest('http://localhost:8072/api/upload', [], files).subscribe(() => {
      console.log('sent');
    });
  }
}

