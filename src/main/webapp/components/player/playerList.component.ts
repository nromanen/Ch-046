/**
 * Created by Oleg on 14.01.2017.
 */

import {Player} from "./player";
import {Component, Input, OnInit, OnChanges, SimpleChanges, DoCheck, ViewChild, ChangeDetectorRef} from "@angular/core";
import {Village} from "../village/village";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";
import {UnitType} from "../UnitType/unitType";
import {Army} from "../army/army";
import {VillageService} from "../services/villageService";
import {FormGroup, FormBuilder, FormControl, Validators, FormArray} from "@angular/forms";

import {type} from "os";
@Component
({
    selector: 'player-list',
    styleUrls:['styles/style.css'],
    template:`

<div class="row ">
<form  (ngSubmit)="onSubmit()">
    <table class="fi responsive-table">
        <thead>
        <tr>
            <th>Village</th>
            <th>Population</th>
            <th>X</th>
            <th>Y</th>
            <th>Capital?</th>
            <th>Wall level</th>
            <th><img src="images/Gauls/GalFal.gif"></th>
            <th><img src="images/Gauls/GalSwordsman.gif"></th>
            <th><img src="images/Gauls/GalPathFinder.gif"></th>
            <th><img src="images/Gauls/GalTewtThunder.gif"></th>
            <th><img src="images/Gauls/GalDruid.gif"></th>
            <th><img src="images/Gauls/Edui.gif"></th>
            <th><img src="images/Gauls/GalRam.gif"></th>
            <th><img src="images/Gauls/GalCatapult.gif"></th>
            <th><img src="images/Gauls/GaLeader.gif"></th>
            <th><img src="images/Germans/Clubswinger.gif"></th>
            <th><img src="images/Germans/Spearman.gif"></th>
            <th><img src="images/Germans/Toporshchik.gif"></th>
            <th><img src="images/Germans/Skaut.gif"></th>
            <th><img src="images/Germans/Paladin.gif"></th>
            <th><img src="images/Germans/Tevtonskaya-konnitsa.gif"></th>
            <th><img src="images/Germans/Taran-ger.gif"></th>
            <th><img src="images/Germans/Katapulta-ger.gif"></th>
            <th><img src="images/Germans/Leader.gif"></th>
            <th><img src="images/Rome/Legioner.gif"></th>
            <th><img src="images/Rome/Praetorian.gif"></th>
            <th><img src="images/Rome/Imperianets.gif"></th>
            <th><img src="images/Rome/Konnyy-razvedchik.gif"></th>
            <th><img src="images/Rome/Konnitsa-imperatora.gif"></th>
            <th><img src="images/Rome/Konnitsa-Tsezarya.gif"></th>
            <th><img src="images/Rome/Taran-rim.gif"></th>
            <th><img src="images/Rome/Ognennaya-katapulta.gif"></th>
            <th><img src="images/Rome/Senator.gif"></th>
            <th></th>
        </tr>
        </thead>

        <tbody>
       
        <tr player-ro [v]="v" *ngFor="let v of player.villages" [isForm]="v==selectedVillage"
            (selectedVillageChanged)="changeSelectedVillage($event)" [editVillageForm]="editVillageForm" (keyup)="cancelEditing($event.keyCode)">       
</tr>
<tr *ngIf=""></tr>
         
        </tbody>
    </table>
    </form>
</div>

`

})
export class PlayerList implements OnInit, OnChanges,DoCheck {
    editVillageForm: FormGroup;
    private cdRef: ChangeDetectorRef;
    ngDoCheck(): void {

    }
    ngOnChanges(changes: SimpleChanges): void {
        // console.log(this.player);
    }
    @Input('player') player: Player;
    unitValues: Array<string>;
    selectedVillage: Village;


    constructor(private currVillageService: CurrVillageArmiesService, private villageService: VillageService,
                private _fBuilder:FormBuilder, cdRef:ChangeDetectorRef) {
        this.unitValues = [];
        this.cdRef=cdRef
    }

    ngOnInit(): void {
        this.villageService.villages = this.player.villages;
        this.editVillageForm=this._fBuilder.group({
        });
    }




    changeSelectedVillage(village: Village) {
        this.selectedVillage = village;
        console.log("v changed");
        // this.buildForm();
        this.editVillageForm.valueChanges
            .subscribe(data =>
            {
                this.cdRef.detectChanges();
            });

        this.player.villages.forEach(v=>{
            console.log();
            if (village==v){
                console.log('found equal');
            }
        });

    }

    onSubmit(){
        let v:Village=this.editVillageForm.value;
        v.armies=this.currVillageService.armies;
        console.log(this.editVillageForm.value);
        // this.editVillageForm.valid=false;
    }

    cancelEditing(event){

    }


}