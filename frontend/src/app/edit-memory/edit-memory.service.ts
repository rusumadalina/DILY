import {Injectable} from "@angular/core";
import {User} from "../model/user.model";
import {Http, Headers} from "@angular/http";
import {Observable} from "rxjs/Observable";
@Injectable()
export class EditMemoryService {
  curent_user: User;
  mainMem: string;
  medias=[];
  privacy:string;
  toggle:boolean;
  constructor(private http: Http) {
    this.curent_user = JSON.parse(localStorage.getItem('user'));
    this.mainMem=localStorage.getItem('memoryMainPhoto');
    this.privacy=localStorage.getItem('privacyMem');
    this.toggle=false;

  }

  getAllFriends() {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      'http://localhost:8072/api/newMemory/friends/' + this.curent_user.user_id,
      {headers: headers})
      .map(res => res.json());
  }



  editMemory(addMemory, id){
    const json = JSON.stringify(
      {
        title: addMemory.title,
        description: addMemory.description,
        memoryLocation: addMemory.location,
        date: addMemory.date,
        privacy: this.privacy,
        mainPicture: this.mainMem
      });

    const header =  new Headers();
    header.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8072/api/editMemory/'+ id,  json, { headers: header }).map(res => res.json());
  }


  public makeFileRequest (url: string, params: string[], files: File[]){
    return Observable.create(observer => {
      const formData: FormData = new FormData(),
        xhr: XMLHttpRequest = new XMLHttpRequest();

      for (let i = 0; i < files.length; i++) {
        formData.append('uploads[]', files[i], files[i].name);
      }
      localStorage.setItem('memoryMainPhoto', 'assets/images/mainPictures/' + files[0].name);
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

  deleteMedia(id){
    console.log(id);

    const header =  new Headers();
    header.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8072/api/editMemory/deleteMedia/'+ id,  { headers: header }).map(res => res.json());
  }

  deleteTags(idTag, idMem){

    const header =  new Headers();
    header.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8072/api/editMemory/deleteTags/'+ idTag + '/' +idMem,  { headers: header }).map(res => res.json());
  }
  deleteTagged(username, idMem){
    const header =  new Headers();
    header.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8072/api/editMemory/deleteTagged/'+ btoa(username) + '/' +idMem,  { headers: header }).map(res => res.json());
  }
}


