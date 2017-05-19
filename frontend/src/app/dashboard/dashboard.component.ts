import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  openMenu: string;
  image
  constructor() {
    this.openMenu = localStorage.getItem('openMenu');
  }

  ngOnInit() {
  }
  setOpenMenu() {

    console.log(this.openMenu);
  }
}
