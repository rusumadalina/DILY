import { Component, OnInit } from '@angular/core';
import {SettingsService} from 'app/settings/settings.service';
import {User} from '../model/user.model';
import {Http, Headers} from "@angular/http";
import {constructDependencies} from "@angular/core/src/di/reflective_provider";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css'],
  providers: [SettingsService]
})

export class SettingsComponent implements OnInit {
  curent_user: User;
  settingValue: string;
  constructor(private _httpService: SettingsService, private _http: Http ) {
    this.curent_user = JSON.parse(localStorage.getItem('user'));

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
    event.stopPropagation();
    console.log('onChange');
    const files = event.srcElement.files;
    console.log(files);
    this._httpService.makeFileRequest('http://localhost:8072/api/upload', [], files).subscribe(
      data => {
        console.log(data);
      },
        error => {
          console.log(error);
        },
        () => "update"
    );
    }

}

