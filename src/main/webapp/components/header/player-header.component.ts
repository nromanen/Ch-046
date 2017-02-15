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
import {ParserService} from "../services/parser.service";
import {Credentials} from "../modal_parsing_window/credentials";
declare let jQuery:any;
@Component(
    {
        selector: 'player-head',
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
    @Input() isLeader: boolean = false;
    showNotif: boolean = false;
    attacks: any[];
    numOfAttacks: number;
    confirmMsg: string;
    confirmParsing: boolean = false;


    constructor(private stompService: StompService, private parserService: ParserService, private helpService:HelpService) {
    }

    public ngOnInit(): void {
        this.websocketConnect();
        jQuery(".button-collapse").sideNav();
        this.getActiveHelp();
    }

    getActiveHelp(){
        this.helpService.getActiveHelp()
            .subscribe(
                resp=>{
                    this.attacks = resp;
                    console.log(this.attacks);
                    this.numOfAttacks = this.attacks !=null? this.attacks.length : null;
                }
            );


    }


    websocketConnect() {
        console.log("Connection start");
        this.stompService.connect();
        this.stompService.getObservable().subscribe(payload => {
            console.log("Show notification");
            this.serverResponse = payload.outputField;
            this.showNotification();
            this.getActiveHelp();
        });


    }

    showNotification() {
        this.showNotif = true;
    }

    close() {
        this.showNotif = false;
    }

    confirmPars() {
        this.confirmParsing = true;
        this.confirmMsg = `Enter the name and password from the game`
    }

    onConfirmPars(cred: Credentials) {
        console.log(" onConfirmPars()");
        console.log(cred);
        this.confirmParsing = false;
        if (cred.letPasrs) {
            this.parserService.pars(cred)
                .subscribe(
                    status => {
                        if (status) {

                            console.log(status);
                        }
                    },
                    error => {
                        console.log(error);
                    }
                );
        } else {
            cred = null;
        }
    }
}