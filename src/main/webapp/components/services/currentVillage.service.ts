import {Injectable, OnInit} from "@angular/core";
import {Player} from "../player/player";
import {Village} from "../village/village";
/**
 * Created by Oleg on 18.01.2017.
 */
@Injectable()
export class CurrVillageService implements OnInit{
    ngOnInit(): void {
        console.log(this.village);
    }
    public village:Village;

    constructor(){
        console.log(this.village);
    }
}