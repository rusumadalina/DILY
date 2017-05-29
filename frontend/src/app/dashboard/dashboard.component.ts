import { Component, OnInit } from '@angular/core';
import {DashboardService} from './dashboard.service';
import {Memory} from "../model/memory.model";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  providers: [DashboardService]
})
export class DashboardComponent implements OnInit {
  memories = [];


  constructor(private dashboardService: DashboardService) {

  }

  ngOnInit(): void {
    this.dashboardService.getAllMemories().subscribe(

        (data) => {this.retrieveData(data); console.log(data) },
        (err) => alert(err));

  }

  retrieveData(responseData: any) {
    for (let index in responseData) {
      let memory = new Memory(responseData[index]);
      this.memories.push(memory);
    }
  }

  deleteMemory(idMem : number){
    this.dashboardService.deleteMemory(idMem).subscribe(

      (data) => {console.log(data) },
      (err) => alert(err));
  }
}

