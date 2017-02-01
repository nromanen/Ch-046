/**
 * Created by rmochetc on 26.01.2017.
 */
System.register(["@angular/core", "../services/helpNotification/help.service"], function(exports_1, context_1) {
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
    var core_1, help_service_1;
    var AllHelps;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (help_service_1_1) {
                help_service_1 = help_service_1_1;
            }],
        execute: function() {
            AllHelps = (function () {
                function AllHelps(helpService) {
                    this.helpService = helpService;
                }
                AllHelps.prototype.ngOnInit = function () {
                    this.getActiveHelp();
                };
                AllHelps.prototype.getActiveHelp = function () {
                    var _this = this;
                    this.helpService.getActiveHelp()
                        .subscribe(function (resp) {
                        _this.attacks = resp;
                        console.log(_this.attacks);
                    });
                };
                AllHelps = __decorate([
                    core_1.Component({
                        selector: 'ask-help',
                        templateUrl: 'components/help/allHelps.html'
                    }), 
                    __metadata('design:paramtypes', [help_service_1.HelpService])
                ], AllHelps);
                return AllHelps;
            }());
            exports_1("AllHelps", AllHelps);
        }
    }
});
//# sourceMappingURL=all-helps.component.js.map