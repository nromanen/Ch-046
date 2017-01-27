/**
 * Created by Oleg on 14.01.2017.
 */
import {PlayerRow} from "./playerRow.component";
import {Player} from "./player";
import {Component, Input, OnInit, OnChanges, SimpleChanges, DoCheck} from "@angular/core";
import {Village} from "../village/village";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";
import {UnitType} from "../UnitType/unitType";
import {Army} from "../army/army";
import {VillageService} from "../services/villageService";
@Component
({
    selector: 'player-list',
    templateUrl: "components/player/playerList.html"

})
export class PlayerList implements OnInit, OnChanges,DoCheck {
    ngDoCheck(): void {
        console.log(this.player);
    }
    ngOnChanges(changes: SimpleChanges): void {
        // console.log(this.player);
    }
    @Input('player') player: Player;
    unitValues: Array<string>;
    selectedVillage: Village;

    constructor(private currVillageService: CurrVillageArmiesService, private villageService: VillageService) {
        this.unitValues = [];
    }

    ngOnInit(): void {
        // console.log(this.player);
        this.villageService.villages = this.player.villages;
    }


    wasEdited(village: Village) {
        this.currVillageService.village = village;
    }

    changeSelectedVillage(village: Village) {
        this.selectedVillage = village;
        // console.log(this.selectedVillage);
    }


}