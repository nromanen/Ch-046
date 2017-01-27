/**
 * Created by okunetc on 19.01.2017.
 */
import {Component, Input, EventEmitter, OnInit, AfterViewChecked} from "@angular/core";
import {UnitType} from "../UnitType/unitType";
import {Village} from "./village";
import {VillageService} from "../services/villageService";
import {error} from "util";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";
@Component({
    selector: '[player-ro]',
    outputs:['selectedVillageChanged'],
    template:`
<td>
    <div *ngIf="!isForm">{{v.name}}</div>
    <input *ngIf="isForm" (ngModelChange)="this.newVillage.name=$event" type="text" [ngModel]="this.v.name"  name="name"></td>
    
<td>
    <div *ngIf="!isForm">{{v.population}}</div>
    <input *ngIf="isForm" (ngModelChange)="this.newVillage.population=$event" type="text" [ngModel]="this.v.population" name="population">
</td>
<td>
    <div *ngIf="!isForm" >{{this.v.xCoord}}</div>
    <input (ngModelChange)="this.newVillage.xCoord=$event" *ngIf="isForm" type="text" [ngModel]="this.v.xCoord" name="x">
</td>
<td >
    <div *ngIf="!isForm">{{v.yCoord}}</div>
    <input *ngIf="isForm" type="text" [ngModel]="this.v.yCoord" name="y" (ngModelChange)="this.newVillage.yCoord=$event">
</td>
<td >
    <div *ngIf="!isForm">{{v.isCapital}}</div>
    <input *ngIf="isForm" type="checkbox" (ngModelChange)="this.newVillage.isCapital=$event" [ngModel]="this.v.isCapital" name="isCapital" id="isCapital" class="filled-in">
    <label *ngIf="isForm" for="isCapital"></label>
</td>

<td >
<div *ngIf="!isForm">{{v.wall}}</div>
<input *ngIf="isForm" type="text" [ngModel]="this.v.wall" (ngModelChange)="this.newVillage.wall=$event" name="wall">
</td>

<td *ngFor="let tp of unitValues">
    <army-cell [type]="tp" [village]="v" 
    (cellClicked)="cellClick($event)" [isInput]="isForm" [ifSave]="ifSaveChanges"></army-cell>
</td>
<td>
    <button (click)="!isForm?showEdit():changeVillage()" class="btn waves-effect waves-light col offset-s1 " type="submit" name="action"
            style="margin-top: 5px;"
    >{{!isForm?"Edit":"Save"}}
    </button>
    
</td>
`
})
export class VillageRow implements OnInit,AfterViewChecked{
    ngAfterViewChecked(): void {

    }
    private newVillage: Village;
    private villBefore;
    ngOnInit(): void {
        // console.log("inside village row");
        // console.log(this.v);
        // alert(JSON.stringify(this.v));
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
    unitValues:Array<string>;
    selectedVillageChanged:EventEmitter<Village>;
    @Input() v:Village;
    @Input() isForm:boolean;
    ifSaveChanges:boolean;
    constructor( private villageService:VillageService, private currVillageArmiesService:CurrVillageArmiesService){
        this.selectedVillageChanged=new EventEmitter<Village>();
        this.unitValues=[];
        this.ifSaveChanges=false;
        // console.log('inside constructor');
        // console.log(this.ifSaveChanges);
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

    changeVillage(){
        this.ifSaveChanges=true;
        console.log('insideChange');
        console.log(this.v.armies);
        this.v.name=this.newVillage.name;
        this.v.armies=this.currVillageArmiesService.armies;

        this.v.xCoord=this.newVillage.xCoord;
        this.v.yCoord=this.newVillage.yCoord;
        this.v.isCapital=this.newVillage.isCapital;
        this.v.population=this.newVillage.population;
        this.v.wall=this.newVillage.wall;

        console.log('insideChange');
        console.log(this.v.armies);
        this.submitEdit();
        this.isForm=false;
        this.ifSaveChanges=false;
        // console.log("inside save");
        // console.log(this.ifSaveChanges);
        // this.ifSaveChanges=false;
    }

    showEdit(){

            this.selectedVillageChanged.emit(null);
            this.selectedVillageChanged.emit(this.v);
             this.ifSaveChanges=false;
             this.currVillageArmiesService.armies.length=0;
             // this.v.armies.forEach(army=>{
             //     this.currVillageArmiesService.armies.push(army);
             // });
             console.log(this.currVillageArmiesService.armies);

    }

}