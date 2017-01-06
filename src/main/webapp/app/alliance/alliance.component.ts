import {Component} from 'angular2/core'
import {AllianceService} from "./alliance-service";
import {Alliance} from "./alliance";


@Component({
    selector: 'my-alliance',
    template: `
         <div class="todo">
            <h1>ALLIANCE WORK!!!</h1>
            <h1>TEST ngFOR!!</h1>
            
            <div class="list">
                <ul>
                    <li>...Start</li>
                    <li  *ngFor="#al of _allianceService.alliances">{{al.uuid}} {{al.name}} {{al.leaderLogin}} {{al.leaderEmail}}</li>
                    <li>...End</li>
                </ul>
            </div>
         </div>
    `,
    providers: [AllianceService],
})

export class AllianceComponent{

    alliances: Array<Alliance> = [];
    title: string = "test";

    constructor(private _allianceService: AllianceService){

    }

}