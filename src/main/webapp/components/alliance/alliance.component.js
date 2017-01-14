System.register(["./alliance", "@angular/core", "../services/alliance-service"], function (exports_1, context_1) {
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
    var alliance_1, core_1, alliance_service_1, AllianceComponent;
    return {
        setters: [
            function (alliance_1_1) {
                alliance_1 = alliance_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (alliance_service_1_1) {
                alliance_service_1 = alliance_service_1_1;
            }
        ],
        execute: function () {
            AllianceComponent = (function () {
                // @ViewChild(ErrorMessage) errorMsg: ErrorMessage;  // ErrorMessage is a ViewChild
                //
                function AllianceComponent(_allianceService) {
                    this._allianceService = _allianceService;
                    this.alliances = [new alliance_1.Alliance('Valhala', 'borg', 'test@net.com'), new alliance_1.Alliance('Torr', 'viking', 'test@nmetacom')];
                    this.selectedAlliance = null;
                }
                AllianceComponent.ontest = function () {
                    console.log("ontest");
                    // this.errorMsg.showErrorMessage("Are you sure you want to delete this alliance?");
                };
                AllianceComponent.prototype.onNotify = function (alliance) {
                    this.selectedAlliance = alliance;
                };
                AllianceComponent.prototype.editAlliance = function (al) {
                    this.selectedAlliance = al;
                };
                AllianceComponent.prototype.deleteAlliance = function (al) {
                    this._allianceService.deleteAlliance(al);
                    // this.alliances.splice(this.alliances.indexOf(al), 1);
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
                    // this.alliances.push(newAlliance);
                };
                return AllianceComponent;
            }());
            AllianceComponent = __decorate([
                core_1.Component({
                    selector: 'my-alliance',
                    templateUrl: 'components/alliance/alliance.html',
                }),
                __metadata("design:paramtypes", [alliance_service_1.AllianceService])
            ], AllianceComponent);
            exports_1("AllianceComponent", AllianceComponent);
        }
    };
});
//# sourceMappingURL=alliance.component.js.map