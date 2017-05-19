import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {
  toggledForm: number;
  constructor() {
    this.toggledForm = 1;
  }

  ngOnInit() {
  }


  toggleForm(newValue: number) {
    if (this.toggledForm === newValue) {
      this.toggledForm = 0;
    }else {
      this.toggledForm = newValue;
    }
  }
}
