/**
 * Created by okunetc on 19.01.2017.
 */
import {Component, Input, EventEmitter} from "@angular/core";
import {UnitType} from "./unitType";
import {Village} from "../village/village";
@Component({
    selector: '[player-ro]',
    outputs:['selectedVillageChanged'],
    template:`
            <td>{{v.name}}</td>
            <td>{{v.population}}</td>
            <td>{{v.xCoord}}</td>
            <td>{{v.yCoord}}</td>
            <td>{{v.isCapital}}</td>
            <td>{{v.wall}}</td>
            
            <td *ngFor="let tp of types"><army-cell [type]="tp" [village]="v" (cellClicked)="cellClick($event)"></army-cell></td>
            
`
})
export class PlayerRo{
    unitValues:Array<string>;
    selectedVillageChanged:EventEmitter<Village>;
    @Input() v:Village;
    @Input() type:UnitType;
    constructor(){
        this.selectedVillageChanged=new EventEmitter<Village>();
        this.unitValues=[];
        this.getStringUnitTypeValues();
        // console.log('in utr');
        // console.log(this.unitValues);
    }

    getStringUnitTypeValues(){
        for (let m in UnitType){
            if (typeof UnitType[m] === 'number'){
                this.unitValues.push(m);
            }
        }
    }

    cellClick(village:Village){
        this.selectedVillageChanged.emit(village);
    }
}