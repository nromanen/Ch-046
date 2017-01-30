System.register(["@angular/core", "../services/helpNotification/stomp.service"], function (exports_1, context_1) {
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
    var core_1, stomp_service_1, PlayerHeader;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (stomp_service_1_1) {
                stomp_service_1 = stomp_service_1_1;
            }
        ],
        execute: function () {
            PlayerHeader = (function () {
                function PlayerHeader(stompService) {
                    this.stompService = stompService;
                    this.showNotif = false;
                }
                PlayerHeader.prototype.ngOnInit = function () {
                    var _this = this;
                    this.stompService.connect();
                    this.stompService.getObservable().subscribe(function (payload) {
                        _this.serverResponse = payload.outputField;
                        _this.showNotification();
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
            PlayerHeader = __decorate([
                core_1.Component({
                    selector: 'player-head',
                    templateUrl: 'components/header/playerHeader.html',
                    styleUrls: ['components/header/playerHeader.css']
                }),
                __metadata("design:paramtypes", [stomp_service_1.StompService])
            ], PlayerHeader);
            exports_1("PlayerHeader", PlayerHeader);
        }
    };
});
//# sourceMappingURL=player-header.component.js.map