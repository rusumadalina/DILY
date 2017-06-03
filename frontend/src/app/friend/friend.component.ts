import { Component, OnInit } from '@angular/core';
import {FriendService} from './friend.service';
import {Friend} from '../model/friend.model';
import {Memory} from "../model/memory.model";
import {Router} from "@angular/router";

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
  constructor(private friendService: FriendService, private  _router: Router) {
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
    this.friendMemories=[];
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

  seeMemory(id: number, title: string , location: string, picture: string, description: string, date: string, privacy:string){
    let aux: string;
    aux=id.toString();
    localStorage.setItem('memory',aux);
    localStorage.setItem('memory-title',title);
    localStorage.setItem('memory-location',location);
    localStorage.setItem('memory-picture',picture);
    localStorage.setItem('memory-description',description);
    localStorage.setItem('memory-date',date);
    localStorage.setItem('memomory-privacy', privacy);
    this._router.navigate(['../memories']);

  }
}
