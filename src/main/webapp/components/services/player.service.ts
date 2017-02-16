/**
 * Created by okunetc on 16.01.2017.
 */

import {Injectable} from "@angular/core";
import {Player} from "../player/player";
import {Http} from "@angular/http";
import {Observable} from "rxjs";


@Injectable()
export class PlayerService {
    
    players:Array<Player>;
    player:Player;
    id:string;
    url = 'player/';

    constructor(private http:Http) {

    }
    
    getById():Observable<Player> {
        return this.http.get(this.url)
            .map(res => res.json());
    }

    getPlayersByAlliance():Observable<Player[]> {
        console.log(`PlayerService.getPlayersByAlliance() method is working`);

        return this.http.get(`${this.url}alliance`)
            .map(res => res.json());
    }

}
