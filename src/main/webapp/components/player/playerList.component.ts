/**
 * Created by Oleg on 14.01.2017.
 */
import {PlayerRow} from "./playerRow.component";
import {Player} from "./player";
import {Component} from "@angular/core";
@Component
({
    selector: 'player-list',

    inputs:['players'],
    // templateUrl:'components/player/playerList.html'
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
    
     <tbody *ngFor="let p of players">
    <tr >
        <td rowspan="2">Village1</td>
        <td rowspan="2">30</td>
        <td rowspan="2">35</td>
        <td rowspan="2">70</td>
        <td rowspan="2">true</td>
        <td rowspan="2">50</td>
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
export class PlayerList {
 players:Player[];
    constructor(){
        console.log(this.players);
    }

}