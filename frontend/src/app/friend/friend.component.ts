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

  constructor(private friendService: FriendService) {

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
    }
  }
}
