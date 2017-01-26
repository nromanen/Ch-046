System.register(["@angular/core", "../UnitType/unitType", "./village", "../services/villageService", "../services/newVillageArmiesService"], function (exports_1, context_1) {
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
    var core_1, unitType_1, village_1, villageService_1, newVillageArmiesService_1, VillageRow;
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
            },
            function (villageService_1_1) {
                villageService_1 = villageService_1_1;
            },
            function (newVillageArmiesService_1_1) {
                newVillageArmiesService_1 = newVillageArmiesService_1_1;
            }
        ],
        execute: function () {
            VillageRow = (function () {
                function VillageRow(villageService, currVillageArmiesService) {
                    this.villageService = villageService;
                    this.currVillageArmiesService = currVillageArmiesService;
                    this.selectedVillageChanged = new core_1.EventEmitter();
                    this.unitValues = [];
                    this.ifSaveChanges = false;
                    console.log('inside constructor');
                    console.log(this.ifSaveChanges);
                }
                VillageRow.prototype.ngOnInit = function () {
                    this.newVillage = new village_1.Village();
                    this.newVillage.name = this.village.name;
                    this.newVillage.uuid = this.village.uuid;
                    this.newVillage.armies = this.village.armies;
                    this.newVillage.isCapital = this.village.isCapital;
                    this.newVillage.population = this.village.population;
                    this.newVillage.xCoord = this.village.xCoord;
                    this.newVillage.yCoord = this.village.yCoord;
                    this.newVillage.wall = this.village.wall;
                    this.getStringUnitTypeValues();
                };
                VillageRow.prototype.getStringUnitTypeValues = function () {
                    for (var m in unitType_1.UnitType) {
                        if (typeof unitType_1.UnitType[m] === 'number') {
                            this.unitValues.push(m);
                        }
                    }
                };
                VillageRow.prototype.submitEdit = function () {
                    this.villageService.update(this.village);
                };
                VillageRow.prototype.cellClick = function (village) {
                    this.selectedVillageChanged.emit(village);
                };
                VillageRow.prototype.changeVillage = function () {
                    this.ifSaveChanges = true;
                    console.log('insideChange');
                    console.log(this.village.armies);
                    this.village.name = this.newVillage.name;
                    this.village.armies = this.currVillageArmiesService.armies;
                    this.village.xCoord = this.newVillage.xCoord;
                    this.village.yCoord = this.newVillage.yCoord;
                    this.village.isCapital = this.newVillage.isCapital;
                    this.village.population = this.newVillage.population;
                    this.village.wall = this.newVillage.wall;
                    this.submitEdit();
                    this.isForm = false;
                    this.ifSaveChanges = false;
                };
                VillageRow.prototype.showEdit = function () {
                    this.selectedVillageChanged.emit(null);
                    this.selectedVillageChanged.emit(this.village);
                    this.ifSaveChanges = false;
                    this.currVillageArmiesService.armies.length = 0;
                };
                return VillageRow;
            }());
            __decorate([
                core_1.Input(),
                __metadata("design:type", village_1.Village)
            ], VillageRow.prototype, "village", void 0);
            __decorate([
                core_1.Input(),
                __metadata("design:type", Boolean)
            ], VillageRow.prototype, "isForm", void 0);
            VillageRow = __decorate([
                core_1.Component({
                    selector: '[player-ro]',
                    outputs: ['selectedVillageChanged'],
                    template: "\n<td>\n    <div *ngIf=\"!isForm\">{{village.name}}</div>\n    <input *ngIf=\"isForm\" (ngModelChange)=\"this.newVillage.name=$event\" type=\"text\" [ngModel]=\"this.village.name\"  name=\"name\"></td>\n    \n<td>\n    <div *ngIf=\"!isForm\">{{village.population}}</div>\n    <input *ngIf=\"isForm\" (ngModelChange)=\"this.newVillage.population=$event\" type=\"text\" [ngModel]=\"this.village.population\" name=\"population\">\n</td>\n<td>\n    <div *ngIf=\"!isForm\" >{{this.village.xCoord}}</div>\n    <input (ngModelChange)=\"this.newVillage.xCoord=$event\" *ngIf=\"isForm\" type=\"text\" [ngModel]=\"this.village.xCoord\" name=\"x\">\n</td>\n<td >\n    <div *ngIf=\"!isForm\">{{village.yCoord}}</div>\n    <input *ngIf=\"isForm\" type=\"text\" [ngModel]=\"this.village.yCoord\" name=\"y\" (ngModelChange)=\"this.newVillage.yCoord=$event\">\n</td>\n<td >\n    <div *ngIf=\"!isForm\">{{village.isCapital}}</div>\n    <input *ngIf=\"isForm\" type=\"checkbox\" (ngModelChange)=\"this.newVillage.isCapital=$event\" [ngModel]=\"this.village.isCapital\" name=\"isCapital\" id=\"isCapital\" class=\"filled-in\">\n    <label *ngIf=\"isForm\" for=\"isCapital\"></label>\n</td>\n\n<td >\n<div *ngIf=\"!isForm\">{{village.wall}}</div>\n<input *ngIf=\"isForm\" type=\"text\" [ngModel]=\"this.village.wall\" (ngModelChange)=\"this.newVillage.wall=$event\" name=\"wall\">\n</td>\n\n<td *ngFor=\"let tp of unitValues\">\n    <army-cell [type]=\"tp\" [village]=\"village\" \n    (cellClicked)=\"cellClick($event)\" [isInput]=\"isForm\" [ifSave]=\"ifSaveChanges\"></army-cell>\n</td>\n<td>\n    <button (click)=\"!isForm?showEdit():changeVillage()\" class=\"btn waves-effect waves-light col offset-s1 \" type=\"submit\" name=\"action\"\n            style=\"margin-top: 5px;\"\n    >{{!isForm?\"Edit\":\"Save\"}}\n    </button>\n    \n</td>\n"
                }),
                __metadata("design:paramtypes", [villageService_1.VillageService, newVillageArmiesService_1.CurrVillageArmiesService])
            ], VillageRow);
            exports_1("VillageRow", VillageRow);
        }
    };
});
//# sourceMappingURL=villageRow.component.js.map