
import 'rxjs/Rx';

import {Http, Headers, Response } from "@angular/http";
import {Injectable} from "@angular/core";
import {Alliance} from "../alliance/alliance";
import { Observable }     from 'rxjs/Rx';

@Injectable()
export class AllianceService {

    // {{pageContext.request.contextPath}};
    url = 'admin/allianceDTO/';
    alliances: Array<Alliance> = new Array<Alliance>();

    constructor(private _http: Http){
    }

    // getAlliances (): Observable<Alliance[]> {
    //     return this._http.get(this.url)
    //         .map(this.extractData);
    //         // .catch(this.handleError);
    // }

    getAlliances() : Observable<Alliance[]> {

        // ...using get request
        return this._http.get(this.url).
        map(this.extractData)
            .catch(this.handleError);

    }

    private extractData(res: Response) {
        let body = res.json();
        console.log(body);
        return body || { };
    }
    private handleError (error: Response | any) {
        // In a real world app, we might use a remote logging infrastructure
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable.throw(errMsg);
    }

    ngOnInit() {
        // get dummy data
        console.log("OnInit service");
        this._http.get(this.url)
            .map(res => res.json())
            .subscribe(
                response => {
                    console.log(response);
                    if (response != null){
                        this.alliances =  response;
                    }
                },
                error => console.log(error)
            );
    }

    getFromServer(){
        console.log("it works from another path");
        this._http.get(this.url)
            .map(res => res.json())
            .subscribe(
                response => {
                    console.log(response);
                    if (response != null){
                        this.alliances =  response;
                    }

                },
                error => console.log(error)
            );
    }

    addAlliance(alliance: Alliance){
        const body = JSON.stringify({name: alliance.name, leaderLogin: alliance.leaderLogin, leaderEmail: alliance.leaderEmail});

        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        this._http.post(this.url, body, {
            headers: headers
        }).map(res => res.json())
            .subscribe(
                response => {
                    console.log('Alliance created successful');
                    this.alliances.push(response);
                },
                error => console.log(error)
            );
    }

    deleteAlliance(alliance: Alliance){

        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        this._http.delete(this.url + alliance.uuid, {
            headers: headers
        }).subscribe(
            response =>{
                console.log('Alliance deleted, id = ' + alliance.uuid);
                this.alliances.splice(this.alliances.indexOf(alliance), 1);
            },
            error => console.log(error)
        );
    }

    updateAlliance(alliance: Alliance, newAlliance: Alliance){


        const body = JSON.stringify({name: newAlliance.name, leaderLogin: newAlliance.leaderLogin, leaderEmail: newAlliance.leaderEmail});
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        this._http.put(this.url + alliance.uuid, body, {
            headers: headers
        }).subscribe(
            response => {
                console.log('Alliance updated, id = ' + alliance.uuid + ', status = ' + response.status);
                this.alliances[this.alliances.indexOf(alliance)] = newAlliance;
            },
            error => console.log(error)
        );
    }
}
