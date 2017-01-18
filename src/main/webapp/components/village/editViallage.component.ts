import {Component} from "@angular/core";
import {Input} from "@angular/core/src/metadata/directives";
import {Player} from "../player/player";
import {CurrPlayerService} from "../services/currentPlayer.service";
/**
 * Created by Oleg on 17.01.2017.
 */
@Component({
    selector:'edit-village',
    template:`
    <player-head></player-head>
    <div>hello{{currPlayerService.player.password}}</div>`
})
export class EditVillageComponent{
 player:Player;
    constructor(private currPlayerService:CurrPlayerService)
    {
        console.log(this.currPlayerService.player);
    }
}