System.register(["@angular/core", "../village/village", "./army", "../services/newVillageArmiesService"], function (exports_1, context_1) {
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
    var core_1, village_1, army_1, newVillageArmiesService_1, ArmyCellComponent;
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
            }
        ],
        execute: function () {
            ArmyCellComponent = (function () {
                function ArmyCellComponent(currVillageArmiesService) {
                    this.currVillageArmiesService = currVillageArmiesService;
                    this.isInput = true;
                    this.cellClicked = new core_1.EventEmitter();
                    // this.newArmy=new Army();
                }
                ArmyCellComponent.prototype.ngOnChanges = function (changes) {
                    if (this.ifSave && this.army != null) {
                        this.army.count = this.newArmy.count;
                        console.log(this.village.armies);
                    }
                    if (changes['isInput'] != null)
                        if (changes['isInput'].currentValue === true && this.army != null) {
                            console.log('isInput');
                            this.currVillageArmiesService.armies.push(this.newArmy);
                            console.log(this.newArmy);
                            console.log(this.currVillageArmiesService.armies);
                            alert(JSON.stringify(this.army));
                            alert(JSON.stringify(this.newArmy));
                        }
                };
                ArmyCellComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    this.village.armies.forEach(function (army) {
                        if (_this.type == army.type.toString()) {
                            _this.army = army;
                            _this.newArmy = new army_1.Army(_this.army.count, _this.army.type, _this.army.ownUnit);
                            _this.newArmy.uuid = _this.army.uuid;
                        }
                    });
                    // console.log(this.currVillageArmiesService.armies);
                };
                ArmyCellComponent.prototype.hide = function () {
                    this.isInput = true;
                    this.cellClicked.emit(this.village);
                };
                ArmyCellComponent.prototype.becomeDiv = function () {
                    this.cellClicked.emit(null);
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
                __metadata("design:type", Boolean)
            ], ArmyCellComponent.prototype, "isInput", void 0);
            __decorate([
                core_1.Input(),
                __metadata("design:type", Boolean)
            ], ArmyCellComponent.prototype, "ifSave", void 0);
            ArmyCellComponent = __decorate([
                core_1.Component({
                    selector: 'army-cell',
                    outputs: ['cellClicked'],
                    template: "\n     <div title=\"{{this.type}}\" (dblclick)=\"hide()\" *ngIf=\"!isInput\" (click)=\"becomeDiv()\">{{army!=null?this.army.count:\"0\"}}</div>\n     <div class=\"input-field\"  *ngIf=\"isInput\">\n     <input class=\"validate\"  type=\"text\"  style=\" \n     width: 20px;height: 22px\" [ngModel]=\"army!=null?this.army.count:'0'\" \n     name=\"value\" (ngModelChange)=\"newArmy.count=$event\"\n     minlength=\"5\" maxlength=\"7\" #name=\"ngModel\">\n     </div>\n\n"
                }),
                __metadata("design:paramtypes", [newVillageArmiesService_1.CurrVillageArmiesService])
            ], ArmyCellComponent);
            exports_1("ArmyCellComponent", ArmyCellComponent);
        }
    };
});
//# sourceMappingURL=armyCellComponent.js.map