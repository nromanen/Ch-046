import {Army} from "../army/army";
import {Player} from "../player/player";
/**
 * Created by okunetc on 17.01.2017.
 */
export class Village{
    uuid:string;
    name:string;
    xCoord:number;
    yCoord:number;
    population:number;
    isCapital:boolean;
    wall:number;
    armies:Array<Army>;
    player:Player;
    constructor(uuid?: string){
        this.uuid = uuid;
    }





}