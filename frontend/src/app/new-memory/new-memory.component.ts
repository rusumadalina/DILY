import {Component, ElementRef, OnInit} from '@angular/core';
import {NewMemoryService} from "./new-memory.service";
import {Friend} from "../model/friend.model";
import {User} from "../model/user.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-memory',
  templateUrl: './new-memory.component.html',
  styleUrls: ['./new-memory.component.scss'],
  providers: [NewMemoryService ]
})
export class NewMemoryComponent implements OnInit {
  friends = [];
  toggle : boolean;
  text: string;
  curent_user: User;
  tagged =[];
  memId : string;
  tags=[];
  files=[];
  files2=[];
  curentStep: number;
  constructor(private newMemoryService: NewMemoryService, private eRef: ElementRef, private _router: Router) {
    this.toggle=false;
    this.curent_user = JSON.parse(localStorage.getItem('user'));
    this.files2=JSON.parse(localStorage.getItem('pictures'));
    this.memId=localStorage.getItem('memory');
    this.curentStep=1;
    if(localStorage.getItem('step')===null){
      localStorage.setItem('step','1');
    }else{
      this.curentStep = parseInt(localStorage.getItem('step'));
    }
  }

  ngOnInit() {
    this.newMemoryService.getAllFriends().subscribe(

      (data) => {this.retrieveData(data); },
      (err) => alert(err));
  }
  retrieveData(responseData: any) {
    for (let index in responseData) {
      let friend = new Friend(responseData[index]);
      this.friends.push(friend);
      console.log(friend);
    }
  }

  setToggle(event) {
    let clickType: boolean;
    if (this.eRef.nativeElement.contains(event.target)) {
      clickType = true;
    } else {
      clickType = false;
    }

    if (clickType === false) {
      this.toggle = false;
    }
    if (clickType === true && this.toggle === false){
      this.toggle = true;
    }
  }

  setSearch(name: string) {
    this.text = name;
    this.toggle=false;
    localStorage.setItem('privacyMem', this.text);
  }

  onChange(event) {
    const files = event.srcElement.files;
    this.newMemoryService.makeFileRequest('http://localhost:8072/api/newMemory/upload', [], files).subscribe(() => {
      console.log('sent');
    });
  }
  submitMemory(addMemory: any) {
    this.newMemoryService.postJSON(addMemory).subscribe(
      data => {
        this.memId=data.toString();
        let aux=data.toString();
        localStorage.setItem('memory',aux);
        console.log(data);},
      error => {console.log('nu ai postat'); }
    );
    //this.curentStep=parseInt(localStorage.getItem('step'));
    localStorage.setItem('step','2');
    this.curentStep=parseInt(localStorage.getItem('step'));
    //this._router.navigate(['dashboard']);

  }
  selectFriends(id: number){
    let aux=false;
   // if(this.tagged.filter(tagg => tagg.name === this.tagged.name))

    for(let index of this.tagged){
      if(index === id){
        aux=true;
        this.tagged.splice(this.tagged.indexOf(index),1);
      }
    }
    if(aux===false){
      this.tagged.push(id);
    }
    console.log(this.tagged);
  }
  submitTagged(){
    this.curentStep=parseInt(localStorage.getItem('step'));
      let array=[];
      for(let index of this.tagged){
        array.push({"friendId": index})
      }
    this.newMemoryService.postTagged(array, this.memId).subscribe(
      (data) => {
        localStorage.removeItem('pictures');
        localStorage.removeItem('memoryId');
        localStorage.removeItem('step');
      },
      (err) => alert(err));


  }

  submitTags(addTags: any){
    this.curentStep=parseInt(localStorage.getItem('step'));
    let array1= addTags.tags.split(' ');

    for (let item of array1){
      this.tags.push({"tagName":item.split('#')[1]});
    }
    console.log(this.tags);
    this.newMemoryService.postTags(this.tags, this.memId).subscribe(

      (data) => {console.log(data); },
      (err) => alert(err));
    localStorage.setItem('step','4');
    this.curentStep=parseInt(localStorage.getItem('step'));
  }

  onChangeMedia(event) {
    const files = event.srcElement.files;

    if(JSON.parse(localStorage.getItem('pictures'))!=null){
      const aux=JSON.parse(localStorage.getItem('pictures'));
      aux.push('assets/images/memory/'+files[0].name);
      localStorage.setItem('pictures',JSON.stringify(aux));
    }else{
      const aux=[];
      aux.push('assets/images/memory/'+files[0].name);
      localStorage.setItem('pictures',JSON.stringify(aux));
    }

    this.newMemoryService.makeFileRequestPictures('http://localhost:8072/api/newMemory/uploadPictures', [], files).subscribe(() => {
      console.log('sent');
    });
    localStorage.setItem('step','2');
  }
  submitMedia(){
    let aux=JSON.parse(localStorage.getItem('pictures'));
    this.newMemoryService.postMedia(aux, this.memId).subscribe(

      (data) => {console.log(data); localStorage.removeItem('pictures'); },
      (err) => alert(err));
    localStorage.setItem('step','3');
    this.curentStep=parseInt(localStorage.getItem('step'));
  }
}
