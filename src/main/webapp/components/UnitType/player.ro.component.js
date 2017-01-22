System.register(["@angular/core", "./unitType", "../village/village"], function (exports_1, context_1) {
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
    var core_1, unitType_1, village_1, PlayerRo;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (unitType_1_1) {
                unitType_1 = unitType_1_1;
            },
            function (village_1_1) {
                village_1 = village_1_1;
            }
        ],
        execute: function () {
            PlayerRo = (function () {
                function PlayerRo() {
                    this.selectedVillageChanged = new core_1.EventEmitter();
                    this.unitValues = [];
                    this.getStringUnitTypeValues();
                    // console.log('in utr');
                    // console.log(this.unitValues);
                }
                PlayerRo.prototype.getStringUnitTypeValues = function () {
                    for (var m in unitType_1.UnitType) {
                        if (typeof unitType_1.UnitType[m] === 'number') {
                            this.unitValues.push(m);
                        }
                    }
                };
                PlayerRo.prototype.cellClick = function (village) {
                    this.selectedVillageChanged.emit(village);
                };
                return PlayerRo;
            }());
            __decorate([
                core_1.Input(),
                __metadata("design:type", village_1.Village)
            ], PlayerRo.prototype, "v", void 0);
            __decorate([
                core_1.Input(),
                __metadata("design:type", Number)
            ], PlayerRo.prototype, "type", void 0);
            PlayerRo = __decorate([
                core_1.Component({
                    selector: '[player-ro]',
                    outputs: ['selectedVillageChanged'],
                    template: "\n            <td>{{v.name}}</td>\n            <td>{{v.population}}</td>\n            <td>{{v.xCoord}}</td>\n            <td>{{v.yCoord}}</td>\n            <td>{{v.isCapital}}</td>\n            <td>{{v.wall}}</td>\n            \n            <td *ngFor=\"let tp of types\"><army-cell [type]=\"tp\" [village]=\"v\" (cellClicked)=\"cellClick($event)\"></army-cell></td>\n            \n"
                }),
                __metadata("design:paramtypes", [])
            ], PlayerRo);
            exports_1("PlayerRo", PlayerRo);
        }
    };
});
//# sourceMappingURL=unittypes.row.component.js.map