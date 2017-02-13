/**
 * Created by okunetc on 13.01.2017.
 */


import {Player} from "./player";
import {Component, OnInit} from "@angular/core";
import {PlayerService} from "../services/player.service";
import {TranslateService} from "ng2-translate";

@Component({
    selector: 'player',
    template: `
        <player-head *ngIf="player" [isLeader]="player.isLeader"></player-head>
        
        <div class="row container">
    <div class="col s12 center-align">
        <div class="col s6 left-align">
            <h4 *ngIf="player">login:{{ player.login }}</h4>
        </div>
        <div class="col s6 right-align">
            <h4 *ngIf="player">alliance: {{ player.alliance.name }}</h4>
        </div>
    </div>
</div>
                 <div class="row">
<div *ngIf="successMessage!=null||errorMessage!=null" class="col s4 offset-s4 ">
    <div  [ngClass]="{'card':true, 'green':successMessage!=null, 'red':errorMessage!=null, 'lighten-5':true}">
        <div [ngClass]="{'card-content':true , 'green-text':successMessage!=null,'red-text':errorMessage!=null }">
            <p>{{successMessage!=null?successMessage:errorMessage}} <span (click)="closeDialog()" class="right x">x</span></p>
        </div>
    </div>
</div>
</div>
        <player-list *ngIf="player" [player]="player" [isPlayerPage]="true"></player-list>
        <div class="row">
            <div class="col s4 offset-s6" >
                <button (click)="showAddForm()" class="btn waves-effect waves-light">Add</button>
            </div>
         
            <add-vill-form [player]="player" *ngIf="showAddVillageForm" (wasSubmitted)="hideAddForm($event)"
            (successMessage)="showSuccessMessage($event)" (errorMessage)="showErrorMessage($event)"></add-vill-form>
`
})
export class PlayerComponent implements OnInit {
    player: Player;
    showAddVillageForm: boolean;
    successMessage;
    errorMessage;

    constructor(private playerService: PlayerService,translate: TranslateService) {

    }

    ngOnInit(): void {
        this.playerService.getById()
            .subscribe(
                player => {
                    console.info(`PlayerComponent ngOnInit() is working. Player: ${JSON.stringify(player)}`);
                    this.player = player;
                    console.log(this.player.alliance);
                    console.info(this.player.isLeader);
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

