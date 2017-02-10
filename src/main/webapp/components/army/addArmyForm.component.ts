/**
 * Created by okunetc on 23.01.2017.
 */
import {Component, Input, OnInit, AfterViewChecked, Output, EventEmitter, ViewChild} from "@angular/core";
import {Army} from "./army";
import {UnitType} from "../UnitType/unitType";
import {Village} from "../village/village";
import {NgForm, FormGroup} from "@angular/forms";
@Component({
    selector:"[add-army]",
    templateUrl:"components/army/addArmyForm.html"
})
export class AddArmyForm implements AfterViewChecked{
@Input() group:FormGroup;
    @Input() army:Army;
    @Input() village:Village;
    @Output () armyIsValid:EventEmitter<boolean>;
    unitTypes:Array<string>;
    unitTypeStrings:Array<string>;

    constructor(){
        this.unitTypes=[];
        this.armyIsValid=new EventEmitter<boolean>();
        this.unitTypeStrings=[];
        this.initUnitTypes();
    }

    ngAfterViewChecked(): void {
        this.armyIsValid.emit(true);
    }

    initUnitTypes(){
        for(let type in UnitType){
            this.unitTypeStrings.push(type);
        }
    }
}