/**
 * Created by okunetc on 16.01.2017.
 */
import {Component} from "@angular/core";
import {StompService} from "../services/helpNotification/stomp.service";

@Component(
    {
        selector:'player-head',
        templateUrl: 'components/header/playerHeader.html',
        styleUrls: ['components/header/playerHeader.css']
    }
)
export class PlayerHeader{

    // EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
    // ALLIANCE_NAME = /^[a-z]{3,9}$/;
    VILLAGE = /^[A-Za-z1-9.]{3,9}$/;
    DATE = /^[A-Za-z1-9. :]{3,20}$/;
    ENEMY = "Enter correct email, please!";
    NAME_ERROR = "Enter from 3 to 10 letters";
    LOGIN_ERROR = "Enter from 3 to 10 letters";

    public serverResponse: string;

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