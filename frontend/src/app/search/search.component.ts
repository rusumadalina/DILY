import { Component, OnInit } from '@angular/core';
import {SearchService} from "./search.service";
import {Memory} from "../model/memory.model";
import {Friend} from "../model/friend.model";
import {Router} from "@angular/router";
import {dashCaseToCamelCase} from "@angular/compiler/src/util";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
  providers: [SearchService]
})
export class SearchComponent implements OnInit {
  searchType: string;
  array = [];
  alert: boolean;
  curent_user_id: number;
  friendMemories = [];
  notEmpty :boolean;
  alertMem: boolean;
  constructor(private searchService: SearchService, private _router: Router ) {
    this.curent_user_id = JSON.parse(localStorage.getItem('user'))['user_id'];
    this.alert = false;
    this.notEmpty=false;
    this.alertMem=false;

  }

  ngOnInit() {
  }

  setSearch(name: string) {
    this.searchType = name;
    this.array = [];
    console.log(this.searchType);
  }

  searchW( search: any ) {
    this.array = [];
    this.searchService.search(this.searchType, search.searchWord ).subscribe(

      (data) => {
        this.retrieveData(data);
        console.log(data) },
      (err) => alert(err));
  }

  retrieveData( responseData: any ) {
    this.array = [];
    if( this.searchType === 'tag' ) {
      for (let index in responseData) {
        let memory= new Memory(responseData[index]);
        this.array.push(memory);
      }
    }else {
      for (let index in responseData) {
        let friend= new Friend(responseData[index]);
        this.array.push(friend);
      }
    }

  }

  addFriend(user: string) {
    this.searchService.addFriend(this.curent_user_id, user ).subscribe(
      (data) => {
        this.alert = true;
        let timeoutId = setTimeout(() => {
          this.alert = false;
        }, 3000);
      },
      (err) => alert(err));
  }

  viewMore(friend: string) {
    this.alertMem=false;
    this.searchService.viewFriend(friend).subscribe(
      (data) => {
        if(data.length===0){
          this.alertMem =true;
        }else{
          this.retrieveDataF(data);
          this.notEmpty = true;
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
