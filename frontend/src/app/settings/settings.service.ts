/**
 * Created by Andra on 5/15/2017.
 */
/**
 * Created by Andra on 5/7/2017.
 */
import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
@Injectable()
export class SettingsService {
  constructor (private _http: Http) {    }
  postJSON(settingsForm) {
    const json = JSON.stringify(
      {
        
        name: settingsForm.name,
        username: settingsForm.username,
        password: settingsForm.password,
        email: settingsForm.email,
        birth: settingsForm.birth,
        country: settingsForm.country,
        city: settingsForm.city,
        profile: settingsForm.profile,
        sex: settingsForm.sex
        });
    const header =  new Headers();
    header.append('Content-Type', 'application/json');
    return this._http.post('http://localhost:8072/api/settings',  json, { headers: header }).map(res => res.json());
  }

}

