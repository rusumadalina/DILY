import { Component, OnInit } from '@angular/core';
import {TaggedService} from "./tagged.service";
import {BigMemory} from "../model/bigMemory.model";
@Component({
  selector: 'app-tagged',
  templateUrl: './tagged.component.html',
  styleUrls: ['./tagged.component.css'],
  providers: [TaggedService]
})
export class TaggedComponent implements OnInit {

  curent_user_id: number;
  constructor(private taggedService:TaggedService) {
    this.curent_user_id=JSON.parse(localStorage.getItem('user'))['user_id'];
    this.seeTagged(this.curent_user_id);
  }

  ngOnInit() {
  }


  seeTagged( id: number){
    this.taggedService.viewTagged(id).subscribe(
      (data) => {
        //this.retrieveData(data);
        console.log(data)
      },
      (err) => alert(err));
  }
}





