import { Component, OnInit } from '@angular/core';
import {SettingsService} from 'app/settings/settings.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css'],
  providers: [SettingsService]
})
export class SettingsComponent implements OnInit {
  settingValue: string;
  constructor(private _httpService: SettingsService) { }

  ngOnInit() {
  }
  submitSettings(settingsForm: any) {
    console.log(settingsForm.value);
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
}
//care e
