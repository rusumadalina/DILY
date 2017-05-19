import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../model/user.model';

@Component({
  selector: 'app-left-menu',
  templateUrl: './left-menu.component.html',
  styleUrls: ['./left-menu.component.scss']
})
export class LeftMenuComponent implements OnInit {
  curent_user: User;
  profile: string;
  constructor(private _router: Router) {
    this.curent_user = JSON.parse(localStorage.getItem('user'));
    if (localStorage.getItem('name') === null){
      this.profile = JSON.parse(localStorage.getItem('user'))['profilePicture'];
    }else {
      this.profile = localStorage.getItem('name');
    }
    console.log(this.curent_user);
  }

  ngOnInit() {
  }

  logout() {
    localStorage.removeItem('user');
    localStorage.removeItem('name');
    this._router.navigate(['']);
  }
}
