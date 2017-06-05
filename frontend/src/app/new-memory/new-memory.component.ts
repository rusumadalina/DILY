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
  names=[];
  step:string;
  type:string;
  constructor(private newMemoryService: NewMemoryService, private eRef: ElementRef, private _router: Router) {
    this.toggle=false;
    this.curent_user = JSON.parse(localStorage.getItem('user'));
    this.files2=JSON.parse(localStorage.getItem('pictures2'));
    this.memId=localStorage.getItem('memory');
    if(localStorage.getItem('step')===null){
      localStorage.setItem('step','1');
      this.step='1';
      console.log(this.step);
    }else{
      this.step=localStorage.getItem('step');
    }
    if(localStorage.getItem('type')!= null){
      this.type=localStorage.getItem('type');
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
    this.step='2';
    localStorage.setItem('step','2');
  }

  selectFriends(id: number, name:string){
    let aux=false;
    // if(this.tagged.filter(tagg => tagg.name === this.tagged.name))
    for(let index of this.tagged){
      if(index === id){
        aux=true;
        this.tagged.splice(this.tagged.indexOf(index),1);
        for(let friend of this.names){
          if(friend === name){
            this.names.splice(this.names.indexOf(friend),1);
          }
        }
      }
    }
    if(aux===false){
      this.tagged.push(id);
      for(let friend of this.friends){
        if(friend.friendId === id){
          this.names.push(friend.name)
        }
      }

    }
    console.log(this.tagged);
    console.log(this.names)
  }

  submitTagged(){
      let array=[];
      for(let index of this.tagged){
        array.push({"friendId": index})
      }
    this.newMemoryService.postTagged(array, this.memId).subscribe(
      (data) => {
        localStorage.removeItem('pictures2');
        localStorage.removeItem('memoryId');

      },
      (err) => alert(err));

      localStorage.setItem('step','1');

  }

  submitTags(addTags: any){
    let array1= addTags.tags.split('#');

    for (let item of array1){
      if(item!=""){
        this.tags.push({"tagName":item});

      }
    }
    console.log(this.tags);
    this.newMemoryService.postTags(this.tags, this.memId).subscribe(

      (data) => {console.log(data); },
      (err) => alert(err));
    localStorage.setItem('step','4');
    this.step='4';
    localStorage.removeItem('pictures2');
  }

  onChangeMedia(event) {
    const files = event.srcElement.files;
    if(files[0].type==='video/mp4'){
      localStorage.setItem('type','video');
      this.type='video';
    }else{
      localStorage.setItem('type','image');
      this.type='image';
    }
    if(JSON.parse(localStorage.getItem('pictures2'))!=null){
      const aux=JSON.parse(localStorage.getItem('pictures2'));
      aux.push('assets/images/memory/'+files[0].name);
      localStorage.setItem('pictures2',JSON.stringify(aux));
    }else{
      const aux=[];
      aux.push('assets/images/memory/'+files[0].name);
      localStorage.setItem('pictures2',JSON.stringify(aux));
    }

    this.newMemoryService.makeFileRequestPictures('http://localhost:8072/api/newMemory/uploadPictures', [], files).subscribe(() => {
      console.log('sent');
    });
    localStorage.setItem('step','2');
  }
  submitMedia(){
    let aux=JSON.parse(localStorage.getItem('pictures2'));
    this.newMemoryService.postMedia(aux, this.memId).subscribe(

      (data) => {console.log(data); localStorage.removeItem('pictures2'); },
      (err) => alert(err));
    localStorage.setItem('step','3');
    this.step='3';
  }
  setStep(number:string){
    localStorage.setItem('step',number);
    this.step=localStorage.getItem('step');
  }
}
