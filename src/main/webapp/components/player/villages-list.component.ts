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

        this.editVillageForm.valueChanges
            .subscribe(data =>
            {
                this.cdRef.detectChanges();
            });
    }

    onSubmit(){
        let v:Village=this.editVillageForm.value;
        v.armies=this.currVillageService.armies;

    }

    cancelEditing(event){

    }

    showEditError(event:{}){
        this.editError=null;
        this.successMessage=null;
        if (event!=null) {
            this.editError = '';
            for (let field in event) {
                if(event[field]!='') {
                    this.translate.get(event[field])
                        .subscribe(msg=>{
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