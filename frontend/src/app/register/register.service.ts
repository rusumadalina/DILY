/**
 * Created by Andra on 5/7/2017.
 */
import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
@Injectable()
export class RegisterService{
  constructor (private _http: Http){    }

  registerJSON(register){
    const json = JSON.stringify({name: register.name , username: register.username, password: register.password, email: register.email,  city: register.city, country: register.country,  birth: register.birth, sex: register.sex});
    const header =  new Headers();
    header.append('Content-Type', 'application/json');
    return this._http.post('http://localhost:8072/api/register',  json, { headers: header }).map(res => res.json());
  }

}


//cred ca da asa pt ca tu din back nu ii trimiti raspuns, si el asta asteapta, si pt ca nuu ii dai nimic stai ca ii dau imediat
