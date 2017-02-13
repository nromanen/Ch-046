System.register(["@angular/core", "../services/helpNotification/stomp.service", "../services/helpNotification/help.service"], function (exports_1, context_1) {
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
    var core_1, stomp_service_1, help_service_1, PlayerHeader;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (stomp_service_1_1) {
                stomp_service_1 = stomp_service_1_1;
            },
            function (help_service_1_1) {
                help_service_1 = help_service_1_1;
            }
        ],
        execute: function () {
            PlayerHeader = (function () {
                function PlayerHeader(stompService, helpService) {
                    this.stompService = stompService;
                    this.helpService = helpService;
                    this.isLeader = false;
                    this.showNotif = false;
                }
                PlayerHeader.prototype.ngOnInit = function () {
                    this.websocketConnect();
                    this.getActiveHelp();
                };
                PlayerHeader.prototype.getActiveHelp = function () {
                    var _this = this;
                    this.helpService.getActiveHelp()
                        .subscribe(function (resp) {
                        _this.attacks = resp;
                        console.log(_this.attacks);
                        _this.numOfAttacks = _this.attacks != null ? _this.attacks.length : null;
                    });
                };
                // getAlliance(){
                //
                //
                //     this.helpService.getAlliance()
                //         .subscribe(
                //             resp => {
                //                 console.log("APP_COMPONENT_SUBSCRIBE");
                //                 PlayerHeader.alliance = resp;
                //                 console.log(PlayerHeader.alliance);
                //             }
                //
                //         )
                //
                // }
                PlayerHeader.prototype.websocketConnect = function () {
                    var _this = this;
                    console.log("Connection start");
                    this.stompService.connect();
                    this.stompService.getObservable().subscribe(function (payload) {
                        console.log("Show notification");
                        _this.serverResponse = payload.outputField;
                        _this.showNotification();
                        _this.getActiveHelp();
                    });
                };
                PlayerHeader.prototype.showNotification = function () {
                    this.showNotif = true;
                };
                PlayerHeader.prototype.close = function () {
                    this.showNotif = false;
                };
                return PlayerHeader;
            }());
            __decorate([
                core_1.Input(),
                __metadata("design:type", Boolean)
            ], PlayerHeader.prototype, "isLeader", void 0);
            PlayerHeader = __decorate([
                core_1.Component({
                    selector: 'player-head',
                    templateUrl: 'components/header/playerHeader.html',
                    styleUrls: ['components/header/playerHeader.css']
                }),
                __metadata("design:paramtypes", [stomp_service_1.StompService, help_service_1.HelpService])
            ], PlayerHeader);
            exports_1("PlayerHeader", PlayerHeader);
        }
    };
});
//# sourceMappingURL=player-header.component.js.map