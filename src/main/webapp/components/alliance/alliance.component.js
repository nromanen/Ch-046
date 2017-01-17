System.register(["@angular/core", "../services/alliance-service", "../services/pager.service", "@angular/http"], function (exports_1, context_1) {
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
    var core_1, alliance_service_1, pager_service_1, http_1, AllianceComponent;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (alliance_service_1_1) {
                alliance_service_1 = alliance_service_1_1;
            },
            function (pager_service_1_1) {
                pager_service_1 = pager_service_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }
        ],
        execute: function () {
            AllianceComponent = (function () {
                function AllianceComponent(_http, _allianceService, pagerService) {
                    this._http = _http;
                    this._allianceService = _allianceService;
                    this.pagerService = pagerService;
                    this.pager = {};
                    this.selectedAlliance = null;
                    this.deletedAlliance = null;
                    this.url = 'admin/allianceDTO/';
                }
                AllianceComponent.prototype.ngOnInit = function () {
                    this.getAlliances();
                    //this.setPage(1);
                };
                AllianceComponent.prototype.onNotify = function (alliance) {
                    this.selectedAlliance = alliance;
                };
                AllianceComponent.prototype.onNotifyDelete = function (confitmation) {
                    if (confitmation) {
                        this._allianceService.deleteAlliance(this.deletedAlliance);
                    }
                    this.deletedAlliance = null;
                    this.setPage(this.pager.currentPage);
                };
                AllianceComponent.prototype.editAlliance = function (al) {
                    this.selectedAlliance = al;
                };
                AllianceComponent.prototype.deleteAlliance = function (al) {
                    console.log("ontest delete");
                    this.confirmMsg = "Are you sure you want to delete alliance " + al.name + "?";
                    this.deletedAlliance = al;
                };
                AllianceComponent.prototype.cancelEditing = function () {
                    this.selectedAlliance = null;
                };
                AllianceComponent.prototype.setPage = function (page) {
                    if (page < 1 || page > this.pager.totalPages) {
                        return;
                    }
                    // get pager object from service
                    this.pager = this.pagerService.getPager(this.alliances.length, page);
                    // get current page of items
                    this.pagedAlliance = this.allAlliance.slice(this.pager.startIndex, this.pager.endIndex + 1);
                };
                AllianceComponent.prototype.getAlliances = function () {
                    var _this = this;
                    this._allianceService.getAlliances()
                        .subscribe(function (alliances) { return _this.alliances = alliances; }, function (error) { return _this.errorMessage = error; });
                    console.log("subscribe");
                    console.log(this.alliances);
                };
                return AllianceComponent;
            }());
            AllianceComponent = __decorate([
                core_1.Component({
                    selector: 'my-alliance',
                    templateUrl: 'components/alliance/alliance.html',
                    styleUrls: ['components/alliance/alliance.css']
                }),
                __metadata("design:paramtypes", [http_1.Http, alliance_service_1.AllianceService, pager_service_1.PagerService])
            ], AllianceComponent);
            exports_1("AllianceComponent", AllianceComponent);
        }
    };
});
//# sourceMappingURL=alliance.component.js.map