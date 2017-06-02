import {Component, ElementRef, OnInit} from '@angular/core';
import {Tag} from "../model/tag.model";
import {User} from "../model/user.model";
import {Media} from "../model/media.model";
import {MemoryService} from "../memory/memory.service";
import {AddMemory} from "../model/addMemory.model";
import {NewMemoryService} from "../new-memory/new-memory.service";
import {EditMemoryService} from "./edit-memory.service";
import {Friend} from "../model/friend.model";

@Component({
  selector: 'app-edit-memory',
  templateUrl: './edit-memory.component.html',
  styleUrls: ['./edit-memory.component.scss'],
  providers:[MemoryService, NewMemoryService, EditMemoryService]
})
export class EditMemoryComponent implements OnInit {
  tags=[];
  localTags=[];
  users=[];
  medias=[];
  id:string;
  text:string;
  curentMemory: AddMemory;
  files2=[];
  toggle: boolean;
  localFriends=[];
  tagged=[];
  constructor(private memoryService: MemoryService,  private eRef: ElementRef, private newMemoryService: NewMemoryService, private editMemoryService: EditMemoryService) {
    this.curentMemory= new AddMemory(localStorage.getItem('memory-title'),localStorage.getItem('memory-description'),localStorage.getItem('memory-location'),localStorage.getItem('memory-date'),localStorage.getItem('memory-privacy'),localStorage.getItem('memory-picture'));
    this.id=localStorage.getItem('memory');
    this.seeMemory(this.id);
    this.toggle=false;
    this.files2=JSON.parse(localStorage.getItem('pictures'));
  }

  ngOnInit() {
    this.newMemoryService.getAllFriends().subscribe(
      (data) => {this.retrieveData4(data) },
      (err) => alert(err));
  }

  retrieveData4(responseData: any) {
    for (let index in responseData) {
      let friend = new Friend(responseData[index]);
      this.localFriends.push(friend);
    }
  }
  submitTagged(){
       let array=[];
    for(let index of this.tagged){
      array.push({"friendId": index})
    }
    this.newMemoryService.postTagged(array, this.id).subscribe(
      (data) => {
            console.log(data);
      },
      (err) => alert(err));


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


  seeMemory(id: string ){
    this.memoryService.getMedia(id).subscribe(
      (data) => {
        this.retrieveDataMedia(data);
      },
      (err) => alert(err));
    this.memoryService.getTagged(id).subscribe(
      (data) => {
        this.retrieveDataUsers(data);
      },
      (err) => alert(err));
    this.memoryService.getTags(id).subscribe(
      (data) => {
        this.retrieveDataTags(data);
      },
      (err) => alert(err));
  }
  retrieveDataTags(responseData: any) {
    for (let index in responseData) {
      let tag = new Tag(responseData[index]);
      this.tags.push(tag);
    }
    console.log(this.tags);
  }

  retrieveDataUsers(responseData: any) {
    for (let index in responseData) {
      let user = new User(responseData[index]);
      this.users.push(user);
    }
    console.log(this.users)
  }

  retrieveDataMedia(responseData: any) {
    for (let index in responseData) {
      let media = new Media(responseData[index]);
      this.medias.push(media);
    }
    console.log(this.medias);
  }
  setSearch(name: string) {
    this.curentMemory.privacy = name;
    this.toggle=false;
    console.log(this.curentMemory.privacy)
    localStorage.setItem('privacyMem', this.curentMemory.privacy);
  }
  setSearch2(name: string) {
    this.text = name;
    this.toggle=false;
    localStorage.setItem('privacyMem', this.text);
  }

  onChange(event) {
    const files = event.srcElement.files;
    this.editMemoryService.makeFileRequest('http://localhost:8072/api/editMemory/upload', [], files).subscribe(() => {
      console.log('sent');
    });
  }

  editMemory(editMem: any, id:number){
    this.editMemoryService.editMemory(editMem, this.id).subscribe(
        data => {
          console.log(data);},
        error => {console.log('nu ai postat'); }
      );
  }
  deleteMedia(id: number){
    for(let index of this.medias){
      if(index.mediaId===id){
        this.medias.splice(this.medias.indexOf(index),1);

      }
    }
    this.editMemoryService.deleteMedia(id).subscribe(

      data => {
        console.log(data);},
      error => {console.log('nu ai postat'); }
    )
  }


  onChange2(event) {
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
  }

  savePictures(){
    let aux=JSON.parse(localStorage.getItem('pictures'));
    this.newMemoryService.postMedia(aux, this.id).subscribe(
      (data) => {console.log(data); localStorage.removeItem('pictures'); },
      (err) => alert(err));
  }
  removeTag(id: number){
    for(let index of this.tags){
      if(index.tagId===id){
        this.tags.splice(this.tags.indexOf(index),1);
      }
    }
    this.editMemoryService.deleteTags(id, this.id).subscribe(
      data => {
        console.log(data);},
      error => {console.log('nu ai postat'); }
    )
  }
  deleteTagged(username: string){
  for(let index of this.users){
    if(index.username===username){
      this.users.splice(this.users.indexOf(index),1);
      console.log(index.username);
      }
    }
    this.editMemoryService.deleteTagged(username, this.id).subscribe(
      data => {
        console.log(data);},
      error => {console.log('nu ai postat'); }
    )
  }

  addTags2(addTags: any){
    let array1= addTags.tags1.split(' ');

    for (let item of array1){
      this.localTags.push({"tagName":item.split('#')[1]});
    }
    console.log(this.tags);
    this.newMemoryService.postTags(this.localTags, this.id).subscribe(

      (data) => {console.log(data); },
      (err) => alert(err));

  }
}
