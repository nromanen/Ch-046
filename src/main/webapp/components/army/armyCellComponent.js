System.register(["@angular/core", "../village/village", "./army", "../services/newVillageArmiesService", "../UnitType/unitType", "@angular/forms"], function (exports_1, context_1) {
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
    var core_1, village_1, army_1, newVillageArmiesService_1, unitType_1, forms_1, ArmyCellComponent;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (village_1_1) {
                village_1 = village_1_1;
            },
            function (army_1_1) {
                army_1 = army_1_1;
            },
            function (newVillageArmiesService_1_1) {
                newVillageArmiesService_1 = newVillageArmiesService_1_1;
            },
            function (unitType_1_1) {
                unitType_1 = unitType_1_1;
            },
            function (forms_1_1) {
                forms_1 = forms_1_1;
            }
        ],
        execute: function () {
            ArmyCellComponent = (function () {
                function ArmyCellComponent(currVillageArmiesService, _fBuilder) {
                    this.currVillageArmiesService = currVillageArmiesService;
                    this._fBuilder = _fBuilder;
                    this.isInput = true;
                    this.cellClicked = new core_1.EventEmitter();
                    this.cancelEdit = new core_1.EventEmitter();
                }
                ArmyCellComponent.prototype.ngDoCheck = function () {
                };
                ArmyCellComponent.prototype.ngOnInit = function () {
                    // this.armiesArrayControl[this.index]=this.initArmies(this.type);
                    var _this = this;
                    this.army = new army_1.Army(-1, unitType_1.UnitType[this.type], true);
                    this.army.type = unitType_1.UnitType[this.type];
                    this.newArmy = new army_1.Army(this.army.count, this.army.type, this.army.ownUnit);
                    this.newArmy.type = this.army.type;
                    this.newArmy.uuid = this.army.uuid;
                    this.village.armies.forEach(function (army) {
                        if (_this.type == army.type.toString() || unitType_1.UnitType[army.type] == _this.type) {
                            _this.army = army;
                            _this.newArmy = new army_1.Army(_this.army.count, _this.army.type, _this.army.ownUnit);
                            _this.newArmy.uuid = _this.army.uuid;
                        }
                    });
                };
                ArmyCellComponent.prototype.ngOnChanges = function (changes) {
                    var _this = this;
                    if (changes['village']) {
                        this.army = new army_1.Army(-1, unitType_1.UnitType[this.type], true);
                        this.army.type = unitType_1.UnitType[this.type];
                        this.newArmy = new army_1.Army(this.army.count, this.army.type, this.army.ownUnit);
                        this.newArmy.type = this.army.type;
                        this.newArmy.uuid = this.army.uuid;
                        this.village.armies.forEach(function (army) {
                            if (_this.type == army.type.toString() || unitType_1.UnitType[army.type] == _this.type) {
                                _this.army = army;
                                _this.newArmy = new army_1.Army(_this.army.count, _this.army.type, _this.army.ownUnit);
                                _this.newArmy.uuid = _this.army.uuid;
                            }
                        });
                    }
                    if (changes['isInput'] != null)
                        // console.log(changes['armiesArrayControl']);
                        // this.armiesArrayControl[this.index] = this.initArmies(this.type);
                        if (changes['isInput'].currentValue === true && this.army.count != -1) {
                            this.currVillageArmiesService.armies.push(this.newArmy);
                        }
                };
                ArmyCellComponent.prototype.hide = function () {
                    this.isInput = true;
                    this.cellClicked.emit(this.village);
                };
                ArmyCellComponent.prototype.becomeDiv = function () {
                    this.cellClicked.emit(null);
                };
                ArmyCellComponent.prototype.addOrEdit = function (event) {
                    this.newArmy.count = event;
                    if (this.army.count == -1) {
                        if (this.currVillageArmiesService.armies.indexOf(this.newArmy) == -1 && this.newArmy.count > 0)
                            this.currVillageArmiesService.armies.push(this.newArmy);
                    }
                    else
                        this.newArmy.count = event;
                };
                ArmyCellComponent.prototype.cancelEditing = function (event) {
                    if (event === 27)
                        this.cancelEdit.emit(null);
                };
                return ArmyCellComponent;
            }());
            __decorate([
                core_1.Input(),
                __metadata("design:type", String)
            ], ArmyCellComponent.prototype, "type", void 0);
            __decorate([
                core_1.Input(),
                __metadata("design:type", village_1.Village)
            ], ArmyCellComponent.prototype, "village", void 0);
            __decorate([
                core_1.Input(),
                __metadata("design:type", forms_1.FormGroup)
            ], ArmyCellComponent.prototype, "group", void 0);
            __decorate([
                core_1.Input(),
                __metadata("design:type", Boolean)
            ], ArmyCellComponent.prototype, "isInput", void 0);
            __decorate([
                core_1.Input(),
                __metadata("design:type", Boolean)
            ], ArmyCellComponent.prototype, "ifSave", void 0);
            __decorate([
                core_1.Input(),
                __metadata("design:type", forms_1.FormArray)
            ], ArmyCellComponent.prototype, "armiesArrayControl", void 0);
            __decorate([
                core_1.Input(),
                __metadata("design:type", Object)
            ], ArmyCellComponent.prototype, "index", void 0);
            __decorate([
                core_1.Output(),
                __metadata("design:type", core_1.EventEmitter)
            ], ArmyCellComponent.prototype, "cancelEdit", void 0);
            ArmyCellComponent = __decorate([
                core_1.Component({
                    selector: 'army-cell',
                    outputs: ['cellClicked'],
                    styleUrls: ['components/army/army.css'],
                    template: "\n          <div class=\"cell\" title=\"{{this.type}}\" (dblclick)=\"hide()\" *ngIf=\"!isInput\" (click)=\"becomeDiv()\">{{this.army.count==0?\" \":this.army.count}}</div>\n     <div class=\"input-field\"  *ngIf=\"isInput\" [formGroup]=\"group\">\n     <input class=\"validate\"  type=\"text\"  style=\" width: 20px;height: 22px\" [ngModel]=\"this.army.count!=0?this.army.count:''\" (keyup)=\"cancelEditing($event.keyCode)\"\n     formControlName=\"count\" (ngModelChange)=\"addOrEdit($event)\">\n     </div>\n"
                }),
                __metadata("design:paramtypes", [newVillageArmiesService_1.CurrVillageArmiesService, forms_1.FormBuilder])
            ], ArmyCellComponent);
            exports_1("ArmyCellComponent", ArmyCellComponent);
        }
    };
});
//# sourceMappingURL=armyCellComponent.js.map