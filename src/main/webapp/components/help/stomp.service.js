System.register(["@angular/core", "rxjs/Subject", "node_modules/stompjs/lib/stomp.min.js"], function (exports_1, context_1) {
    "use strict";
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var __moduleName = context_1 && context_1.id;
    var core_1, Subject_1, StompService, StompService_1;
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
            StompService = StompService_1 = (function () {
                function StompService() {
                    this.stompSubject = new Subject_1.Subject();
                    this.WEBSOCKETURL = 'ws://localhost:8080/travian/stompTest';
                    var webSocket = new WebSocket(this.WEBSOCKETURL);
                    StompService_1.stompClient = Stomp.over(webSocket);
                }
                StompService.prototype.connect = function () {
                    var self = this;
                    if (!StompService_1.stompClient.isConnected) {
                        console.log("Use static! Connecting to websocket server");
                        StompService_1.stompClient.connect({}, function (frame) {
                            StompService_1.stompClient.subscribe('/topic/greetings', function (stompResponse) {
                                self.stompSubject.next(JSON.parse(stompResponse.body));
                            });
                        });
                    }
                };
                StompService.prototype.send = function (payload) {
                    StompService_1.stompClient.send("/app/hello", {}, JSON.stringify({ 'message': 'askHelp' }));
                };
                StompService.prototype.getObservable = function () {
                    return this.stompSubject.asObservable();
                };
                return StompService;
            }());
            StompService = StompService_1 = __decorate([
                core_1.Injectable(),
                __metadata("design:paramtypes", [])
            ], StompService);
            exports_1("StompService", StompService);
        }
    };
});
//# sourceMappingURL=stomp.service.js.map