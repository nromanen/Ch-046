/**
 * Created by okunetc on 19.01.2017.
 */
import {Component, Input, EventEmitter, OnInit, AfterViewChecked, ViewChild} from "@angular/core";
import {UnitType} from "../UnitType/unitType";
import {Village} from "./village";
import {VillageService} from "../services/villageService";
import {error} from "util";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";
@Component({
    selector: '[player-ro]',
    outputs:['selectedVillageChanged'],
    styleUrls:['css/style.css'],
    template:`

<!--<input minlength="5" type="text"  class="validate" name="test" #test="ngModel>"-->
<td [formGroup]="editVillageForm">
    <div *ngIf="!isForm">{{v.name}}</div>
    <input *ngIf="isForm" type="text" formControlName="name">
    </td>
  
<td [formGroup]="editVillageForm">
    <div *ngIf="!isForm">{{v.population}}</div>
    <input *ngIf="isForm"  type="text"  formControlName="population">
</td>
<td [formGroup]="editVillageForm">
    <div *ngIf="!isForm" >{{this.v.xCoord}}</div>
    <input  *ngIf="isForm" type="text"  formControlName="xCoord">
</td>
<td [formGroup]="editVillageForm">
    <div *ngIf="!isForm">{{v.yCoord}}</div>
    <input *ngIf="isForm" type="text"  formControlName="yCoord" >
</td>
<td [formGroup]="editVillageForm">
    <div *ngIf="!isForm">
    <i *ngIf="v.isCapital" class="small material-icons check">done</i>
    </div>
    <input *ngIf="isForm" type="checkbox" formControlName="isCapital" id="isCapital" class="filled-in">
    <label *ngIf="isForm" for="isCapital"></label>
</td>

<td [formGroup]="editVillageForm">
<div *ngIf="!isForm">{{v.wall}}</div>
<input *ngIf="isForm" type="text" formControlName="wall">
</td>

<td *ngFor="let tp of unitValues; let i=index;" >
    <army-cell [type]="tp" [village]="v" [group]="editVillageForm.controls.armies?editVillageForm.controls.armies.controls[i]:null"
    (cellClicked)="cellClick($event)" [isInput]="isForm" [ifSave]="ifSaveChanges"></army-cell>
</td>
<td>
    <button (click)="!isForm?showEdit():null" type="submit" [disabled]="!editVillageForm.valid && isForm"
    class="btn waves-effect waves-light col offset-s1"  name="action" 
            style="margin-top: 5px;" >
            {{!isForm?"Edit":"Save"}}      
    </button>
</td>

`
})
export class VillageRow implements OnInit,AfterViewChecked{
    @Input() v:Village;
    @Input() isForm:boolean;
    @Input() editVillageForm;

    unitValues:Array<string>;
    selectedVillageChanged:EventEmitter<Village>;
    ngAfterViewChecked(): void {

    }
    private newVillage: Village;
    private villBefore;
    ngOnInit(): void {
        this.newVillage=new Village();
        this.newVillage.name=this.v.name;
        this.newVillage.uuid=this.v.uuid;
        this.newVillage.armies=this.v.armies;
        this.newVillage.isCapital=this.v.isCapital;
        this.newVillage.population=this.v.population;
        this.newVillage.xCoord=this.v.xCoord;
        this.newVillage.yCoord=this.v.yCoord;
        this.newVillage.wall=this.v.wall;
        this.getStringUnitTypeValues();
    }


    ifSaveChanges:boolean;
    constructor( private villageService:VillageService, private currVillageArmiesService:CurrVillageArmiesService){
        this.selectedVillageChanged=new EventEmitter<Village>(false);
        this.unitValues=[];
        this.ifSaveChanges=false;

    }



    getStringUnitTypeValues(){
        for (let m in UnitType){
            if (typeof UnitType[m] === 'number'){
                this.unitValues.push(m);
            }
        }

    }
    submitEdit(){
        this.villageService.update(this.v);
    }
    cellClick(village:Village){
        this.selectedVillageChanged.emit(village);
    }

    // changeVillage(){
    //     this.ifSaveChanges=true;
    //     console.log('insideChange');
    //     console.log(this.v.armies);
    //     this.v.name=this.newVillage.name;
    //     this.v.armies=this.currVillageArmiesService.armies;
    //
    //     this.v.xCoord=this.newVillage.xCoord;
    //     this.v.yCoord=this.newVillage.yCoord;
    //     this.v.isCapital=this.newVillage.isCapital;
    //     this.v.population=this.newVillage.population;
    //     this.v.wall=this.newVillage.wall;
    //
    //     console.log('insideChange');
    //     console.log(this.v.armies);
    //     this.submitEdit();
    //     this.isForm=false;
    //     this.ifSaveChanges=false;
    //     // console.log("inside save");
    //     // console.log(this.ifSaveChanges);
    //     // this.ifSaveChanges=false;
    // }

    showEdit(){

            // this.selectedVillageChanged.emit(null);
            this.selectedVillageChanged.emit(this.v);
             this.ifSaveChanges=false;
             this.currVillageArmiesService.armies.length=0;
             // this.v.armies.forEach(army=>{
             //     this.currVillageArmiesService.armies.push(army);
             // });
             console.log(this.currVillageArmiesService.armies);

    }

}