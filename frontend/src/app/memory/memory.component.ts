import { Component, OnInit } from '@angular/core';
import {MemoryService} from "./memory.service";
import {BigMemory} from "../model/bigMemory.model";
import {Tag} from "../model/tag.model";
import {User} from "../model/user.model";
import {Media} from "../model/media.model";

@Component({
  selector: 'app-memory',
  templateUrl: './memory.component.html',
  styleUrls: ['./memory.component.scss'],
  providers: [MemoryService]
})
export class MemoryComponent implements OnInit {
  aux:string;
  users =[];
  tags=[];
  medias=[];
  title: string;
  location: string;
  picture: string;
  description: string;
  date: string;
  //user: BigMemory;
  constructor(private memoryService: MemoryService) {
      this.aux=localStorage.getItem('memory');
      this.title=localStorage.getItem('memory-title');
      this.location=localStorage.getItem('memory-location');
      this.picture=localStorage.getItem('memory-picture');
      this.description=localStorage.getItem('memory-description');
      this.date=localStorage.getItem('memory-date');

  }

  ngOnInit() {
    this.seeMemory(this.aux);
  }

  seeMemory( memory: string ){
    this.memoryService.getMedia(memory).subscribe(
      (data) => {
           this.retrieveDataMedia(data);
         },
      (err) => alert(err));
    this.memoryService.getTagged(memory).subscribe(
      (data) => {
        this.retrieveDataUsers(data);
      },
      (err) => alert(err));
    this.memoryService.getTags(memory).subscribe(
      (data) => {
        this.retrieveDataTags(data);
      },
      (err) => alert(err));
  }
  retrieveDataTags(responseData: any) {
    for (let index in responseData) {
      let tag = new Tag(responseData[index]);
      this.tags.push(tag);
    }
  }

  retrieveDataUsers(responseData: any) {
    for (let index in responseData) {
      let user = new User(responseData[index]);
      this.users.push(user);
    }
  }

  retrieveDataMedia(responseData: any) {
    for (let index in responseData) {
      let media = new Media(responseData[index]);
      this.medias.push(media);
    }
    console.log(this.medias);
  }
}
