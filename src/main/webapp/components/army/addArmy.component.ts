/**
 * Created by okunetc on 23.01.2017.
 */
import {Component, Input, OnInit, OnChanges, AfterViewChecked} from "@angular/core";
import {Army} from "./army";
import {UnitType} from "../UnitType/unitType";
import {Player} from "../player/player";
import {Village} from "../village/village";
@Component({
    selector:"[add-army]",
    template:`
 <div class="input-field col s6 offset-s3">
    <select class="browser-default" [ngModel]="army.type" 
    (ngModelChange)="army.type = $event" name="type" #type>
    <option value="" disabled selected>Choose your option</option>
    <option *ngFor="let type of unitTypes" [ngValue]="type">{{unitTypeStrings[type]}}</option>
  </select>
    </div>
    <div class="input-field col s6 offset-s3">
          <input id="count" type="text" class="validate" [(ngModel)]="army.count" name="count" pattern="[0-9]*" #count="ngModel" required>
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
Is capital?
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
export class AddArmyComponent implements OnInit,AfterViewChecked{
    ngAfterViewChecked(): void {
        // console.log(this.army);
    }
    ngOnInit(): void {
        // console.log('army is');
        // console.log(this.army);
        this.army.type=UnitType.Axeman;
    }
    @Input() army:Army;
    @Input() village:Village;
unitTypes:Array<number>;
unitTypeStrings:Array<string>;
    constructor(){
        this.unitTypes=[];
        this.unitTypeStrings=[];
        for(let t in UnitType){
            if (typeof UnitType[t]==='string')
            this.unitTypes.push(+t);
            else {
                this.unitTypeStrings.push(t);
            }
        }

        // console.log(this.unitTypes);
        // console.log(this.unitTypeStrings);
        // console.log(UnitType[0]);
    }

}