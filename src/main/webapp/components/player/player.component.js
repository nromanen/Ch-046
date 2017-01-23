/**
 * Created by okunetc on 13.01.2017.
 */
System.register(["@angular/core", "../services/player.service", "@angular/router", "../village/village", "../services/newVillageArmiesService", "../services/villageService", "../army/army", "../UnitType/unitType"], function (exports_1, context_1) {
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
    var core_1, player_service_1, router_1, village_1, newVillageArmiesService_1, villageService_1, army_1, unitType_1, PlayerComponent;
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
            function (village_1_1) {
                village_1 = village_1_1;
            },
            function (newVillageArmiesService_1_1) {
                newVillageArmiesService_1 = newVillageArmiesService_1_1;
            },
            function (villageService_1_1) {
                villageService_1 = villageService_1_1;
            },
            function (army_1_1) {
                army_1 = army_1_1;
            },
            function (unitType_1_1) {
                unitType_1 = unitType_1_1;
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
                }
                PlayerComponent.prototype.f = function () {
                    console.log('clicked');
                    console.log(this.player);
                    var village = new village_1.Village();
                    var armies = [];
                    village.armies = armies;
                    alert(JSON.stringify(village));
                    village.armies.push(new army_1.Army(15, unitType_1.UnitType.Legionnaire, true));
                    this.player.villages.push(village);
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
                return PlayerComponent;
            }());
            PlayerComponent = __decorate([
                core_1.Component({
                    selector: 'player',
                    template: "\n<player-head></player-head>\n<player-list *ngIf=\"player\" [player]=\"player\"></player-list>\n<button  (click)=\"f()\" >add</button>\n<add-vill-form></add-vill-form>\n"
                }),
                __metadata("design:paramtypes", [newVillageArmiesService_1.CurrVillageArmiesService, player_service_1.PlayerService, router_1.ActivatedRoute, villageService_1.VillageService])
            ], PlayerComponent);
            exports_1("PlayerComponent", PlayerComponent);
        }
    };
});
//# sourceMappingURL=player.component.js.map