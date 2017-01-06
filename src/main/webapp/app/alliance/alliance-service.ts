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

}

//
//
// import {Injectable, provide} from 'angular2/core';
// import {Http, Headers} from 'angular2/http';
// import 'rxjs/Rx';
// import {Alliance} from './alliance';
//
// @Injectable()
// export class AllianceService {
//
//     alliances: Array<Alliance> = [];
//
//
//     constructor(private _http: Http){
//         this.alliances[0] = new Alliance("test_id", "test_name", "test_leaderLogin", "test_leaderEmail");
//         this.alliances[1] = new Alliance("test_id2", "test_name2", "test_leaderLogin2", "test_leaderEmail2");
//         this.alliances[2] = new Alliance("test_id3", "test_name3", "test_leaderLogin3", "test_leaderEmail3");
//     }
//
//
//     getAlliances() : any {
//         return this.alliances;
//     }
//
// }

