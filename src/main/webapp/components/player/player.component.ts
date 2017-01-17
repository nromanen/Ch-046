/**
 * Created by okunetc on 13.01.2017.
 */


import {PlayerList} from "./playerList.component";
import {Player} from "./player";
import {Component} from "@angular/core";
import {PlayerService} from "../services/player.service";
import {ActivatedRoute, Params} from "@angular/router";
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
    constructor(private playerService:PlayerService,private route:ActivatedRoute){
        this.players=[new Player(), new Player(),new Player];
    }

    gt(){
        this.playerService.activatedRoute=this.route;
        // this.route.queryParams.subscribe((params:Params)=>
        // {
        //     console.log(params);
        // });
        // this.route.params.subscribe((params:Params)=>
        // {
        //     console.log(params);
        // });
        this.playerService.getById();
    }
}

