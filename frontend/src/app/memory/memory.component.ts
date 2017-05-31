import { Component, OnInit } from '@angular/core';
import {MemoryService} from "./memory.service";
import {Tag} from "../model/tag.model";
import {User} from "../model/user.model";
import {Media} from "../model/media.model";
import {BigMemory} from "../model/bigMemory.model";

@Component({
  selector: 'app-memory',
  templateUrl: './memory.component.html',
  styleUrls: ['./memory.component.scss'],
  providers: [MemoryService]
})
export class MemoryComponent implements OnInit {
  aux:string;
  user:BigMemory;
  constructor(private memoryService: MemoryService) {
    this.aux=localStorage.getItem('memory');
    this.seeMemory(this.aux);
  }

  ngOnInit() {

  }

  seeMemory( memory: string ){
    this.memoryService.seeMoreMemory(memory).subscribe(
      (data) => {
            this.retrieveData(data);
         },
      (err) => alert(err));
  }
  retrieveData(responseData: any) {
    for (let index in responseData) {
      this.user = new BigMemory(responseData[index]);

    }
    console.log(this.user);
  }
}
