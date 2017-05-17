import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../model/user.model';

@Component({
  selector: 'app-left-menu',
  templateUrl: './left-menu.component.html',
  styleUrls: ['./left-menu.component.css']
})
export class LeftMenuComponent implements OnInit {
  curent_user: User;
  constructor(private _router: Router) {
    this.curent_user = JSON.parse(localStorage.getItem('user'));
  }

  ngOnInit() {
  }

  logout() {
    localStorage.removeItem('user');
    this._router.navigate(['']);
  }
}
