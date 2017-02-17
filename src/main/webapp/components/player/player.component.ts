/**
 * Created by okunetc on 13.01.2017.
 */


import {Player} from "./player";
import {Component, OnInit} from "@angular/core";
import {PlayerService} from "../services/player.service";
import {TranslateService} from "ng2-translate";

@Component({
    selector: 'player',
    templateUrl:"components/player/playerComponent.html"
})
export class PlayerComponent implements OnInit {
    player: Player;
    showAddVillageForm: boolean;
    successMessage;
    errorMessage;

    constructor(private playerService: PlayerService,private translate: TranslateService) {

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
        this.translate.get(event).subscribe(
            res=>{
                this.successMessage=res;
            }
        );

    }

    showErrorMessage(event:string){
        this.successMessage=null;
        this.translate.get(event).subscribe(
            res=>{
                this.errorMessage=res;
            }
        );

    }
}

