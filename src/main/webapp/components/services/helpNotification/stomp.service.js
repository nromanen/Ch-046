System.register(['@angular/core', 'rxjs/Subject', 'node_modules/stompjs/lib/stomp.min.js', "./help.service"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, Subject_1, help_service_1;
    var StompService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (Subject_1_1) {
                Subject_1 = Subject_1_1;
            },
            function (_1) {},
            function (help_service_1_1) {
                help_service_1 = help_service_1_1;
            }],
        execute: function() {
            StompService = (function () {
                function StompService(helpService) {
                    this.helpService = helpService;
                    this.stompSubject = new Subject_1.Subject();
                    this.WEBSOCKETURL = 'ws://localhost:8080/travian/stompTest';
                    this.alliance = null;
                    //this.connectInit();
                }
                StompService.prototype.connectInit = function () {
                    var _this = this;
                    var self = this;
                    this.helpService.getAlliance()
                        .subscribe(function (resp) {
                        console.log("APP_COMPONENT_SUBSCRIBE");
                        _this.alliance = resp;
                        console.log(_this.alliance);
                        var webSocket = new WebSocket(_this.WEBSOCKETURL);
                        StompService.stompClient = Stomp.over(webSocket);
                        console.log("Use static! Connecting to websocket server");
                        StompService.stompClient.connect({}, function (frame) {
                            StompService.stompClient.subscribe('/topic/greetings/' + resp.allianceUuid, function (stompResponse) {
                                self.stompSubject.next(JSON.parse(stompResponse.body));
                            });
                        });
                    });
                };
                StompService.prototype.connect = function () {
                    if (StompService.stompClient == null || StompService.stompClient.OPENED) {
                        this.connectInit();
                    }
                };
                StompService.prototype.send = function () {
                    StompService.stompClient.send("/app/hello/" + this.alliance.allianceUuid, {}, JSON.stringify({ 'message': 'askHelp' }));
                };
                StompService.prototype.getObservable = function () {
                    return this.stompSubject.asObservable();
                };
                StompService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [help_service_1.HelpService])
                ], StompService);
                return StompService;
            }());
            exports_1("StompService", StompService);
        }
    }
});
//# sourceMappingURL=stomp.service.js.map