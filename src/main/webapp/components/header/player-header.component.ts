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

     @Input() id: string;

    public serverResponse: string;

    showNotif: boolean = false;

    constructor(private stompService: StompService){
    }

    public ngOnInit(): void {
        console.log(this.id);
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