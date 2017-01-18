import {Injectable, OnInit} from "@angular/core";
import {Player} from "../player/player";
/**
 * Created by Oleg on 18.01.2017.
 */
@Injectable()
export class CurrPlayerService implements OnInit{
    ngOnInit(): void {
        console.log(this.player);
    }
    public player:Player;

    constructor(){
        console.log(this.player);
    }
}