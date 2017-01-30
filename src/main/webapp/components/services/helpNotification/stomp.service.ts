import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/Subject';
import {Observable} from 'rxjs/Observable';

import 'node_modules/stompjs/lib/stomp.min.js';
import {AppComponent} from "../../app.component";

declare let Stomp: any;

@Injectable()
export class StompService {

    private static stompClient;
    private stompSubject: Subject<any> = new Subject<any>();
    private WEBSOCKETURL = 'ws://localhost:8080/travian/stompTest';

    constructor() {
        this.connectInit();
    }

    private connectInit(){
        let self = this;
        let e = new Date().getTime() + 300;
        while (new Date().getTime() <= e) {
        }
        let webSocket = new WebSocket(this.WEBSOCKETURL);
        StompService.stompClient = Stomp.over(webSocket);
        console.log("Use static! Connecting to websocket server");
        StompService.stompClient.connect({}, function (frame) {


            StompService.stompClient.subscribe('/topic/greetings/' + AppComponent.alliance.allianceUuid, function (stompResponse) {
                self.stompSubject.next(JSON.parse(stompResponse.body));
            });
        });
    }

    public connect(): void {

        if (StompService.stompClient.OPENED) {
            this.connectInit();
        }
    }


    public send(payload: string) {
        StompService.stompClient.send("/app/hello/" + AppComponent.alliance.allianceUuid, {}, JSON.stringify({'message': 'askHelp'}));
    }

    public getObservable(): Observable<any> {
        return this.stompSubject.asObservable();
    }
}