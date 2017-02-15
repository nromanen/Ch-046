/**
 * Created by okunetc on 16.01.2017.
 */
import {Component, Input, OnInit, AfterViewInit, AfterViewChecked} from "@angular/core";
import {StompService} from "../services/helpNotification/stomp.service";
import {Alliance} from "../alliance/alliance";
import {HelpService} from "../services/helpNotification/help.service";
import {Observable} from "rxjs";
import {isUndefined} from "util";
import {error} from "util";
declare let jQuery:any;
@Component(
    {
        selector:'player-head',
        templateUrl: 'components/header/playerHeader.html',
        styleUrls: ['components/header/playerHeader.css']
    }
)
export class PlayerHeader implements OnInit,AfterViewInit{
    ngAfterViewInit(): void {
        jQuery(".dropdown-button").dropdown();
    }


    @Input() playerLogin;
    public serverResponse: string;
    @Input() isLeader: boolean=false;
    showNotif: boolean = false;


    constructor(private stompService: StompService,){
    }

    public ngOnInit(): void {
        this.websocketConnect();
        jQuery(".button-collapse").sideNav();
    }


    websocketConnect(){
        console.log("Connection start");
        this.stompService.connect();
        this.stompService.getObservable().subscribe(payload => {
            console.log("Show notification");
            this.serverResponse = payload.outputField;
            this.showNotification();
        });


    }

    showNotification(){
        this.showNotif = true;
    }

    close(){
        this.showNotif = false;
    }
}