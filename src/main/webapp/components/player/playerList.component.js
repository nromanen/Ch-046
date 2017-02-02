System.register(["./player", "@angular/core", "../village/village", "../services/newVillageArmiesService", "../UnitType/unitType", "../services/villageService"], function (exports_1, context_1) {
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
    var player_1, core_1, village_1, newVillageArmiesService_1, unitType_1, villageService_1, PlayerList;
    return {
        setters: [
            function (player_1_1) {
                player_1 = player_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (village_1_1) {
                village_1 = village_1_1;
            },
            function (newVillageArmiesService_1_1) {
                newVillageArmiesService_1 = newVillageArmiesService_1_1;
            },
            function (unitType_1_1) {
                unitType_1 = unitType_1_1;
            },
            function (villageService_1_1) {
                villageService_1 = villageService_1_1;
            }
        ],
        execute: function () {
            PlayerList = (function () {
                function PlayerList(currVillageService, villageService) {
                    this.currVillageService = currVillageService;
                    this.villageService = villageService;
                    this.unitValues = [];
                    this.selectedVillage = new village_1.Village();
                    console.log(this.unitValues);
                }
                PlayerList.prototype.ngOnInit = function () {
                    console.log(unitType_1.UnitType.Legionnaire);
                    this.villageService.villages = this.player.villages;
                };
                PlayerList.prototype.wasEdited = function (village) {
                    this.currVillageService.village = village;
                };
                PlayerList.prototype.changeSelectedVillage = function (village) {
                    this.selectedVillage = village;
                    console.log(this.selectedVillage);
                };
                return PlayerList;
            }());
            __decorate([
                core_1.Input('player'),
                __metadata("design:type", player_1.Player)
            ], PlayerList.prototype, "player", void 0);
            PlayerList = __decorate([
                core_1.Component({
                    selector: 'player-list',
                    templateUrl: "components/player/playerList.html"
                }),
                __metadata("design:paramtypes", [newVillageArmiesService_1.CurrVillageArmiesService, villageService_1.VillageService])
            ], PlayerList);
            exports_1("PlayerList", PlayerList);
        }
    };
});
//# sourceMappingURL=playerList.component.js.map