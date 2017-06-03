/**
 * Created by rusum on 02.06.2017.
 */
import {Injectable} from "@angular/core";
import {Http , Headers} from "@angular/http";
import {User} from "../model/user.model";
/**
 * Created by rusum on 31.05.2017.
 */
@Injectable()
export class FacebookSignUpService {
  curent_user: User;
  constructor(private http: Http) {
    this.curent_user = JSON.parse(localStorage.getItem('user'));
  }



  sendlogin(id: string, token: string){
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      'http://localhost:8072/api/facebook/signup/' + btoa(id)+'/'+btoa(token),
      {headers: headers})
      .map(res => res.json());
  }

  facebookSendData(id, name, birthday, hometown, email, profilePicture, gender){
    const hmt= hometown.split(',');
    const city=hmt[0];
    const country=hmt[1];
    const json = JSON.stringify(
      {
        id: id,
        name: name ,
        email: email,
        city: city,
       country: country,
       birth: birthday,
       gender: gender,
        profilePicture: profilePicture
      });
    const header =  new Headers();
    header.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8072/api/registerFacebook',  json, { headers: header }).map(res => res.json());
  }

  postJSON(information) {
    const header =  new Headers();
    header.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8072/api/addNewFbMemory/'+this.curent_user.user_id,  information, { headers: header }).map(res => res.json());
  }
}
