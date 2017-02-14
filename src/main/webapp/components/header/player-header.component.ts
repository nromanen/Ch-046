/**
 * Created by okunetc on 16.01.2017.
 */
import {Component, Input} from "@angular/core";
import {StompService} from "../services/helpNotification/stomp.service";
import {ParserService} from "../services/parser.service";
import {Credentials} from "../modal_parsing_window/Credentials";

@Component(
    {
        selector: 'player-head',
        templateUrl: 'components/header/playerHeader.html',
        styleUrls: ['components/header/playerHeader.css']
    }
)
export class PlayerHeader {

    public serverResponse: string;
    @Input() isLeader: boolean = false;
    showNotif: boolean = false;
    confirmMsg: string;
    confirmParsing: boolean = false;


    constructor(private stompService: StompService, private parserService: ParserService) {
    }

    public ngOnInit(): void {
        this.websocketConnect();
    }

    // getAlliance(){
    //
    //
    //     this.helpService.getAlliance()
    //         .subscribe(
    //             resp => {
    //                 console.log("APP_COMPONENT_SUBSCRIBE");
    //                 PlayerHeader.alliance = resp;
    //                 console.log(PlayerHeader.alliance);
    //             }
    //
    //         )
    //
    // }

    websocketConnect() {
        console.log("Connection start");
        this.stompService.connect();
        this.stompService.getObservable().subscribe(payload => {
            console.log("Show notification");
            this.serverResponse = payload.outputField;
            this.showNotification();
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