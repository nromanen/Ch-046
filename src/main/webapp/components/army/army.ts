import {UnitType} from "../UnitType/unitType";
/**
 * Created by okunetc on 19.01.2017.
 */
export class Army{
    type:UnitType;
    constructor(count?: number,type?: UnitType,  ownUnit?: boolean) {
        this.type = type||null;
        this.count = count||0;
        this.ownUnit = ownUnit||false;
    }

    count:number;
    ownUnit:boolean;
    uuid:string;
}