import {Injectable} from "@angular/core";
import {Village} from "../village/village";
import {Http, Headers} from "@angular/http";
import {Observable} from "rxjs";
import {error} from "util";
/**
 * Created by okunetc on 20.01.2017.
 */
@Injectable()
export class VillageService{
    villageURL="village/";
    villages:Array<Village>;
    update(village:Village){


        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.put(this.villageURL + village.uuid, JSON.stringify(village), {
            headers: headers
        }).map(res=>res.json());
    }

    add(village:Village){
        const body = JSON.stringify(village);
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');


       return this.http.post(this.villageURL, body, {
            headers: headers
        }).map(res => res.json());
    }
    constructor(private http:Http){

    }
}