import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Url} from "url";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'app works!';
  element: boolean;
  constructor(private _router: Router ){
      this.element=false;
      _router.events.subscribe((url:any) => {if(_router.url==='/'){
        this.element=true;
    }else{
      this.element=false;}
      }
    )

  }

}
