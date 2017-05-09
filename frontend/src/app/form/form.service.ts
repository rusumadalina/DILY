/**
 * Created by Andra on 5/7/2017.
 */
import { Injectable } from '@angular/core';
import {Http,Headers} from '@angular/http';
import 'rxjs/add/operator/map'
@Injectable()
export class FormService{
  constructor (private _http: Http){    }
  getCurrentTime(){
    return this._http.get('http://date.jsontest.com')
      .map(res => res.json());
  }
  postJSON(myform){
    console.log(myform.username);
    console.log(myform.password);
    var json = JSON.stringify({username:myform.username,password:myform.password});
    var params = '' + json;

    var header =  new Headers();
    header.append('Content-Type','application/json');
    console.log(params);
    return this._http.post('http://localhost:8072/api/login',  json, { headers: header }).map(res=>res.json());
  }

}
