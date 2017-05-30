import { Component, OnInit } from '@angular/core';
import {FriendService} from './friend.service';
import {Friend} from '../model/friend.model';

@Component({
  selector: 'app-friend',
  templateUrl: './friend.component.html',
  styleUrls: ['./friend.component.scss'],
  providers: [FriendService]
})
export class FriendComponent implements OnInit {
  friends = [];
  curent_user_id:number;
  constructor(private friendService: FriendService) {
    this.curent_user_id=JSON.parse(localStorage.getItem('user'))['user_id'];
  }

  ngOnInit(): void {
    this.friendService.getAllFriends().subscribe(

      (data) => {this.retrieveData(data); },
      (err) => alert(err));
  }

  retrieveData(responseData: any) {
    for (let index in responseData) {
      let friend = new Friend(responseData[index]);
      this.friends.push(friend);
      console.log(friend);
    }
  }
  deleteFriend(friend: number){
    this.friendService.deleteFriend(this.curent_user_id,friend).subscribe(
      (data) => {console.log("ti-ai sters prietenul hahahahha") },
      (err) => alert(err));
    window.location.reload();
  }

}
