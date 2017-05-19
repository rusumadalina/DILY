import { Component, OnInit } from '@angular/core';
import {SettingsService} from 'app/settings/settings.service';
import {User} from '../model/user.model';
import {Http, Headers} from '@angular/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css'],
  providers: [SettingsService]
})

export class SettingsComponent implements OnInit {
  curent_user: User;
  settingValue: string;
  constructor(private _httpService: SettingsService, private _http: Http,  private _router: Router ) {
    this.curent_user = JSON.parse(localStorage.getItem('user'));

  }

  ngOnInit() {

  }
  submitSettings(settingsForm: any) {
    this._httpService.postJSON(settingsForm).subscribe(
      data => {console.log('ai postat'); },
      error => {console.log('nu ai postat'); }
    );

  }
  onChange(event) {
    const files = event.srcElement.files;
    this._httpService.makeFileRequest('http://localhost:8072/api/upload', [], files).subscribe(() => {
      console.log('sent');
    });
  }
}

