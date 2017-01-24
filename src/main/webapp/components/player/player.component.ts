/**
 * Created by okunetc on 13.01.2017.
 */


import {PlayerList} from "./playerList.component";
import {Player} from "./player";
import {Component, OnInit} from "@angular/core";
import {PlayerService} from "../services/player.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Village} from "../village/village";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";
import {VillageService} from "../services/villageService";
import {Army} from "../army/army";
import {UnitType} from "../UnitType/unitType";
@Component({
    selector: 'player',
    template:`
<player-head></player-head>
<player-list *ngIf="player" [player]="player"></player-list>
<button  (click)="f()" >add</button>
<add-vill-form [player]="player"></add-vill-form>
`
})
 export class PlayerComponent implements OnInit{
    player:Player;
    id;
    constructor(private currPlayerService:CurrVillageArmiesService, private playerService:PlayerService, private route:ActivatedRoute, private villageService:VillageService){
        this.route.params.subscribe((param:any)=>
        {
            this.id=param['id'];
            this.playerService.url=this.playerService.url+this.id;
        });


    }

    f():void{
        console.log('clicked');
        console.log(this.player);
        let village=new Village();
        let armies:Army[]=[];
        village.armies=armies;
        alert(JSON.stringify(village));
        village.armies.push(new Army(15,UnitType.Legionnaire,true));
        this.player.villages.push(village);
    }

    ngOnInit(): void {
        this.playerService.getById()
            .subscribe(
                player=>{
                    this.player=player;
                    console.log(this.player);
                    // alert(JSON.stringify(this.player));
                }
            );
    }
}

