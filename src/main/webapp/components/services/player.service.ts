/**
 * Created by okunetc on 16.01.2017.
 */

import {Injectable} from "@angular/core";
import {Player} from "../player/player";
import {Http,Headers} from "@angular/http";
@Injectable()
export class PlayerService{
        url='player/1';
        players:Array<Player>;
        player:Player;

    constructor(private _http:Http){

    }

    getById(){

        this._http.get(this.url)
            .map(res => res.json())
            .subscribe(
                response => {
                    console.log(response);
                    if (response != null){
                        this.player =  response;
                    }

                },
                error => console.log(error)
            );
    }
}
