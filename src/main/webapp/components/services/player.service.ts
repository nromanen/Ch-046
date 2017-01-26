/**
 * Created by okunetc on 16.01.2017.
 */

import {Injectable} from "@angular/core";
import {Player} from "../player/player";
import {Http} from "@angular/http";
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs";
@Injectable()
export class PlayerService{
        player:Player;
        id:string;
        url='player/';
    constructor(private _http:Http){

    }


    getById():Observable<Player>{
        console.log(this.url);
        return this._http.get(this.url)
            .map(res => res.json());
    }

}
