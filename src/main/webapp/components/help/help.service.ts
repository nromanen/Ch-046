/**
 * Created by rmochetc on 22.01.2017.
 */
import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs";
@Injectable()
export class HelpService {

    url = 'askhelp';

    constructor(private _http: Http, private _activatedRoute: ActivatedRoute) {

    }


    getById(): Observable<any> {
        console.log(this.url);
        return this._http.get(this.url)
            .map(res => res.json());
    }
}