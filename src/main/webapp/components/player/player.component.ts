/**
 * Created by okunetc on 13.01.2017.
 */


import {Player} from "./player";
import {Component, OnInit} from "@angular/core";
import {PlayerService} from "../services/player.service";
import {ActivatedRoute} from "@angular/router";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";
import {VillageService} from "../services/villageService";
@Component({
    selector: 'player',
    template: `
        <player-head></player-head>
        <player-list *ngIf="player" [player]="player"></player-list>
        <div class="row">
            <div class="col offset-s5">
                <button (click)="showAddForm()" class="btn waves-effect waves-light">add</button>
            </div>
            <add-vill-form [player]="player" *ngIf="showAddVillageForm" (wasSubmitted)="hideAddForm($event)"></add-vill-form>
        </div>
        <button (click)="changePlayer()">ghfh</button>
`
})
export class PlayerComponent implements OnInit {
    player: Player;

    showAddVillageForm: boolean;

    constructor(private currPlayerService: CurrVillageArmiesService, private playerService: PlayerService, private route: ActivatedRoute, private villageService: VillageService) {

    }

    ngOnInit(): void {
        this.playerService.getById()
            .subscribe(
                player => {
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

    changePlayer(){
        let i:number;
        i++;
        this.player.login=''+i;
    }
}

