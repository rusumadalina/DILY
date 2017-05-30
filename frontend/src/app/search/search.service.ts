import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {User} from "../model/user.model";
/**
 * Created by rusum on 29.05.2017.
 */
@Injectable()
export class SearchService{

  currentUser : User;

  constructor(private http: Http) {

    this.currentUser = JSON.parse(localStorage.getItem('user'));
  }
  search(type: string, word:string) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    if (type === 'tag'){
    return this.http.get(
      'http://localhost:8072/api/search/' + type + '/' + word,
      {headers: headers})
      .map(res => res.json());
  }else {
      return this.http.get(
        'http://localhost:8072/api/search/' + type + '/' + word + '/' + this.currentUser.user_id,
        {headers: headers})
        .map(res => res.json());
    }

  }

  addFriend(userId: number, username: string) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    console.log(btoa(username));
    return this.http.post(
      'http://localhost:8072/api/friends/add/' + userId + '/' + btoa(username),
      {headers: headers})
      .map(res => res.json());
  }

  viewFriend(username: string) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      'http://localhost:8072/api/search/newFriends/view/' + btoa(username) ,
      {headers: headers})
      .map(res => res.json());
  }
}
/*
* /api/search/tag/cuvant
* /api/search/friends
* /api/search/newFriends
* */
