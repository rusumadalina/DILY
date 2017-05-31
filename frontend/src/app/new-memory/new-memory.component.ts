import { Component, OnInit } from '@angular/core';
import {NewMemoryService} from "./new-memory.service";
import {Friend} from "../model/friend.model";

@Component({
  selector: 'app-new-memory',
  templateUrl: './new-memory.component.html',
  styleUrls: ['../settings/settings.component.scss'],
  providers: [NewMemoryService ]
})
export class NewMemoryComponent implements OnInit {
  friends = [];
  constructor(private newMemoryService: NewMemoryService) { }

  ngOnInit() {
    this.newMemoryService.getAllFriends().subscribe(

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

}
