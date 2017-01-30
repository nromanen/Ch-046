/**
 * Created by vyach on 25.01.2017.
 */

import {Component, Input} from '@angular/core';

import {Player} from "./player";

@Component({
    selector: "player-leader", // todo change to player list and rename in playerList.component
    templateUrl: "components/player/player-list.html",
    styleUrls: ["components/player/player-list.css"]
})

export class PlayerListComponent {
    
    @Input() player: Player;
}