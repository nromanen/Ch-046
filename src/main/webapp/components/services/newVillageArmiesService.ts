import {Injectable, OnInit} from "@angular/core";
import {Player} from "../player/player";
import {Village} from "../village/village";
import {Army} from "../army/army";
/**
 * Created by Oleg on 18.01.2017.
 */

@Injectable()
export class CurrVillageArmiesService implements OnInit{
    ngOnInit(): void {
        console.log(this.village);
    }
    public village:Village;
    public armies:Array<Army>;

    constructor(){
        console.log(this.village);
        this.armies=[];
    }
}