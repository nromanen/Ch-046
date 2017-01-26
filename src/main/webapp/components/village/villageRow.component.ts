/**
 * Created by okunetc on 19.01.2017.
 */
import {Component, Input, EventEmitter, OnInit, AfterViewChecked} from "@angular/core";
import {UnitType} from "../UnitType/unitType";
import {Village} from "./village";
import {VillageService} from "../services/villageService";
import {error} from "util";
import {EditingVillageArmiesService} from "../services/editing-village-armis.service";
@Component({
    selector: '[player-ro]',
    outputs: ['selectedVillageChanged'],
    template: `
<td>
    <div *ngIf="!isForm">{{village.name}}</div>
    <input *ngIf="isForm" (ngModelChange)="this.newVillage.name=$event" type="text" [ngModel]="this.village.name"  name="name"></td>
    
<td>
    <div *ngIf="!isForm">{{village.population}}</div>
    <input *ngIf="isForm" (ngModelChange)="this.newVillage.population=$event" type="text" [ngModel]="this.village.population" name="population">
</td>
<td>
    <div *ngIf="!isForm" >{{this.village.xCoord}}</div>
    <input (ngModelChange)="this.newVillage.xCoord=$event" *ngIf="isForm" type="text" [ngModel]="this.village.xCoord" name="x">
</td>
<td >
    <div *ngIf="!isForm">{{village.yCoord}}</div>
    <input *ngIf="isForm" type="text" [ngModel]="this.village.yCoord" name="y" (ngModelChange)="this.newVillage.yCoord=$event">
</td>
<td >
    <div *ngIf="!isForm">{{village.isCapital}}</div>
    <input *ngIf="isForm" type="checkbox" (ngModelChange)="this.newVillage.isCapital=$event" [ngModel]="this.village.isCapital" name="isCapital" id="isCapital" class="filled-in">
    <label *ngIf="isForm" for="isCapital"></label>
</td>

<td >
<div *ngIf="!isForm">{{village.wall}}</div>
<input *ngIf="isForm" type="text" [ngModel]="this.village.wall" (ngModelChange)="this.newVillage.wall=$event" name="wall">
</td>

<td *ngFor="let tp of unitValues">
    <army-cell [type]="tp" [village]="village" 
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
export class VillageRow implements OnInit {
    @Input() village: Village;
    @Input() isForm: boolean;
    ifSaveChanges: boolean;
    private newVillage: Village;
    unitValues: Array<string>;
    selectedVillageChanged: EventEmitter<Village>;

    constructor(private villageService: VillageService, private currVillageArmiesService: EditingVillageArmiesService) {
        this.selectedVillageChanged = new EventEmitter<Village>();
        this.unitValues = [];
        this.ifSaveChanges = false;
        console.log('inside constructor');
        console.log(this.ifSaveChanges);
    }

    ngOnInit(): void {
        this.newVillage = new Village();
        this.newVillage.name = this.village.name;
        this.newVillage.uuid = this.village.uuid;
        this.newVillage.armies = this.village.armies;
        this.newVillage.isCapital = this.village.isCapital;
        this.newVillage.population = this.village.population;
        this.newVillage.xCoord = this.village.xCoord;
        this.newVillage.yCoord = this.village.yCoord;
        this.newVillage.wall = this.village.wall;
        this.getStringUnitTypeValues();
    }

    getStringUnitTypeValues() {
        for (let m in UnitType) {
            if (typeof UnitType[m] === 'number') {
                this.unitValues.push(m);
            }
        }

    }

    submitEdit() {
        this.villageService.update(this.village);
    }

    cellClick(village: Village) {
        this.selectedVillageChanged.emit(village);
    }

    changeVillage() {
        this.ifSaveChanges = true;
        console.log('insideChange');
        console.log(this.village.armies);
        this.village.name = this.newVillage.name;
        this.village.armies = this.currVillageArmiesService.armies;

        this.village.xCoord = this.newVillage.xCoord;
        this.village.yCoord = this.newVillage.yCoord;
        this.village.isCapital = this.newVillage.isCapital;
        this.village.population = this.newVillage.population;
        this.village.wall = this.newVillage.wall;

        this.submitEdit();
        this.isForm = false;
        this.ifSaveChanges = false;

    }

    showEdit() {

        this.selectedVillageChanged.emit(null);
        this.selectedVillageChanged.emit(this.village);
        this.ifSaveChanges = false;
        this.currVillageArmiesService.armies.length = 0;

    }

}