
import 'rxjs/Rx';

import {Http, Headers, Response, RequestOptions} from "@angular/http";
import {Injectable} from "@angular/core";

import { Observable }     from 'rxjs/Rx';
import {Alliance} from "../../alliance/alliance";

@Injectable()
export class AllianceService {


    url = 'admin/allianceDTO/';
    alliances: Array<Alliance> = new Array<Alliance>();

    constructor(private _http: Http) {
    }

    getAlliances(): Observable<Alliance[]> {

        return this._http.get(this.url)
            .map((res: Response) => {
            return res.json();}
            )
            .catch((error: any) => Observable.throw(error.json().error || 'Server error')); //...errors if

    }

    addAlliance(alliance: Alliance):  Observable<Alliance> {
        const body = JSON.stringify({
            name: alliance.name,
            leaderLogin: alliance.leaderLogin,
            leaderEmail: alliance.leaderEmail
        });
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this._http.post(this.url, body, {
            headers: headers
        }).map((res: Response) => res.json())
            .catch(
                (error: any) => { return Observable.throw(error._body);
                                   }
            );
    }

    deleteAlliance(alliance: Alliance): boolean {

        let result : boolean = true;
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        this._http.delete(this.url + alliance.allianceUuid, {
            headers: headers
        }).subscribe(
            response => {
                console.log('Alliance deleted, id = ' + alliance.allianceUuid);
                result = true;
            },
            error => {
                console.log(error);
                result = false;
            }
        );
        return result;
    }

    updateAlliance(newAlliance: Alliance):  Observable<Alliance> {
        const body = JSON.stringify({
            name: newAlliance.name,
            leaderLogin: newAlliance.leaderLogin,
            leaderEmail: newAlliance.leaderEmail,
            leaderUuid: newAlliance.leaderUuid
        });
        console.log("body");
        console.log(body);
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this._http.put(this.url + newAlliance.allianceUuid, body, {
            headers: headers
        }).map((res: Response) => res.json()) // ...and calling .json() on the response to return data
            .catch(
                (error: any) => {
                    if (error.status == 400){
                        return Observable.throw('ERROR!!! Invalid data. Please check entered data!');
                    } else if(error.status == 409){
                        return Observable.throw('ERROR!!! User whit the same login or e-mail is in DB!');
                    } else {
                        return Observable.throw('UNKNOWN ERROR!!!');
                    }
                }
            ); //...errors if
    }
}
