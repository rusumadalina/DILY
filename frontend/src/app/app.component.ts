import { Component, NgZone } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'app works!';
  element: boolean;
  toggle: boolean;
  width: any;
  constructor(private _router: Router, private ngZone: NgZone ) {
      this.element = false;
      _router.events.subscribe((url: any ) => {if ( _router.url === '/' ) {
        this.element = true;
    }else {
      this.element = false; }
      }
    )

    window.onresize = (e) => {
      this.ngZone.run(() => {
        this.width = window.innerWidth;
      });
    };
    localStorage.removeItem('memory');
    localStorage.removeItem('memory-title');
    localStorage.removeItem('memory-location');
    localStorage.removeItem('memory-picture');
    localStorage.removeItem('memory-description');
    localStorage.removeItem('memory-date');
  }

  checkToggle() {
    let aux: string;
    aux = localStorage.getItem('toggle');
    if (aux === 'true') {
      this.toggle = true;
    }else {
      this.toggle = false;
    }
  }
}
