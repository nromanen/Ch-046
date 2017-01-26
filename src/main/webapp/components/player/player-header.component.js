System.register(["@angular/core", "../help/stomp.service"], function (exports_1, context_1) {
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
                    // EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
                    // ALLIANCE_NAME = /^[a-z]{3,9}$/;
                    this.VILLAGE = /^[A-Za-z1-9.]{3,9}$/;
                    this.DATE = /^[A-Za-z1-9. :]{3,20}$/;
                    this.ENEMY = "Enter correct email, please!";
                    this.NAME_ERROR = "Enter from 3 to 10 letters";
                    this.LOGIN_ERROR = "Enter from 3 to 10 letters";
                    this.showNotif = false;
                }
                PlayerHeader.prototype.ngOnInit = function () {
                    var _this = this;
                    this.stompService.connect('ws://localhost:8080/travian/stompTest');
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
                    template: "<nav>\n<div class=\"nav-wrapper\">\n<a href=\"#!\" class=\"brand-logo\">Logo</a>\n<ul class=\"right hide-on-med-and-down\">\n<li><a href=\"#\" >Add village</a></li>\n<li><a href=\"#\">All villages</a></li>\n<li><a routerLink=\"/help\" routerLinkActive=\"active\">Ask help</a></li>\n<li><a routerLink=\"/allHelps\" routerLinkActive=\"active\">Attacks</a></li>\n<li><a href=\"logout\" >Log out</a></li>\n</ul>\n</div>\n</nav>\n<div id=\"note\" *ngIf=\"showNotif\" routerLink=\"/allHelps\" routerLinkActive=\"active\" style=\"\n\n    position: absolute;\n    width: 30%;\n    height: 60px;\n    z-index: 101;\n    top: 70px;\n    font-size: 18px;\n\n    right: 10px;\n    color: #fff;\n    background: #fd5c68;\n    line-height: 2.5;\n    -webkit-box-shadow: 0 0 5px black;\n    -moz-box-shadow:    0 0 5px black;\n    box-shadow:         0 0 5px black;\n    padding: 10px;\n\">\n    One of alliance member needs help!\n    <div  class=\"right\" style=\"cursor: pointer;\" (click) = \"close()\">\n    x\n    </div>\n</div>\n"
                }),
                __metadata("design:paramtypes", [stomp_service_1.StompService])
            ], PlayerHeader);
            exports_1("PlayerHeader", PlayerHeader);
        }
    };
});
//# sourceMappingURL=player-header.component.js.map