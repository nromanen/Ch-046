/**
 * Created by okunetc on 13.01.2017.
 */
System.register(["@angular/core", "../services/player.service"], function (exports_1, context_1) {
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
    var core_1, player_service_1, PlayerComponent;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (player_service_1_1) {
                player_service_1 = player_service_1_1;
            }
        ],
        execute: function () {/**
             * Created by okunetc on 13.01.2017.
             */
            PlayerComponent = (function () {
                function PlayerComponent(playerService) {
                    this.playerService = playerService;
                }
                PlayerComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    this.playerService.getById()
                        .subscribe(function (player) {
                        console.info("PlayerComponent ngOnInit() is working. Player: " + JSON.stringify(player));
                        _this.player = player;
                        console.log(_this.player.alliance);
                    });
                };
                PlayerComponent.prototype.hideAddForm = function () {
                    this.showAddVillageForm = false;
                };
                PlayerComponent.prototype.showAddForm = function () {
                    this.showAddVillageForm = true;
                };
                PlayerComponent.prototype.closeDialog = function () {
                    this.errorMessage = null;
                    this.successMessage = null;
                };
                PlayerComponent.prototype.showSuccessMessage = function (event) {
                    this.errorMessage = null;
                    this.successMessage = event;
                };
                PlayerComponent.prototype.showErrorMessage = function (event) {
                    this.successMessage = null;
                    this.errorMessage = event;
                };
                return PlayerComponent;
            }());
            PlayerComponent = __decorate([
                core_1.Component({
                    selector: 'player',
                    template: "\n        <player-head *ngIf=\"player\" [isLeader]=\"player.isLeader\"></player-head>\n        \n        <div class=\"row container\">\n    <div class=\"col s12 center-align\">\n        <div class=\"col s6 left-align\">\n            <h4 *ngIf=\"player\">login:{{ player.login }}</h4>\n        </div>\n        <div class=\"col s6 right-align\">\n            <h4 *ngIf=\"player\">alliance: {{ player.alliance.name }}</h4>\n        </div>\n    </div>\n</div>\n                 <div class=\"row\">\n<div *ngIf=\"successMessage!=null||errorMessage!=null\" class=\"col s4 offset-s4 \">\n    <div  [ngClass]=\"{'card':true, 'green':successMessage!=null, 'red':errorMessage!=null, 'lighten-5':true}\">\n        <div [ngClass]=\"{'card-content':true , 'green-text':successMessage!=null,'red-text':errorMessage!=null }\">\n            <p>{{successMessage!=null?successMessage:errorMessage}} <span (click)=\"closeDialog()\" class=\"right\">x</span></p>\n        </div>\n    </div>\n</div>\n</div>\n        <player-list *ngIf=\"player\" [player]=\"player\"></player-list>\n        <div class=\"row\">\n            <div class=\"col s4 offset-s6\" >\n                <button (click)=\"showAddForm()\" class=\"btn waves-effect waves-light\">Add</button>\n            </div>\n         \n            <add-vill-form [player]=\"player\" *ngIf=\"showAddVillageForm\" (wasSubmitted)=\"hideAddForm($event)\"\n            (successMessage)=\"showSuccessMessage($event)\" (errorMessage)=\"showErrorMessage($event)\"></add-vill-form>\n      \n\n\n"
                }),
                __metadata("design:paramtypes", [player_service_1.PlayerService])
            ], PlayerComponent);
            exports_1("PlayerComponent", PlayerComponent);
        }
    };
});
//# sourceMappingURL=player.component.js.map