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
<div class="row">
<div class="col offset-s5">
<button  (click)="showAddForm()" class="btn waves-effect waves-light">add</button>
</div>
<add-vill-form [player]="player" *ngIf="showAddVillageForm" (wasSubmitted)="hideAddForm($event)" ></add-vill-form>
`
})
 export class PlayerComponent implements OnInit{
    player:Player;
    id;
    showAddVillageForm:boolean;
    constructor(private currPlayerService:CurrVillageArmiesService, private playerService:PlayerService, private route:ActivatedRoute, private villageService:VillageService){
        this.route.params.subscribe((param:any)=>
        {
            this.id=param['id'];
            this.playerService.url=this.playerService.url+this.id;
        });
        this.showAddVillageForm=false;


    }

    hideAddForm():void{
      this.showAddVillageForm=false;
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

    showAddForm(){
        console.log(this.showAddVillageForm);
        this.showAddVillageForm=true;
    }
}

