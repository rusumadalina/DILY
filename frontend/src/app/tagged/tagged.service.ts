import {Injectable} from "@angular/core";
import {Http , Headers} from "@angular/http";
/**
 * Created by rusum on 31.05.2017.
 */
@Injectable()
export class TaggedService {

  constructor(private http: Http) {
  }

  viewTagged( id: number ){
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(
      'http://localhost:8072/api/tagged/' + id,
      {headers: headers})
      .map(res => res.json());
  }

}
