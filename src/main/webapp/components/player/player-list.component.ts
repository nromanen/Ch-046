/**
 * Created by Oleg on 14.01.2017.
 */
import {Player} from "./player";
import {Component, Input, OnInit} from "@angular/core";
import {Village} from "../village/village";
import {EditingVillageArmiesService} from "../services/editing-village-armis.service";
import {UnitType} from "../UnitType/unitType";
import {VillageService} from "../services/villageService";
@Component
({
    selector: 'player-list',
    templateUrl: "components/player/player-list.component.html"

})
export class PlayerList implements OnInit {
    @Input('player') player: Player;
    unitValues: Array<string>;
    selectedVillage: Village;

    constructor(private currVillageService: EditingVillageArmiesService, private villageService: VillageService) {
        this.unitValues = [];
        this.selectedVillage = null;
        console.log(this.unitValues);
    }

    ngOnInit(): void {
        console.log(UnitType.Legionnaire);
        console.log(this.player.villages[0].armies[0].type);
        console.log(UnitType[UnitType.Legionnaire] == this.player.villages[0].armies[0].type.toString());
        this.villageService.villages = this.player.villages;
    }


    wasEdited(village: Village) {
        this.currVillageService.village = village;
    }

    changeSelectedVillage(village: Village) {
        this.selectedVillage = village;
    }


}