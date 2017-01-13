
import 'rxjs/Rx';

import {Http, Headers} from "@angular/http";
import {Injectable} from "@angular/core";
import {Alliance} from "../alliance/alliance";


@Injectable()
export class AllianceService {

    alliances: Array<Alliance> = null;

    constructor(private _http: Http){
        this.getFromServer();
    }

    getAlliances() : any {
        return this.alliances;
    }


    getFromServer(){
        console.log("it works from another path");
        this._http.get('allianceDTO')
            .map(res => res.json())
            .subscribe(
                response => {
                    console.log(response);
                    this.alliances =  response;
                },
                error => console.log(error)
            );
    }

    addAlliance(alliance: Alliance){
        const body = JSON.stringify({name: alliance.name, leaderLogin: alliance.leaderLogin, leaderEmail: alliance.leaderEmail});

        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        this._http.post('allianceDTO', body, {
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
        const url = 'allianceDTO/'+ alliance.uuid;
        this._http.delete(url, {
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
        const url = 'allianceDTO/'+ alliance.uuid ;
        this._http.put(url, body, {
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
