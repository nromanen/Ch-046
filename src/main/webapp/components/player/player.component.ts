/**
 * Created by okunetc on 13.01.2017.
 */


import {Player} from "./player";
import {Component, OnInit, ViewChild} from "@angular/core";
import {PlayerService} from "../services/player.service";
import {ActivatedRoute} from "@angular/router";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";
import {VillageService} from "../services/villageService";
import {UnitType} from "../UnitType/unitType";
import {Army} from "../army/army";
@Component({
    selector: 'player',
    template: `
        <player-head></player-head>
        <div *ngIf="successEditMessage!=null||errorEditMessage!=null" class="col s4 offset-s4 ">
    <div  [ngClass]="{'card':true, 'green':successEditMessage!=null, 'red':errorEditMessage!=null, 'lighten-5':true}">
        <div [ngClass]="{'card-content':true , 'green-text':successMessage!=null,'red-text':errorMessage!=null }">
            <p>{{successEditMessage!=null?successEditMessage:errorEditMessage}} <span (click)="closeDialog()" class="right">x</span></p>
        </div>
    </div>
</div>
        <player-list *ngIf="player" [player]="player"></player-list>
        <div class="row">
            <div class="col s4 offset-s6" >
                <button (click)="showAddForm()" class="btn waves-effect waves-light">Add</button>
            </div>
            <div class="row">
<div *ngIf="successMessage!=null||errorMessage!=null" class="col s4 offset-s4 ">
    <div  [ngClass]="{'card':true, 'green':successMessage!=null, 'red':errorMessage!=null, 'lighten-5':true}">
        <div [ngClass]="{'card-content':true , 'green-text':successMessage!=null,'red-text':errorMessage!=null }">
            <p>{{successMessage!=null?successMessage:errorMessage}} <span (click)="closeDialog()" class="right">x</span></p>
        </div>
    </div>
</div>
</div>
            <add-vill-form [player]="player" *ngIf="showAddVillageForm" (wasSubmitted)="hideAddForm($event)"
            (successMessage)="showSuccessMessage($event)" (errorMessage)="showErrorMessage($event)"></add-vill-form>
        </div>


`
})
export class PlayerComponent implements OnInit {
    player: Player;
    showAddVillageForm: boolean;
    successMessage;
    errorMessage;

    constructor(private playerService: PlayerService) {

    }

    ngOnInit(): void {
        this.playerService.getById()
            .subscribe(
                player => {
                    this.player = player;
                    console.log(this.player.alliance.allianceUuid);
                }
            );


    }

    hideAddForm(): void {
        this.showAddVillageForm = false;
    }

    showAddForm() {
        this.showAddVillageForm = true;
    }

    closeDialog(){
        this.errorMessage=null;
        this.successMessage=null;
    }

    showSuccessMessage(event:string){
        this.errorMessage=null;
        this.successMessage=event;
    }

    showErrorMessage(event:string){
        this.successMessage=null;
          this.errorMessage=event;
    }
}

