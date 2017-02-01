System.register(["@angular/core", "../village/village", "../army/army", "../services/villageService"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, village_1, army_1, villageService_1;
    var AddVillageForm;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (village_1_1) {
                village_1 = village_1_1;
            },
            function (army_1_1) {
                army_1 = army_1_1;
            },
            function (villageService_1_1) {
                villageService_1 = villageService_1_1;
            }],
        execute: function() {
            AddVillageForm = (function () {
                function AddVillageForm(villageService) {
                    this.villageService = villageService;
                    this.village = new village_1.Village("");
                    this.village.armies = [];
                    this.village.isCapital = false;
                    this.wasSubmitted = new core_1.EventEmitter();
                    this.showAddArmyButton = true;
                }
                AddVillageForm.prototype.ngOnInit = function () {
                };
                AddVillageForm.prototype.ngAfterViewChecked = function () {
                };
                AddVillageForm.prototype.addArmies = function () {
                    this.village.armies.push(new army_1.Army);
                    console.log(this.village);
                    this.showAddArmyButton = false;
                };
                AddVillageForm.prototype.onSubmit = function (village) {
                    this.player.villages.push(village);
                    this.villageService.add(village);
                    this.village = new village_1.Village("");
                    this.village.armies = [];
                    this.wasSubmitted.emit(false);
                };
                AddVillageForm.prototype.showAddArmy = function (ifShow) {
                    this.showAddArmyButton = ifShow;
                };
                __decorate([
                    core_1.Input(), 
                    __metadata('design:type', Object)
                ], AddVillageForm.prototype, "player", void 0);
                __decorate([
                    core_1.Output(), 
                    __metadata('design:type', (typeof (_a = typeof core_1.EventEmitter !== 'undefined' && core_1.EventEmitter) === 'function' && _a) || Object)
                ], AddVillageForm.prototype, "wasSubmitted", void 0);
                AddVillageForm = __decorate([
                    core_1.Component({
                        selector: "add-vill-form",
                        templateUrl: "components/village/addVillageForm.html"
                    }), 
                    __metadata('design:paramtypes', [villageService_1.VillageService])
                ], AddVillageForm);
                return AddVillageForm;
                var _a;
            }());
            exports_1("AddVillageForm", AddVillageForm);
        }
    }
});
//# sourceMappingURL=addVillageForm.js.map