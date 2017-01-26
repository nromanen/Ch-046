import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/Subject';
import {Observable} from 'rxjs/Observable';

import 'node_modules/stompjs/lib/stomp.min.js';

declare let Stomp:any;

@Injectable()
export class StompService {

    private stompClient;
    private stompSubject : Subject<any> = new Subject<any>();

    public connect(webSocketUrl: string) : void {
        let self = this;
        let webSocket = new WebSocket(webSocketUrl);
        this.stompClient = Stomp.over(webSocket);
        this.stompClient.connect({}, function (frame) {
            self.stompClient.subscribe('/topic/greetings', function (stompResponse) {
                self.stompSubject.next(JSON.parse(stompResponse.body));
            });
        });
    }

    public send(payload: string) {
        this.stompClient.send("/app/hello", {}, JSON.stringify({'message': 'askHelp'}));
    }

    public getObservable() : Observable<any> {
        return this.stompSubject.asObservable();
    }
}