import {Injectable, provide} from 'angular2/core';
import {Http, Headers} from 'angular2/http';
import 'rxjs/Rx';
import {Alliance} from './alliance';
import {getAllDebugNodes} from "angular2/src/core/debug/debug_node";


@Injectable()
export class AllianceService {

    alliances: Array<Alliance> = [];


    constructor(private _http: Http){
        // this.alliances[0] = new Alliance("test_id", "test_name", "test_leaderLogin", "test_leaderEmail");
        // this.alliances[0] = new Alliance("test_id2", "test_name2", "test_leaderLogin2", "test_leaderEmail2");
        // this.alliances[0] = new Alliance("test_id3", "test_name3", "test_leaderLogin3", "test_leaderEmail3");

        this.getFromServer();
    }

    private getCookie(name) {
        let value = "; " + document.cookie;
        let parts = value.split("; " + name + "=");
        if (parts.length == 2)
            return parts.pop().split(";").shift();
    }

    getAlliances() : any {
        return this.alliances;
    }


    getFromServer(){
        console.log("it works");
        this._http.get('/allianceDTO')
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
        headers.append('X-CSRFToken', this.getCookie('csrftoken'));
        this._http.post('/allianceDTO', body, {
            headers: headers
        }).map(res => res.json())
            .subscribe(
                response => console.log('Alliance created successful'),
                error => console.log(error)
            );
        this.getFromServer();
        this.getFromServer();
    }

    deleteAlliance(alliance: Alliance){

        this.alliances.splice(this.alliances.indexOf(alliance), 1);

        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('X-CSRFToken', this.getCookie('csrftoken'));
        const url = '/allianceDTO/'+ alliance.uuid;
        this._http.delete(url, {
            headers: headers
        }).subscribe(
            response => console.log('Alliance deleted, id = ' + alliance.uuid),
            error => console.log(error)
        );

    }

    updateAlliance(alliance: Alliance, newAlliance: Alliance){

        this.alliances[this.alliances.indexOf(alliance)] = newAlliance;

        const body = JSON.stringify({name: newAlliance.name, leaderLogin: newAlliance.leaderLogin, leaderEmail: newAlliance.leaderEmail});
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('X-CSRFToken', this.getCookie('csrftoken'));
        const url = '/allianceDTO/'+ alliance.uuid ;
        this._http.put(url, body, {
            headers: headers
        }).subscribe(
            response => console.log('Alliance updated, id = ' + alliance.uuid),
            error => console.log(error)
        );
    }


}
