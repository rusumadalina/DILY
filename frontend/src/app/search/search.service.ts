import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
/**
 * Created by rusum on 29.05.2017.
 */
@Injectable()
export class SearchService{


  constructor(private http: Http) {

  }
  search(type: string, word:string) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      'http://localhost:8072/api/search/' + type + '/' + word,
      {headers: headers})
      .map(res => res.json());
  }
}
/*
* /api/search/tag/cuvant
* /api/search/friends
* /api/search/newFriends
* */
