/**
 * Created by okunetc on 13.01.2017.
 */


import {PlayerList} from "./playerList.component";
import {Player} from "./player";
import {Component} from "@angular/core";
import {PlayerService} from "../services/player.service";
@Component({
    selector: 'player',

    // templateUrl : 'components/player/player.html'
    template:`
<player-head></player-head>
<player-list [players]="players"></player-list>
<button (click)="gt()">button</button>
`
})
 export class PlayerComponent {
    players:Player[];
    constructor(private playerService:PlayerService){
        this.players=[new Player(), new Player(),new Player];
    }

    gt(){
        this.playerService.getById();
    }
}

