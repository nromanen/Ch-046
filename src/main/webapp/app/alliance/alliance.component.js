System.register(['angular2/core', "./alliance-service"], function(exports_1, context_1) {
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
    var core_1, alliance_service_1;
    var AllianceComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (alliance_service_1_1) {
                alliance_service_1 = alliance_service_1_1;
            }],
        execute: function() {
            AllianceComponent = (function () {
                function AllianceComponent(_allianceService) {
                    this._allianceService = _allianceService;
                    this.alliances = [];
                    this.selectedAlliance = null;
                }
                AllianceComponent.prototype.editAlliance = function (al) {
                    this.selectedAlliance = al;
                    this.editName = al.name;
                    this.editLogin = al.leaderLogin;
                    this.editEmail = al.leaderEmail;
                };
                AllianceComponent.prototype.cancelEditing = function () {
                    this.selectedAlliance = null;
                };
                AllianceComponent = __decorate([
                    core_1.Component({
                        selector: 'my-alliance',
                        templateUrl: 'app/alliance/alliance.html',
                        providers: [alliance_service_1.AllianceService],
                    }), 
                    __metadata('design:paramtypes', [alliance_service_1.AllianceService])
                ], AllianceComponent);
                return AllianceComponent;
            }());
            exports_1("AllianceComponent", AllianceComponent);
        }
    }
});
//# sourceMappingURL=alliance.component.js.map