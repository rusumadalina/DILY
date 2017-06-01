import {Component, ElementRef, OnInit} from '@angular/core';
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
  toggle: boolean;
  text:string;
  alert2: boolean;
  aux: boolean;
  constructor(private searchService: SearchService, private _router: Router, private eRef: ElementRef ) {
    this.curent_user_id = JSON.parse(localStorage.getItem('user'))['user_id'];
    this.alert = false;
    this.notEmpty=false;
    this.alertMem=false;
    this.toggle=false;
    this.alert2=false;
    this.aux=false;
  }

  ngOnInit() {
  }

  searchW( search: any ) {
    this.searchService.search(this.searchType, search.searchWord ).subscribe(

      (data)=>{

        this.retrieveData(data);
        console.log(data) },
      (err) => alert(err));
  }

  retrieveData( responseData: any ) {
    this.array = [];
    this.notEmpty=false;
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
    if(this.array.length===0){
      this.aux=true;
    }
  }

  addFriend(user: string) {
    this.searchService.addFriend(this.curent_user_id, user ).subscribe(
      (data) => {
        this.alert = true;
        let timeoutId = setTimeout(() => {
          window.location.reload();
        }, 4000);
      },
      (err) => alert(err));
  }

  deleteFriend(user: string){
    this.searchService.deleteFriend(user ).subscribe(
      (data) => {
        if(data===1){
          this.alert2=true;
          let timeoutId = setTimeout(() => {
            window.location.reload();
          }, 4000);
        }
      },
      (err) => alert(err));
  }

  viewMore(friend: string) {
    this.alertMem=false;
    this.friendMemories=[];
    this.array=[];
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
      console.log(memory)
    }
  }
  changeEmpty(){
    this.notEmpty=false;
    this.friendMemories=[];

  }

  setSearch(name: string) {
    this.searchType = name;
    this.array = [];
    console.log(this.searchType);
    this.toggle=false;
  }
  setToggle(event) {
    let clickType: boolean;
    if (this.eRef.nativeElement.contains(event.target)) {
      clickType = true;
    } else {
      clickType = false;
    }

    if (clickType === false) {
      this.toggle = false;
    }
    if (clickType === true && this.toggle === false){
      this.toggle = true;
    }
  }

  seeMemory(id: number, title: string , location: string, picture: string, description: string, date: string){
    let aux: string;
    aux=id.toString();
    localStorage.setItem('memory',aux);
    localStorage.setItem('memory-title',title);
    localStorage.setItem('memory-location',location);
    localStorage.setItem('memory-picture',picture);
    localStorage.setItem('memory-description',description);
    localStorage.setItem('memory-date',date);
    // this._router.navigate(['../memories']);

  }

}
