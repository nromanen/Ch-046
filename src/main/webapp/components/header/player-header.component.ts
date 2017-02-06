/**
 * Created by okunetc on 16.01.2017.
 */
import {Component, Input} from "@angular/core";
import {StompService} from "../services/helpNotification/stomp.service";

@Component(
    {
        selector:'player-head',
        templateUrl: 'components/header/playerHeader.html',
        styleUrls: ['components/header/playerHeader.css']
    }
)
export class PlayerHeader{

    public serverResponse: string;
    @Input() isLeader: boolean;
    showNotif: boolean = false;

    constructor(private stompService: StompService){
    }

    public ngOnInit(): void {
        this.stompService.connect();
        this.stompService.getObservable().subscribe(payload => {
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