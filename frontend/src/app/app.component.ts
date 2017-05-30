import { Component, NgZone } from '@angular/core';
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
  toggle: boolean;
  width: any;
  constructor(private _router: Router, private ngZone:NgZone ){
      this.element=false;
      _router.events.subscribe((url:any) => {if(_router.url==='/'){
        this.element=true;
    }else{
      this.element=false;}
      }
    )
    this.toggle=true;
    window.onresize = (e) =>
    {
      //ngZone.run will help to run change detection
      this.ngZone.run(() => {
        this.width=window.innerWidth;
      });
    };
  }

  checkToggle(){
    let aux :string;
    aux=localStorage.getItem('toggle');
    if(aux==='true'){
      this.toggle=true;
    }else{
      this.toggle=false;
    }
    console.log(this.toggle);
  }
}
