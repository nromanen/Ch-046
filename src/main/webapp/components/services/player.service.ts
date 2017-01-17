/**
 * Created by okunetc on 16.01.2017.
 */

import {Injectable} from "@angular/core";
import {Player} from "../player/player";
import {Http,Headers} from "@angular/http";
import {Router, ActivatedRoute} from "@angular/router";
@Injectable()
export class PlayerService{
        url='player/1';
        players:Array<Player>;
        player:Player;
        id:number;

    constructor(private _http:Http, public activatedRoute:ActivatedRoute){

    }

    getById(){
        console.log(this.activatedRoute.params.map);
        console.log(this.activatedRoute.queryParams.map);
        var str =this.activatedRoute.params.subscribe((param:any)=>
        {
            console.log();

            let userId = param['id'];
            console.log(param['id']);
            console.log(this.activatedRoute===undefined);
        });

        var str1=this.activatedRoute.queryParams.subscribe((par:any)=>
            {
                console.log(str1);
                console.log(par);
                console.log(par['id']);
            }
        );
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
