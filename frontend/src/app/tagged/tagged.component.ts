import { Component, OnInit } from '@angular/core';
import {TaggedService} from "./tagged.service";
import {BigMemory} from "../model/bigMemory.model";
import {Memory} from "../model/memory.model";
@Component({
  selector: 'app-tagged',
  templateUrl: './tagged.component.html',
  styleUrls: ['./tagged.component.scss'],
  providers: [TaggedService]
})
export class TaggedComponent implements OnInit {
  memories=[];
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
        this.retrieveData(data);
        console.log(data)
      },
      (err) => alert(err));
  }

  retrieveData(responseData: any) {
    for (let index in responseData) {
      let memory = new Memory(responseData[index]);
      this.memories.push(memory);
      console.log(memory);
    }
  }
}





