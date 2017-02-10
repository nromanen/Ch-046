/**
 * Created by vyach on 25.01.2017.
 */

import {Component, Input, OnInit} from "@angular/core";
import {UnitType} from "../UnitType/unitType";
import {Village} from "./village";

@Component({
    selector: '[village-info]',
    templateUrl: 'components/village/village-info.html'
})

export class VillageInfoComponent implements OnInit {
    @Input() village:Village;
    unitValues:string[];

    constructor() {
        this.unitValues = [];
    }

    ngOnInit():void {
        this.getStringUnitTypeValues();
    }

    getStringUnitTypeValues() {
        for (let type in UnitType){
            this.unitValues.push(type);
        }
    }
}