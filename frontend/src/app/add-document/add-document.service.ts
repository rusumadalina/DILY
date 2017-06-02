import {Injectable} from "@angular/core";
import {User} from "../model/user.model";
import {Http, Headers} from "@angular/http";
import {Observable} from "rxjs/Observable";
@Injectable()
export class AddDocumentService {
  curent_user: User;
  mainMem: string;
localDocuments=[];
  privacy:string;
  toggle:boolean;
  constructor(private http: Http) {
    this.curent_user = JSON.parse(localStorage.getItem('user'));


  }

  public makeFileRequest (url: string, params: string[], files: File[]){
    return Observable.create(observer => {
      const formData: FormData = new FormData(),
        xhr: XMLHttpRequest = new XMLHttpRequest();

      for (let i = 0; i < files.length; i++) {
        formData.append('uploads[]', files[i], files[i].name);
      }
      //localStorage.setItem('memoryMainPhoto', 'assets/images/mainPictures/' + files[0].name);
      xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
          if (xhr.status === 200) {
            observer.next(JSON.parse(xhr.response));
            observer.complete();
          } else {
            observer.error(xhr.response);
          }
        }
      };
      xhr.open('POST', url, true);
      xhr.send(formData);
    });
  }

  postDocuments(documents,id){
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    for(let index of documents){
      this.localDocuments.push({"path":'assets/documents/'+index})
    }
    return this.http.post(
      'http://localhost:8072/api/addDocuments/save/' + id, this.localDocuments,
      {headers: headers})
      .map(res => res.json());
  }

  getAllDocuments(id){
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      'http://localhost:8072/api/addDocuments/getDocuments/' + id,
      {headers: headers})
      .map(res => res.json());
  }
  deleteDocument(id,userId){
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      'http://localhost:8072/api/addDocuments/deleteDocuments/' + userId+'/'+id,
      {headers: headers})
      .map(res => res.json());
  }
}


