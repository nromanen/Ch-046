System.register(["@angular/core", "./army", "../UnitType/unitType", "../village/village"], function (exports_1, context_1) {
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
    var core_1, army_1, unitType_1, village_1, AddArmyComponent;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (army_1_1) {
                army_1 = army_1_1;
            },
            function (unitType_1_1) {
                unitType_1 = unitType_1_1;
            },
            function (village_1_1) {
                village_1 = village_1_1;
            }
        ],
        execute: function () {
            AddArmyComponent = (function () {
                function AddArmyComponent() {
                    this.unitTypes = [];
                    this.unitTypeStrings = [];
                    for (var t in unitType_1.UnitType) {
                        if (typeof unitType_1.UnitType[t] === 'string')
                            this.unitTypes.push(+t);
                        else {
                            this.unitTypeStrings.push(t);
                        }
                    }
                    // console.log(this.unitTypes);
                    // console.log(this.unitTypeStrings);
                    // console.log(UnitType[0]);
                }
                AddArmyComponent.prototype.ngAfterViewChecked = function () {
                    // console.log(this.army);
                };
                AddArmyComponent.prototype.ngOnInit = function () {
                    // console.log('army is');
                    // console.log(this.army);
                    this.army.type = unitType_1.UnitType.Axeman;
                };
                return AddArmyComponent;
            }());
            __decorate([
                core_1.Input(),
                __metadata("design:type", army_1.Army)
            ], AddArmyComponent.prototype, "army", void 0);
            __decorate([
                core_1.Input(),
                __metadata("design:type", village_1.Village)
            ], AddArmyComponent.prototype, "village", void 0);
            AddArmyComponent = __decorate([
                core_1.Component({
                    selector: "[add-army]",
                    template: "\n <div class=\"input-field col s6 offset-s3\">\n    <select class=\"browser-default\" [ngModel]=\"army.type\" \n    (ngModelChange)=\"army.type = $event\" name=\"type\" #type>\n    <option value=\"\" disabled selected>Choose your option</option>\n    <option *ngFor=\"let type of unitTypes\" [ngValue]=\"type\">{{unitTypeStrings[type]}}</option>\n  </select>\n    </div>\n    <div class=\"input-field col s6 offset-s3\">\n          <input id=\"count\" type=\"text\" class=\"validate\" [(ngModel)]=\"army.count\" name=\"count\" pattern=\"[0-9]*\" #count=\"ngModel\" required>\n          <label for=\"count\">Count</label>\n          <div *ngIf=\"count.errors && (count.dirty || count.touched)\"\n             class=\"alert alert-danger\">\n            <div [hidden]=\"!count.errors.required\">\n              Count is required\n            </div>\n            <div [hidden]=\"!count.errors.pattern\">\n             Coordinates can contain chars only!\n            </div>          \n        </div>\n        </div>\n        \n  <div class=\"input-field col s6 offset-s3\">\nIs capital?\n</div>\n<div class=\"switch \">\n    <label class=\"col offset-s3\">\n         No\n      <input type=\"checkbox\" [(ngModel)]=\"army.ownUnit\" name=\"isCapital\">\n      <span class=\"lever\"></span>\n      Yes\n    </label>\n  </div>\n"
                }),
                __metadata("design:paramtypes", [])
            ], AddArmyComponent);
            exports_1("AddArmyComponent", AddArmyComponent);
        }
    };
});
//# sourceMappingURL=addArmy.component.js.map