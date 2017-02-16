import {Injectable, OnInit} from "@angular/core";
import {Player} from "../player/player";
import {Village} from "../village/village";
import {Army} from "../army/army";
/**
 * Created by Oleg on 18.01.2017.
 */

@Injectable()
export class CurrVillageArmiesService{
    public village:Village;
    public armies:Array<Army>;
    constructor(){
        this.armies=[];
    }
}