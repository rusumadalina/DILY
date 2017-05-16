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
        user_id: localStorage.getItem('user')['user_id'],
        name: settingsForm.name,
        username: settingsForm.username,
        password: settingsForm.password,
        email: settingsForm.email,
        dateOfBirth: settingsForm.dateOfBirth,
        country: settingsForm.country,
        city: settingsForm.city,
        profilePicture : localStorage.getItem('user')['profilePicture'],
        gender: settingsForm.gender,
        });
    console.log(json);
    const header =  new Headers();
    header.append('Content-Type', 'application/json');
    return this._http.post('http://localhost:8072/api/settings',  json, { headers: header }).map(res => res.json());
  }

}//

