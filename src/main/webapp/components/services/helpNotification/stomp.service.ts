import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/Subject';
import {Observable} from 'rxjs/Observable';

import 'js/stomp.min.js';
import {HelpService} from "./help.service";
import {Alliance} from "../../alliance/alliance";

declare let Stomp: any;

@Injectable()
export class StompService {

    private static stompClient;
    private stompSubject: Subject<any> = new Subject<any>();
    private WEBSOCKETURL = 'ws://localhost:8080/travian/stompTest';

    public  alliance: Alliance = null;

    constructor(private helpService: HelpService) {
    }

    private connectInit(){
        let self = this;
        this.helpService.getAlliance()
                .subscribe(
                    resp => {
                        console.log("APP_COMPONENT_SUBSCRIBE");
                        this.alliance = resp;
                        console.log(this.alliance);

                        let webSocket = new WebSocket(this.WEBSOCKETURL);
                        StompService.stompClient = Stomp.over(webSocket);
                        console.log("Use static! Connecting to websocket server");
                        StompService.stompClient.connect({}, function (frame) {
                            StompService.stompClient.subscribe('/topic/notification/' + resp.allianceUuid, function (stompResponse) {
                                self.stompSubject.next(JSON.parse(stompResponse.body));
                            });
                        });
                    }
                )
    }

    public connect(): void {

        if (StompService.stompClient == null || StompService.stompClient.OPENED) {
            this.connectInit();
        }
    }

    public send() {
        StompService.stompClient.send("/app/help/" + this.alliance.allianceUuid, {}, JSON.stringify({'message': 'askHelp'}));
    }

    public getObservable(): Observable<any> {
        return this.stompSubject.asObservable();
    }
}