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
  openMenu: string;
  constructor(private _router: Router) {
    this.curent_user = JSON.parse(localStorage.getItem('user'));
    localStorage.setItem('openMenu', 'true' );
    this.openMenu = 'true';
  }

  ngOnInit() {
  }

  logout() {
    localStorage.removeItem('user');
    this._router.navigate(['']);
  }
  setOpenMenu() {
    if (this.openMenu === 'true'){
      this.openMenu = 'false';
    }else {
      this.openMenu = 'true';
    }
    localStorage.setItem('openMenu', this.openMenu);
  }
}
