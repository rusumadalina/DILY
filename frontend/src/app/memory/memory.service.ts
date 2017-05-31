import {Injectable} from "@angular/core";
import {Http , Headers} from "@angular/http";
/**
 * Created by rusum on 31.05.2017.
 */
@Injectable()
export class MemoryService {

  constructor(private http: Http) {
  }

  seeMoreMemory( id: string ){
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      'http://localhost:8072/api/memories/view/' + id,
      {headers: headers})
      .map(res => res.json());
  }

}
