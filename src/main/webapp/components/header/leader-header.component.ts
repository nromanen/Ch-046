/**
 * Created by vyach on 26.01.2017.
 */

import {Component} from "@angular/core";
import {StompService} from "../services/helpNotification/stomp.service";
import {PlayerHeader} from "./player-header.component";
import {HelpService} from "../services/helpNotification/help.service";
import {Alliance} from "../alliance/alliance";


@Component({
    selector: 'leader-header',
    template: `
<nav>
    <div class="nav-wrapper">
        <a class="brand-logo">Logo</a>
        <ul class="right hide-on-med-and-down">
            <li><a routerLink="/leader" routerLinkActive="active">Main</a></li>
            <li><a routerLink="/leader/manager" routerLinkActive="active">Manage</a></li>
            <li><a routerLink="/help" routerLinkActive="active">Ask help</a></li>
            <li><a routerLink="/allHelps" routerLinkActive="active">Attacks</a></li>
            <li><a href="/travian/parser/">Parse</a></li>
            <li><a href="/travian/logout">Logout</a></li>
        </ul>
    </div>
</nav>
<div id="note" *ngIf="showNotif" routerLink="/allHelps" routerLinkActive="active">
    One of alliance member needs help!
    <div  class="right" style="cursor: pointer;" (click) = "close()"> x </div>
</div>
    `,
    styleUrls: ['components/header/playerHeader.css']
})
export class LeaderHeaderComponent {
    public serverResponse: string;
    public static alliance: Alliance = null;

    showNotif: boolean = false;


    constructor(private stompService: StompService, private helpService: HelpService){
    }

    public ngOnInit(): void {
        this.websocketConnect();
    }


    websocketConnect(){
        console.log("Connection start");
        this.stompService.connect();
        this.stompService.getObservable().subscribe(payload => {
            console.log("Show notification");
            // this.serverResponse = payload.outputField;
            this.showNotification();
        });


    }

    showNotification(){
        console.log("Show not true");
        this.showNotif = true;
    }

    close(){
        this.showNotif = false;
    }
    
}
