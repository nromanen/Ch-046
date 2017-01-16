import {Component} from "@angular/core";
/**
 * Created by Oleg on 14.01.2017.
 */

@Component
({
    selector:'player-row',
    template:`
    <tbody>
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
    </tbody>`
})
export class PlayerRow{

}