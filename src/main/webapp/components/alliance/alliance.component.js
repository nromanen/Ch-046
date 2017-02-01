System.register(["@angular/core", "../services/alliance/alliance-service"], function(exports_1, context_1) {
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
                    this.errorMessage = null;
                    this.successMessage = null;
                    this.addNewAlliance = false;
                    this.selectedAlliance = null;
                    this.deletedAlliance = null;
                }
                AllianceComponent.prototype.ngOnInit = function () {
                    this.getAlliances();
                };
                AllianceComponent.prototype.getAlliances = function () {
                    var _this = this;
                    this._allianceService.getAlliances()
                        .subscribe(function (alliances) { return _this.alliances = alliances; }, function (error) { return _this.errorMessage = error; });
                };
                AllianceComponent.prototype.onNotifyUpdate = function (alliance) {
                    var _this = this;
                    if (alliance !== null) {
                        this._allianceService.updateAlliance(alliance)
                            .subscribe(function (resp) {
                            _this.alliances[_this.alliances.indexOf(_this.selectedAlliance)] = resp;
                            _this.successMessage = "Alliance updated successfully";
                            _this.errorMessage = null;
                            _this.selectedAlliance = null;
                        }, function (error) {
                            _this.errorMessage = error;
                            _this.successMessage = null;
                            _this.selectedAlliance = null;
                        });
                    }
                    else {
                        this.selectedAlliance = alliance;
                    }
                };
                AllianceComponent.prototype.onNotifyDelete = function (confitmation) {
                    if (confitmation) {
                        if (this._allianceService.deleteAlliance(this.deletedAlliance)) {
                            this.alliances.splice(this.alliances.indexOf(this.deletedAlliance), 1);
                            this.successMessage = "Alliance deleted successfully";
                            this.errorMessage = null;
                        }
                    }
                    this.deletedAlliance = null;
                };
                AllianceComponent.prototype.onNotifyCreate = function (alliance) {
                    var _this = this;
                    this._allianceService.addAlliance(alliance)
                        .subscribe(function (resp) {
                        _this.alliances.push(resp);
                        _this.successMessage = "Alliance added successfully";
                        _this.errorMessage = null;
                    }, function (error) {
                        _this.errorMessage = error;
                        _this.successMessage = null;
                    });
                    this.addNewAlliance = false;
                };
                AllianceComponent.prototype.newAlliance = function () {
                    this.addNewAlliance = true;
                };
                AllianceComponent.prototype.editAlliance = function (alliance) {
                    this.selectedAlliance = alliance;
                };
                AllianceComponent.prototype.deleteAlliance = function (alliance) {
                    this.confirmMsg = "Are you sure you want to delete alliance " + alliance.name + "?";
                    this.deletedAlliance = alliance;
                };
                AllianceComponent.prototype.cancelEditing = function () {
                    this.selectedAlliance = null;
                };
                AllianceComponent.prototype.closeSuccess = function () {
                    this.successMessage = null;
                };
                AllianceComponent.prototype.closeError = function () {
                    this.errorMessage = null;
                };
                AllianceComponent = __decorate([
                    core_1.Component({
                        selector: 'my-alliance',
                        templateUrl: 'components/alliance/alliance.html',
                        styleUrls: ['components/alliance/alliance.css']
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