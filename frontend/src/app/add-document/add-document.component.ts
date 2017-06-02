import { Component, OnInit } from '@angular/core';
import {AddDocumentService} from "./add-document.service";
import {User} from "../model/user.model";
import {Document} from "../model/document.model";

@Component({
  selector: 'app-add-document',
  templateUrl: './add-document.component.html',
  styleUrls: ['./add-document.component.scss'],
  providers: [AddDocumentService]
})
export class AddDocumentComponent implements OnInit {

  curent_user: User;
  documents=[];
  allDocuments=[];

  constructor(private addDocumentService: AddDocumentService) {

    if(JSON.parse(localStorage.getItem('documents'))!=null){
      this.documents=JSON.parse(localStorage.getItem('documents'));
    }else{
      this.documents=[];
    }
    this.curent_user = JSON.parse(localStorage.getItem('user'));
  }

  ngOnInit() {
    this.addDocumentService.getAllDocuments(this.curent_user.user_id).subscribe(
      (data) => {this.retrieveData(data)},
      (err) => alert(err)
    );
  }
  retrieveData(responseData: any) {
    for (let index in responseData) {
      let doc = new Document(responseData[index]);
      this.allDocuments.push(doc);

    }

  }

  onChange(event) {
    const files = event.srcElement.files;
    this.documents.push(files[0].name);
    console.log(files[0]);
    localStorage.setItem('documents',JSON.stringify(this.documents));
    this.addDocumentService.makeFileRequest('http://localhost:8072/api/addDocument', [], files).subscribe(() => {
      console.log('sent');
    });
  }
  openDocument(name: string){
    window.open('assets/documents/'+name);
  }
  openDocument2(name:string){
    window.open(name);
  }
  saveDocuments(){
    this.addDocumentService.postDocuments(this.documents, this.curent_user.user_id).subscribe(
    (data) => {console.log(data);
        localStorage.removeItem('documents');
    },
    (err) => alert(err));
    window.location.reload();
  }
  deleteDoc(id: number){
    this.addDocumentService.deleteDocument(id, this.curent_user.user_id).subscribe(
      (data) => {console.log(data);
      },
      (err) => alert(err));
    window.location.reload();
  }

}
