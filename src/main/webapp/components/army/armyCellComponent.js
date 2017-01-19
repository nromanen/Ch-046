System.register(["@angular/core", "../village/village"], function (exports_1, context_1) {
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
    var core_1, village_1, ArmyCellComponent;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (village_1_1) {
                village_1 = village_1_1;
            }
        ],
        execute: function () {
            ArmyCellComponent = (function () {
                function ArmyCellComponent() {
                    this.value = 0;
                    this.isForm = true;
                    this.emitter = new core_1.EventEmitter();
                }
                ArmyCellComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    console.log(this.village.armies.length);
                    this.village.armies.forEach(function (army) {
                        console.log(army.type);
                        console.log(_this.type.toString());
                        console.log(_this.type === army.type.toString());
                        if (_this.type == army.type.toString()) {
                            _this.value = army.count;
                        }
                    });
                };
                ArmyCellComponent.prototype.hide = function () {
                    this.isForm = false;
                    // this.emitter.emit(this.village);
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
            ], ArmyCellComponent.prototype, "isForm", void 0);
            ArmyCellComponent = __decorate([
                core_1.Component({
                    selector: 'army-cell',
                    outputs: ['cellClicked'],
                    template: "\n{{diagnostic}}\n     <div title=\"{{this.type}}\" (dblclick)=\"hide()\" *ngIf=\"isVisible\">{{value}}</div>\n     <input  type=\"text\" *ngIf=\"!isVisible\"  style=\"margin: 0;width: 20px;height: 22px\" [(ngModel)]=\"this.value\" name=\"value\" #spy>\n"
                }),
                __metadata("design:paramtypes", [])
            ], ArmyCellComponent);
            exports_1("ArmyCellComponent", ArmyCellComponent);
        }
    };
});
//# sourceMappingURL=armyCellComponent.js.map