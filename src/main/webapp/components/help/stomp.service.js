System.register(["@angular/core", "rxjs/Subject", "node_modules/stompjs/lib/stomp.min.js"], function (exports_1, context_1) {
    "use strict";
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __moduleName = context_1 && context_1.id;
    var core_1, Subject_1, StompService;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (Subject_1_1) {
                Subject_1 = Subject_1_1;
            },
            function (_1) {
            }
        ],
        execute: function () {
            StompService = (function () {
                function StompService() {
                    this.stompSubject = new Subject_1.Subject();
                }
                StompService.prototype.connect = function (webSocketUrl) {
                    var self = this;
                    var webSocket = new WebSocket(webSocketUrl);
                    this.stompClient = Stomp.over(webSocket);
                    this.stompClient.connect({}, function (frame) {
                        self.stompClient.subscribe('/topic/greetings', function (stompResponse) {
                            self.stompSubject.next(JSON.parse(stompResponse.body));
                        });
                    });
                };
                StompService.prototype.send = function (payload) {
                    this.stompClient.send("/app/hello", {}, JSON.stringify({ 'message': 'askHelp' }));
                };
                StompService.prototype.getObservable = function () {
                    return this.stompSubject.asObservable();
                };
                return StompService;
            }());
            StompService = __decorate([
                core_1.Injectable()
            ], StompService);
            exports_1("StompService", StompService);
        }
    };
});
//# sourceMappingURL=stomp.service.js.map