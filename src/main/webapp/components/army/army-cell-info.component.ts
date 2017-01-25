/**
 * Created by vyach on 25.01.2017.
 */

import {Component, Input, OnInit, EventEmitter, OnChanges, SimpleChanges} from "@angular/core";
import {Village} from "../village/village";
import {Army} from "./army";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";
import {UnitType} from "../UnitType/unitType";

@Component({
    selector: 'army-cell-info',
    template: `
     <div title="{{this.type}}">{{army!=null ? this.army.count : "0"}}</div>
`
})
export class ArmyCellInfoComponent implements OnInit {

    @Input() type:string;
    @Input() village:Village;
    army:Army;

    ngOnInit():void {
        this.village.armies.forEach((army)=> {
            if (this.type == army.type.toString() || UnitType[army.type] == this.type) {
                this.army = army;
            }
        });
    }
}