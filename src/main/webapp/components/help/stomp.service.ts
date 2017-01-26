import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/Subject';
import {Observable} from 'rxjs/Observable';

import 'node_modules/stompjs/lib/stomp.min.js';

declare let Stomp:any;

@Injectable()
export class StompService {

    private static stompClient;
    private stompSubject : Subject<any> = new Subject<any>();
    private WEBSOCKETURL = 'ws://localhost:8080/travian/stompTest';

    constructor(){
        let webSocket = new WebSocket(this.WEBSOCKETURL);
        StompService.stompClient = Stomp.over(webSocket);
    }

    public connect() : void {
        let self = this;
        if (!StompService.stompClient.isConnected) {
            console.log("Use static! Connecting to websocket server")
            StompService.stompClient.connect({}, function (frame) {
                StompService.stompClient.subscribe('/topic/greetings', function (stompResponse) {
                    self.stompSubject.next(JSON.parse(stompResponse.body));
                });
            });
        }
    }


    public send(payload: string) {
        StompService.stompClient.send("/app/hello", {}, JSON.stringify({'message': 'askHelp'}));
    }

    public getObservable() : Observable<any> {
        return this.stompSubject.asObservable();
    }
}