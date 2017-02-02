/**
 * Created by okunetc on 13.01.2017.
 */


import {Player} from "./player";
import {Component, OnInit} from "@angular/core";
import {PlayerService} from "../services/player.service";

@Component({
    selector: 'player',
    template: `
        <player-head [isLeader]="player.isLeader"></player-head>
        
        <div class="row container">
    <div class="col s12 center-align">
        <div class="col s6 left-align">
            <h4>{{ player.login }}</h4>
        </div>
        <div class="col s6 right-align">
            <h4>alliance: {{ player.alliance }}</h4>
        </div>
    </div>
</div>
        
        <player-list *ngIf="player" [player]="player"></player-list>
        <div class="row">
            <div class="col offset-s5">
                <button (click)="showAddForm()" class="btn waves-effect waves-light">add</button>
            </div>
            <add-vill-form [player]="player" *ngIf="showAddVillageForm" (wasSubmitted)="hideAddForm($event)"></add-vill-form>
        </div>
`
})
export class PlayerComponent implements OnInit {
    player: Player;

    showAddVillageForm: boolean;

    constructor(private playerService: PlayerService) {
        this.player = new Player();
    }

    ngOnInit(): void {
        this.playerService.getById()
            .subscribe(
                player => {
                    console.info(`PlayerComponent ngOnInit() is working. Player: ${JSON.stringify(player)}`);
                    this.player = player;
                }
            );
    }

    hideAddForm(): void {
        this.showAddVillageForm = false;
    }

    showAddForm() {
        this.showAddVillageForm = true;
    }
}

