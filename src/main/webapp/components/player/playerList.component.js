System.register(["./player", "@angular/core", "../services/currentVillage.service", "../UnitType/unitType"], function (exports_1, context_1) {
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
    var player_1, core_1, currentVillage_service_1, unitType_1, PlayerList;
    return {
        setters: [
            function (player_1_1) {
                player_1 = player_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (currentVillage_service_1_1) {
                currentVillage_service_1 = currentVillage_service_1_1;
            },
            function (unitType_1_1) {
                unitType_1 = unitType_1_1;
            }
        ],
        execute: function () {
            PlayerList = (function () {
                function PlayerList(currVillageService) {
                    this.currVillageService = currVillageService;
                    this.unitValues = [];
                    this.selectedVillage = null;
                    for (var m in unitType_1.UnitType) {
                        if (typeof unitType_1.UnitType[m] === 'number') {
                            this.unitValues.push(m);
                        }
                    }
                    console.log(this.unitValues);
                }
                PlayerList.prototype.ngOnInit = function () {
                    console.log(unitType_1.UnitType.Legionnaire);
                    console.log(this.player.villages[0].armies[0].type);
                    console.log(unitType_1.UnitType[unitType_1.UnitType.Legionnaire] == this.player.villages[0].armies[0].type.toString());
                };
                PlayerList.prototype.wasEdited = function (village) {
                    console.log('was edited');
                    console.log(village);
                    this.currVillageService.village = village;
                };
                PlayerList.prototype.g = function () {
                    this.player.villages[0].name = "vill6";
                };
                PlayerList.prototype.showForm = function () {
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
                    template: "<div class=\"row \">\n    <table>\n        <thead>\n        <tr>\n            <th>Village</th>\n            <th>Population</th>\n            <th>X</th>\n            <th>Y</th>\n            <th>Capital?</th>\n            <th>Wall level</th>\n            <td><img src=\"images/Gauls/GalFal.gif\"></td>\n            <td><img src=\"images/Gauls/GalSwordsman.gif\"></td>\n            <td><img src=\"images/Gauls/GalPathFinder.gif\"></td>\n            <td><img src=\"images/Gauls/GalTewtThunder.gif\"></td>\n            <td><img src=\"images/Gauls/GalDruid.gif\"></td>\n            <td><img src=\"images/Gauls/Edui.gif\"></td>\n            <td><img src=\"images/Gauls/GalRam.gif\"></td>\n            <td><img src=\"images/Gauls/GalCatapult.gif\"></td>\n            <td><img src=\"images/Gauls/GaLeader.gif\"></td>\n            <td><img src=\"images/Germans/Clubswinger.gif\"></td>\n            <td><img src=\"images/Germans/Spearman.gif\"></td>\n            <td><img src=\"images/Germans/Toporshchik.gif\"></td>\n            <td><img src=\"images/Germans/Skaut.gif\"></td>\n            <td><img src=\"images/Germans/Paladin.gif\"></td>\n            <td><img src=\"images/Germans/Tevtonskaya-konnitsa.gif\"></td>\n            <td><img src=\"images/Germans/Taran-ger.gif\"></td>\n            <td><img src=\"images/Germans/Katapulta-ger.gif\"></td>\n            <td><img src=\"images/Germans/Leader.gif\"></td>\n            <td><img src=\"images/Rome/Legioner.gif\"></td>\n            <td><img src=\"images/Rome/Praetorian.gif\"></td>\n            <td><img src=\"images/Rome/Imperianets.gif\"></td>\n            <td><img src=\"images/Rome/Konnyy-razvedchik.gif\"></td>\n            <td><img src=\"images/Rome/Konnitsa-imperatora.gif\"></td>\n            <td><img src=\"images/Rome/Konnitsa-Tsezarya.gif\"></td>\n            <td><img src=\"images/Rome/Taran-rim.gif\"></td>\n            <td><img src=\"images/Rome/Ognennaya-katapulta.gif\"></td>\n            <td><img src=\"images/Rome/Senator.gif\"></td>\n            <th></th>\n        </tr>\n        </thead>\n\n        <tbody>\n        <tr *ngFor=\"let v of player.villages\">\n            <td>{{v.name}}</td>\n            <td>{{v.population}}</td>\n            <td>{{v.xCoord}}</td>\n            <td>{{v.yCoord}}</td>\n            <td>{{v.isCapital}}</td>\n            <td>{{v.wall}}</td>\n           \n            <td *ngFor=\"let u of unitValues\">\n                <army-cell [type]=\"u\" [village]=\"v\" ></army-cell>\n            </td>\n            <!--<td><player-row [v]=\"v\" (editClick)=\"wasEdited($event)\"></player-row></td>-->\n            \n            <tr my-tr>\n            </tr>\n        </tbody>\n    </table>\n    <button (click)=\"g()\">degfdfg</button>\n</div>\n"
                }),
                __metadata("design:paramtypes", [currentVillage_service_1.CurrVillageService])
            ], PlayerList);
            exports_1("PlayerList", PlayerList);
        }
    };
});
//# sourceMappingURL=playerList.component.js.map