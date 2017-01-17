System.register(["./player", "@angular/core"], function (exports_1, context_1) {
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
    var player_1, core_1, PlayerList;
    return {
        setters: [
            function (player_1_1) {
                player_1 = player_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            }
        ],
        execute: function () {
            PlayerList = (function () {
                function PlayerList() {
                }
                PlayerList.prototype.ngOnInit = function () {
                    console.log(this.player);
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
                    template: "\n<table>\n<thead >\n    <tr>\n        <th>Village</th>\n        <th>Population</th>\n        <th>X</th>\n        <th>Y</th>\n        <th>Capital?</th>\n        <th>Wall level</th>\n        <th>Armies</th>\n        <th>Quantity</th>\n    </tr>\n    </thead>\n    \n     <tbody *ngFor=\"let v of player.villages\">\n    <tr >\n        <td rowspan=\"2\">{{v.name}}</td>\n        <td rowspan=\"2\">{{v.population}}</td>\n        <td rowspan=\"2\">{{v.xCoord}}</td>\n        <td rowspan=\"2\">{{v.yCoord}}</td>\n        <td rowspan=\"2\">{{v.isCapital}}</td>\n        <td rowspan=\"2\">{{v.wall}}</td>\n        <td>type1</td>\n        <td>63</td>\n    </tr>\n\n    <tr>\n        <td>type2</td>\n        <td>23</td>\n    </tr>\n    </tbody>\n</table>\n"
                }),
                __metadata("design:paramtypes", [])
            ], PlayerList);
            exports_1("PlayerList", PlayerList);
        }
    };
});
//# sourceMappingURL=playerList.component.js.map