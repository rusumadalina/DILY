import {Injectable} from "@angular/core";
import {User} from "../model/user.model";
import {Http, Headers} from "@angular/http";
import {Observable} from "rxjs/Observable";
@Injectable()
export class NewMemoryService {
  curent_user: User;
  mainMem: string;
  privacy:string;
  medias=[];
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

  postJSON(addMemory) {
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
    return this.http.post('http://localhost:8072/api/addMemory/'+ this.curent_user.user_id,  json, { headers: header }).map(res => res.json());
  }

  postTagged(array: any[], id: string){
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.post(
      'http://localhost:8072/api/addMemory/tagged/' + id, array,
      {headers: headers})
      .map(res => res.json());
  }

  postTags(array: any[], id: string){
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.post(
      'http://localhost:8072/api/addMemory/tags/' + id, array,
      {headers: headers})
      .map(res => res.json());
  }


  public makeFileRequestPictures (url: string, params: string[], files: File[]){
    return Observable.create(observer => {
      const formData: FormData = new FormData(),
        xhr: XMLHttpRequest = new XMLHttpRequest();

      for (let i = 0; i < files.length; i++) {
        formData.append('uploads[]', files[i], files[i].name);
      }
     // localStorage.setItem('memoryMainPhoto', 'assets/images/memory/' + files[0].name);
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
  postMedia(array: string[], id:string){
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    for(let index of array){
      this.medias.push({"picture":index})
    }
    return this.http.post(
      'http://localhost:8072/api/addMemory/media/' + id, this.medias,
      {headers: headers})
      .map(res => res.json());
  }
}
