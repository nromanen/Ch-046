System.register(['angular2/core', "./alliance", "../../services/alliacne/alliance-service", "../modal_window/modal", "angular2/src/core/metadata"], function(exports_1, context_1) {
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
    var core_1, alliance_1, alliance_service_1, modal_1, metadata_1;
    var AllianceComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (alliance_1_1) {
                alliance_1 = alliance_1_1;
            },
            function (alliance_service_1_1) {
                alliance_service_1 = alliance_service_1_1;
            },
            function (modal_1_1) {
                modal_1 = modal_1_1;
            },
            function (metadata_1_1) {
                metadata_1 = metadata_1_1;
            }],
        execute: function() {
            AllianceComponent = (function () {
                function AllianceComponent(_allianceService) {
                    this._allianceService = _allianceService;
                    this.alliances = [];
                    this.selectedAlliance = null;
                }
                AllianceComponent.prototype.ontest = function () {
                    console.log("ontest");
                    this.errorMsg.showErrorMessage("Are you sure you want to delete this alliance?");
                };
                AllianceComponent.prototype.editAlliance = function (al) {
                    this.selectedAlliance = al;
                    this.editName = al.name;
                    this.editLogin = al.leaderLogin;
                    this.editEmail = al.leaderEmail;
                };
                AllianceComponent.prototype.deleteAlliance = function (al) {
                    this._allianceService.deleteAlliance(al);
                };
                AllianceComponent.prototype.cancelEditing = function () {
                    this.selectedAlliance = null;
                };
                AllianceComponent.prototype.addAlliance = function () {
                    console.log("AddAlliance");
                    var newAlliance = new alliance_1.Alliance(this.name, this.login, this.email);
                    this.name = "";
                    this.login = "";
                    this.email = "";
                    this._allianceService.addAlliance(newAlliance);
                };
                AllianceComponent.prototype.updateAlliance = function () {
                    console.log("update alliance");
                    var updatedAlliance = new alliance_1.Alliance(this.editName, this.editLogin, this.editEmail);
                    this._allianceService.updateAlliance(this.selectedAlliance, updatedAlliance);
                };
                __decorate([
                    metadata_1.ViewChild(modal_1.ErrorMessage), 
                    __metadata('design:type', modal_1.ErrorMessage)
                ], AllianceComponent.prototype, "errorMsg", void 0);
                AllianceComponent = __decorate([
                    core_1.Component({
                        selector: 'my-alliance',
                        templateUrl: 'components/alliance/alliance.html',
                        providers: [alliance_service_1.AllianceService],
                        directives: [modal_1.ErrorMessage],
                    }), 
                    __metadata('design:paramtypes', [(typeof (_a = typeof alliance_service_1.AllianceService !== 'undefined' && alliance_service_1.AllianceService) === 'function' && _a) || Object])
                ], AllianceComponent);
                return AllianceComponent;
                var _a;
            }());
            exports_1("AllianceComponent", AllianceComponent);
        }
    }
});
//# sourceMappingURL=alliance.component.js.map