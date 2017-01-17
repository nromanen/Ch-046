/**
 * Created by Oleg on 14.01.2017.
 */
import {PlayerRow} from "./playerRow.component";
import {Player} from "./player";
import {Component, Input, OnInit} from "@angular/core";
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
    <tr >
        <td rowspan="2">{{v.name}}</td>
        <td rowspan="2">{{v.population}}</td>
        <td rowspan="2">{{v.xCoord}}</td>
        <td rowspan="2">{{v.yCoord}}</td>
        <td rowspan="2">{{v.isCapital}}</td>
        <td rowspan="2">{{v.wall}}</td>
        <td>type1</td>
        <td>63</td>
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
    constructor(){


    }

}