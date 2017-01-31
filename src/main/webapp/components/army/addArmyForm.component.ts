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
//     template:`
// <!--<div [formGroup]="group">-->
//
// <!--<div class="input-field col s6 offset-s3" >-->
//     <!--<select class="browser-default" name="type" formControlName="type">-->
//         <!--<option value="" disabled selected>Choose your option</option>-->
//         <!--<option *ngFor="let type of unitTypes" [ngValue]="type">{{unitTypeStrings[type]}}</option>-->
//     <!--</select>-->
// <!--</div>-->
//
// <!--<div class="input-field col s6 offset-s3">-->
//     <!--<input id="count" type="text" class="validate" formControlName="count" required>-->
//     <!--<label for="count">Count</label>-->
//         <!--<div *ngIf="!group.controls.count.valid">-->
//         <!--<div [hidden]=" (!group.controls['count'].touched || !group.controls['count'].dirty)||-->
//         <!--!group.controls['count'].errors.required">-->
//             <!--Count is required!-->
//         <!--</div>-->
//         <!--<div [hidden]="(!group.controls['count'].touched ||-->
//          <!--!group.controls['count'].dirty) || !group.controls['count'].errors.pattern">-->
//            <!--Count can contain numbers only!-->
//         <!--</div>-->
//         <!--</div>-->
//     <!---->
// <!--</div>-->
// <!--<div class="input-field col s6 offset-s3">-->
//     <!--Is own unit?-->
// <!--</div>-->
// <!--<div class="switch ">-->
//     <!--<label class="col offset-s3">-->
//         <!--No-->
//         <!--<input type="checkbox"  formControlName="isOwnUnit">-->
//         <!--<span class="lever"></span>-->
//         <!--Yes-->
//     <!--</label>-->
// <!--</div>-->
// <!--</div>-->
// `
})
export class AddArmyForm implements OnInit,AfterViewChecked{
@Input()
    group:FormGroup;
    unitTypes:Array<number>;
    unitTypeStrings:Array<string>;

    constructor(){
        this.unitTypes=[];
        this.armyIsValid=new EventEmitter<boolean>();
        this.unitTypeStrings=[];
        for(let type in UnitType){
            this.unitTypeStrings.push(type);
            if (typeof UnitType[type]==='string')
                this.unitTypes.push(+type);
            else {
                this.unitTypeStrings.push(type);
            }
        }
        console.log(this.unitTypeStrings);
    }

    @Input() army:Army;
    @Input() village:Village;
    @Output () armyIsValid:EventEmitter<boolean>;
    ngOnInit(): void {
        // this.army.type=UnitType.Axeman;
    }
    ngAfterViewChecked(): void {
        this.armyIsValid.emit(true);
    }
}