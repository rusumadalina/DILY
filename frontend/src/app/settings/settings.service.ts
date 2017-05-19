/**
 * Created by Andra on 5/15/2017.
 */
/**
 * Created by Andra on 5/7/2017.
 */
import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from "rxjs/Observable";

@Injectable()
export class SettingsService {
  name: string;
  constructor (private _http: Http) {

  }
  postJSON(settingsForm) {
    const json = JSON.stringify(
      {
        user_id: JSON.parse(localStorage.getItem('user'))['user_id'],
        name: settingsForm.name,
        username: settingsForm.username,
        password: settingsForm.password,
        email: settingsForm.email,
        dateOfBirth: settingsForm.dateOfBirth,
        country: settingsForm.country,
        city: settingsForm.city,
        profilePicture : this.name,
        gender: settingsForm.gender,
      });


    console.log(json);
    localStorage.setItem('user', json );
    const header =  new Headers();
    header.append('Content-Type', 'application/json');
    return this._http.post('http://localhost:8072/api/settings',  json, { headers: header }).map(res => res.json());
  }

  public makeFileRequest (url: string, params: string[], files: File[]){
    return Observable.create(observer => {
      const formData: FormData = new FormData(),
        xhr: XMLHttpRequest = new XMLHttpRequest();

      for (let i = 0; i < files.length; i++) {
        formData.append('uploads[]', files[i], files[i].name);
      }

      this.name = files[0].name;
      console.log(files[0].name);
      xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
          if (xhr.status === 200) {
            observer.next(JSON.parse(xhr.response));
            observer.complete();
          } else {
            observer.error(xhr.response);
          }
        }
      };
      xhr.open('POST', url, true);
      xhr.send(formData);
    });
  }

}//

