import {Injectable} from "@angular/core";
import {User} from "../model/user.model";
import {Http, Headers} from "@angular/http";
@Injectable()
export class NewMemoryService {
  curent_user: User;

  constructor(private http: Http) {
    this.curent_user = JSON.parse(localStorage.getItem('user'));
  }

  getAllFriends() {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      'http://localhost:8072/api/newMemory/friends/' + this.curent_user.user_id,
      {headers: headers})
      .map(res => res.json());
  }
}
