/**
 * Created by okunetc on 13.01.2017.
 */


import {PlayerList} from "./playerList.component";
import {Player} from "./player";
import {Component, OnInit} from "@angular/core";
import {PlayerService} from "../services/player.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Village} from "../village/village";
import {CurrPlayerService} from "../services/currentPlayer.service";
@Component({
    selector: 'player',
    template:`
<player-head></player-head>
<player-list *ngIf="player" [player]="player"></player-list>
<button (click)="f()"></button>
`
})
 export class PlayerComponent implements OnInit{
    player:Player;
    id;
    constructor(private currPlayerService:CurrPlayerService,private playerService:PlayerService,private route:ActivatedRoute){
        this.route.params.subscribe((param:any)=>
        {
            this.id=param['id'];
            this.playerService.url=this.playerService.url+this.id;
        });


    }

    f():void{
        console.log('clicked');
        console.log(this.player);
        this.player.villages.push(new Village);
    }

    ngOnInit(): void {
        this.playerService.getById()
            .subscribe(
                player=>{
                    this.player=player;
                    console.log(this.player);
                    this.currPlayerService.player=this.player;
                }
            );
    }
}

