/**
 * Created by okunetc on 23.01.2017.
 */
import {Component, Input, OnInit, AfterViewChecked, Output, EventEmitter, ViewChild} from "@angular/core";
import {Army} from "./army";
import {UnitType} from "../UnitType/unitType";
import {Village} from "../village/village";
import {NgForm} from "@angular/forms";
@Component({
    selector: "[add-army]",
    template: `
<div class="input-field col s6 offset-s3">
    <select class="browser-default" [ngModel]="army.type"
            (ngModelChange)="army.type = $event" name="type" #type>
        <option value="" disabled selected>Choose your option</option>
        <option *ngFor="let type of unitTypes" [ngValue]="type">{{unitTypeStrings[type]}}</option>
    </select>
</div>
<div class="input-field col s6 offset-s3">
    <input id="count" type="text" class="validate" [(ngModel)]="army.count" name="count" pattern="[1-9][0-9]*"
           #count="ngModel" required>
    <label for="count">Count</label>
    <div *ngIf="count.errors && (count.dirty || count.touched)"
         class="alert alert-danger">
        <div [hidden]="!count.errors.required">
            Count is required
        </div>
        <div [hidden]="!count.errors.pattern">
            Coordinates can contain chars only!
        </div>
    </div>
</div>

<div class="input-field col s6 offset-s3">
    Is own unit?
</div>
<div class="switch ">
    <label class="col offset-s3">
        No
        <input type="checkbox" [(ngModel)]="army.ownUnit" name="isCapital">
        <span class="lever"></span>
        Yes
    </label>
</div>
`
})
export class AddArmyForm implements OnInit,AfterViewChecked {
    @Input() army: Army;
    @Input() village: Village;
    @Output() armyIsValid: EventEmitter<boolean>;
    unitTypes: Array<number>;
    unitTypeStrings: Array<string>;

    constructor() {
        this.unitTypes = [];
        this.armyIsValid = new EventEmitter<boolean>();
        this.unitTypeStrings = [];
        for (let type in UnitType) {
            if (typeof UnitType[type] === 'string')
                this.unitTypes.push(+type);
            else {
                this.unitTypeStrings.push(type);
            }
        }
    }

    ngOnInit(): void {
        this.army.type = UnitType.Axeman;
    }

    ngAfterViewChecked(): void {
        this.armyIsValid.emit(true);
    }
}