/**
 * Created by okunetc on 13.01.2017.
 */
System.register(["@angular/core", "../services/player.service", "@angular/router", "../services/newVillageArmiesService", "../services/villageService"], function (exports_1, context_1) {
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
    var core_1, player_service_1, router_1, newVillageArmiesService_1, villageService_1, PlayerComponent;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (player_service_1_1) {
                player_service_1 = player_service_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (newVillageArmiesService_1_1) {
                newVillageArmiesService_1 = newVillageArmiesService_1_1;
            },
            function (villageService_1_1) {
                villageService_1 = villageService_1_1;
            }
        ],
        execute: function () {/**
             * Created by okunetc on 13.01.2017.
             */
            PlayerComponent = (function () {
                function PlayerComponent(currPlayerService, playerService, route, villageService) {
                    var _this = this;
                    this.currPlayerService = currPlayerService;
                    this.playerService = playerService;
                    this.route = route;
                    this.villageService = villageService;
                    this.route.params.subscribe(function (param) {
                        _this.id = param['id'];
                        _this.playerService.url = _this.playerService.url + _this.id;
                    });
                    this.showAddVillageForm = false;
                }
                PlayerComponent.prototype.hideAddForm = function () {
                    this.showAddVillageForm = false;
                };
                PlayerComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    this.playerService.getById()
                        .subscribe(function (player) {
                        _this.player = player;
                        console.log(_this.player);
                        // alert(JSON.stringify(this.player));
                    });
                };
                PlayerComponent.prototype.showAddForm = function () {
                    console.log(this.showAddVillageForm);
                    this.showAddVillageForm = true;
                };
                return PlayerComponent;
            }());
            PlayerComponent = __decorate([
                core_1.Component({
                    selector: 'player',
                    template: "\n<player-head></player-head>\n<player-list *ngIf=\"player\" [player]=\"player\"></player-list>\n<div class=\"row\">\n<div class=\"col offset-s5\">\n<button  (click)=\"showAddForm()\" class=\"btn waves-effect waves-light\">add</button>\n</div>\n<add-vill-form [player]=\"player\" *ngIf=\"showAddVillageForm\" (wasSubmitted)=\"hideAddForm($event)\" ></add-vill-form>\n"
                }),
                __metadata("design:paramtypes", [newVillageArmiesService_1.CurrVillageArmiesService, player_service_1.PlayerService, router_1.ActivatedRoute, villageService_1.VillageService])
            ], PlayerComponent);
            exports_1("PlayerComponent", PlayerComponent);
        }
    };
});
//# sourceMappingURL=player.component.js.map