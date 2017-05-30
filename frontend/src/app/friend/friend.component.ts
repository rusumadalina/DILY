import { Component, OnInit } from '@angular/core';
import {FriendService} from './friend.service';
import {Friend} from '../model/friend.model';
import {Memory} from "../model/memory.model";

@Component({
  selector: 'app-friend',
  templateUrl: './friend.component.html',
  styleUrls: ['./friend.component.scss'],
  providers: [FriendService]
})
export class FriendComponent implements OnInit {
  friends = [];
  friendMemories = [];
  curent_user_id: number;
  friend: Friend;
  notEmpty: boolean;
  alert:boolean;
  constructor(private friendService: FriendService) {
    this.curent_user_id=JSON.parse(localStorage.getItem('user'))['user_id'];
    this.notEmpty = false;
    this.alert = false;
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

  deleteFriend(friend: number) {
    this.friendService.deleteFriend(this.curent_user_id, friend ).subscribe(
      (data) => {console.log('ti-ai sters prietenul hahahahha') },
      (err) => alert(err));
    window.location.reload();
  }

  viewMore(friend: number) {
    this.alert=false;
    this.friendService.viewFriend(friend).subscribe(
      (data) => {
        if(data.length===0){
          this.alert = true;
        }else{
          this.retrieveDataF(data);
          this.notEmpty=true;

        }
      },
      (err) => alert(err));
  }

  retrieveDataF(responseData: any) {
    for (let index in responseData) {
      let memory = new Memory(responseData[index]);
      this.friendMemories.push(memory);
    }
  }
  changeEmpty(){
    this.notEmpty=false;
    this.friendMemories=[];

  }
}
