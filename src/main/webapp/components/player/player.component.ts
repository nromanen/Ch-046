/**
 * Created by okunetc on 13.01.2017.
 */


import {Player} from "./player";
import {Component, OnInit} from "@angular/core";
import {PlayerService} from "../services/player.service";
import {ActivatedRoute} from "@angular/router";
import {EditingVillageArmiesService} from "../services/editing-village-armis.service";
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
`
})
export class PlayerComponent implements OnInit {
    player: Player;
    id: string;
    showAddVillageForm: boolean;

    constructor(private currPlayerService: EditingVillageArmiesService, private playerService: PlayerService, private route: ActivatedRoute, private villageService: VillageService) {
        this.route.params.subscribe((param: any) => {
            this.id = param['id'];
            this.playerService.url = this.playerService.url + this.id;
        });
        this.showAddVillageForm = false;
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
}

