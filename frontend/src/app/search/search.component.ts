import { Component, OnInit } from '@angular/core';
import {SearchService} from "./search.service";
import {Memory} from "../model/memory.model";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
  providers: [SearchService]
})
export class SearchComponent implements OnInit {
  searchType: string;
  array=[];
  constructor(private searchService: SearchService) { }

  ngOnInit() {
  }

  setSearch(name: string){
    this.searchType = name;
    console.log(this.searchType);
  }

  searchW(search:any){
    this.searchService.search(this.searchType, search.searchWord ).subscribe(

      (data) => {this.retrieveData(data);  },
      (err) => alert(err));
  }
  retrieveData(responseData: any) {
    if(this.searchType==='tag'){
      for (let index in responseData) {
        let memory= new Memory(responseData[index]);
        this.array.push(memory);
      }
    }

  }
}
