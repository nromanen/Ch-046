/**
 * Created by Oleg on 14.01.2017.
 */
import {PlayerRow} from "./playerRow.component";
import {Player} from "./player";
import {Component, Input, OnInit} from "@angular/core";
import {Village} from "../village/village";
import {CurrVillageService} from "../services/currentVillage.service";
@Component
({
    selector: 'player-list',
    template: `
<table>
<thead >
    <tr>
        <th>Village</th>
        <th>Population</th>
        <th>X</th>
        <th>Y</th>
        <th>Capital?</th>
        <th>Wall level</th>
        <th>Armies</th>
        <th>Quantity</th>
    </tr>
    </thead>
    
     <tbody *ngFor="let v of player.villages">
    <tr>
        <td rowspan="3">{{v.name}}</td>
        <td rowspan="3">{{v.population}}</td>
        <td rowspan="3">{{v.xCoord}}</td>
        <td rowspan="3">{{v.yCoord}}</td>
        <td rowspan="3">{{v.isCapital}}</td>
        <td rowspan="3">{{v.wall}}</td>
        <td>type1</td>
        <td>63</td>
        <td><player-row [v]="v" (editClick)="wasEdited($event)"></player-row></td>
   
    </tr>
    
    <tr>
        <td>type2</td>
        <td>23</td>
    </tr>
    <tr>
        <td>type2</td>
        <td>23</td>
    </tr>
    </tbody>
</table>
`
})
export class PlayerList implements OnInit{
    ngOnInit(): void {
        console.log(this.player);
    }
 @Input('player') player:Player;

    constructor(private currVillageService:CurrVillageService){

    }

    wasEdited(village:Village){
        console.log('was edited');
        console.log(village);
        this.currVillageService.village=village;
    }

}