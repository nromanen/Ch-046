/**
 * Created by Oleg on 14.01.2017.
 */

import {Player} from "./player";
import {Component, Input, OnInit, OnChanges, SimpleChanges, DoCheck, ChangeDetectorRef} from "@angular/core";
import {Village} from "../village/village";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";

import {VillageService} from "../services/villageService";
import {FormGroup, FormBuilder,} from "@angular/forms";
import {TranslateService} from "ng2-translate";

@Component
({
    selector: 'player-list',
    styleUrls:['styles/style.css'],
    templateUrl:"components/player/villagesList.html"

})
export class PlayerList implements OnInit {
    @Input() isPlayerPage;
    @Input('player') player: Player;
    private cdRef: ChangeDetectorRef;
    editVillageForm: FormGroup;
    editError:string;
    successMessage:string;
    unitValues: Array<string>;
    selectedVillage: Village;

    constructor(private currVillageService: CurrVillageArmiesService, private villageService: VillageService,
                private _fBuilder:FormBuilder, cdRef:ChangeDetectorRef,private translate:TranslateService) {
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
        this.editVillageForm.valueChanges
            .subscribe(data =>
            {
                this.cdRef.detectChanges();
            });
    }

    onSubmit(){
        let v:Village=this.editVillageForm.value;
        v.armies=this.currVillageService.armies;
        console.log(this.editVillageForm.value);
    }

    cancelEditing(event){

    }

    showEditError(event:{}){
        this.editError=null;
        this.successMessage=null;
        if (event!=null) {
            this.editError = '';
            for (let field in event) {
                // this.editError += event[field] + " ";
                if(event[field]!='') {
                    console.log(event[field]);
                    console.log(event[field]=="Population can contain numbers only!");
                    this.translate.get(event[field])
                        .subscribe(msg=>{
                            console.log(msg);
                            this.editError += msg + " ";
                        });
                }
            }

        }
    }

    showSuccessMessage(event){
        this.editError=null;
        this.successMessage=null;
        this.successMessage=event;

    }

    closeDialog(){
        this.editError=null;
        this.successMessage=null;
    }

}