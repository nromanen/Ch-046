import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs";

/**
 * Created by rmochetc on 14.02.2017.
 */

@Injectable()
export class AttackArchiveService {

    url = 'user/attackArchive';

    constructor(private http: Http) {
    }

    getById(id:string): Observable<any[]>{
       return this.http.get('user/attack/' + id)
            .map(res => res.json());
    }

    getAttackArchive(): Observable<any[]> {
        return this.http.get(`${this.url}`)
            .map(res => res.json());
    }


}