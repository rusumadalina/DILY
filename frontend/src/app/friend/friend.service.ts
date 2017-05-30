/**
 * Created by Andra on 5/25/2017.
 */
import {Http, Headers} from '@angular/http';
import {Injectable} from '@angular/core';
import {User} from '../model/user.model';
@Injectable()
export class FriendService {
  curent_user: User;
  constructor(private http: Http) {
    this.curent_user = JSON.parse(localStorage.getItem('user'));
  }
  getAllFriends() {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      'http://localhost:8072/api/friends/' + this.curent_user.user_id,
      {headers: headers})
      .map(res => res.json());
  }

  deleteFriend(user1Id:number, user2Id:number){
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      'http://localhost:8072/api/friends/delete/' + user1Id + '/' + user2Id,
      {headers: headers})
      .map(res => res.json());
  }
}
