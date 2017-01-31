/**
 * Created by Oleg on 14.01.2017.
 */
import {PlayerRow} from "./playerRow.component";
import {Player} from "./player";
import {Component, Input, OnInit, OnChanges, SimpleChanges, DoCheck, ViewChild, ChangeDetectorRef} from "@angular/core";
import {Village} from "../village/village";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";
import {UnitType} from "../UnitType/unitType";
import {Army} from "../army/army";
import {VillageService} from "../services/villageService";
import {FormGroup, FormBuilder, FormControl, Validators, FormArray} from "@angular/forms";
import {forbiddenXValidator} from "../village/forbidden-x.directive";
@Component
({
    selector: 'player-list',
    styleUrls:['components/style.css'],
    // templateUrl: "components/player/playerList.html"
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
         
        </tbody>
    </table>
    </form>
</div>

`

})
export class PlayerList implements OnInit, OnChanges,DoCheck {
    editVillageForm: FormGroup;
    POPULATION_REGEXP=/^\d*$/;
    COORD_REGEXP=/^[0-9]*$/;
    private cdRef: ChangeDetectorRef;
    ngDoCheck(): void {

    }
    ngOnChanges(changes: SimpleChanges): void {
        // console.log(this.player);
    }
    @Input('player') player: Player;
    unitValues: Array<string>;
    selectedVillage: Village;


    constructor(private currVillageService: CurrVillageArmiesService, private villageService: VillageService,private _fBuilder:FormBuilder, cdRef:ChangeDetectorRef) {
        this.unitValues = [];
        this.cdRef=cdRef
    }

    ngOnInit(): void {
        this.villageService.villages = this.player.villages;
        this.editVillageForm=this._fBuilder.group({
            // test:[this.selectedVillage.name,[]]
        });
    }

    buildForm(){
        // this.editVillageForm=null;
        this.editVillageForm=this._fBuilder.group({
           // name:[this.selectedVillage.name,[]]
            'name':new FormControl(this.selectedVillage.name,[Validators.required]),
            'population':[this.selectedVillage.population,[Validators.required,Validators.pattern(this.POPULATION_REGEXP)]
            ],
            'xCoord': [this.selectedVillage.xCoord,
                [Validators.required, forbiddenXValidator(),Validators.pattern(this.COORD_REGEXP)]
            ],
            'yCoord':[this.selectedVillage.yCoord,

                [Validators.required,forbiddenXValidator(),Validators.pattern(this.COORD_REGEXP)]
            ],
            'wall':[this.selectedVillage.wall,
                [Validators.required,Validators.pattern(this.POPULATION_REGEXP)]
            ],
            'isCapital':[this.selectedVillage.isCapital],
            'armies':this._fBuilder.array([]),
        });

        const control = <FormArray>this.editVillageForm.controls['armies'];
        let i=0;
        // this.selectedVillage.armies.forEach(army=>{
        //     control.push(this.initArmies(i));
        //
        // });
        for (let i=0;i<27;i++) {
            control.push(this.initArmies(i));

        }

        console.log(control);
    }

    initArmies(ord:number) {
        // initialize our address
        let h:string;

        return this._fBuilder.group({
            // value: [this.selectedVillage.armies[ord]!=undefined?this.selectedVillage.armies[ord].count:''],
            value:['',[Validators.required,Validators.pattern(this.POPULATION_REGEXP)]],
        });
    }

    wasEdited(village: Village) {
        this.currVillageService.village = village;
    }

    changeSelectedVillage(village: Village) {
        this.selectedVillage = village;
        console.log(this.selectedVillage);
        console.log(this.editVillageForm);
        this.buildForm();
        this.editVillageForm.valueChanges
            .subscribe(data =>
            {
                this.cdRef.detectChanges();
            });
        // console.log(this.selectedVillage);
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