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
    // templateUrl: "components/player/playerList.html"
    template:`
<form #heroForm="ngForm">
<div class="row ">
    <table>
        <thead>
        <tr>
            <th>Village</th>
            <th>Population</th>
            <th>X</th>
            <th>Y</th>
            <th>Capital?</th>
            <th>Wall level</th>
            <td><img src="images/Gauls/GalFal.gif"></td>
            <td><img src="images/Gauls/GalSwordsman.gif"></td>
            <td><img src="images/Gauls/GalPathFinder.gif"></td>
            <td><img src="images/Gauls/GalTewtThunder.gif"></td>
            <td><img src="images/Gauls/GalDruid.gif"></td>
            <td><img src="images/Gauls/Edui.gif"></td>
            <td><img src="images/Gauls/GalRam.gif"></td>
            <td><img src="images/Gauls/GalCatapult.gif"></td>
            <td><img src="images/Gauls/GaLeader.gif"></td>
            <td><img src="images/Germans/Clubswinger.gif"></td>
            <td><img src="images/Germans/Spearman.gif"></td>
            <td><img src="images/Germans/Toporshchik.gif"></td>
            <td><img src="images/Germans/Skaut.gif"></td>
            <td><img src="images/Germans/Paladin.gif"></td>
            <td><img src="images/Germans/Tevtonskaya-konnitsa.gif"></td>
            <td><img src="images/Germans/Taran-ger.gif"></td>
            <td><img src="images/Germans/Katapulta-ger.gif"></td>
            <td><img src="images/Germans/Leader.gif"></td>
            <td><img src="images/Rome/Legioner.gif"></td>
            <td><img src="images/Rome/Praetorian.gif"></td>
            <td><img src="images/Rome/Imperianets.gif"></td>
            <td><img src="images/Rome/Konnyy-razvedchik.gif"></td>
            <td><img src="images/Rome/Konnitsa-imperatora.gif"></td>
            <td><img src="images/Rome/Konnitsa-Tsezarya.gif"></td>
            <td><img src="images/Rome/Taran-rim.gif"></td>
            <td><img src="images/Rome/Ognennaya-katapulta.gif"></td>
            <td><img src="images/Rome/Senator.gif"></td>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <tr player-ro [v]="v" *ngFor="let v of player.villages" [isForm]="v==selectedVillage"
            (selectedVillageChanged)="changeSelectedVillage($event)">
        </tbody>
    </table>

</div>
</form>
`

})
export class PlayerList implements OnInit, OnChanges,DoCheck {
    ngDoCheck(): void {

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