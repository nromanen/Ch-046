System.register(["@angular/core", "@angular/http", "@angular/router"], function (exports_1, context_1) {
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
    var core_1, http_1, router_1, HelpService;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            }
        ],
        execute: function () {
            HelpService = (function () {
                function HelpService(_http, _activatedRoute) {
                    this._http = _http;
                    this._activatedRoute = _activatedRoute;
                    this.url = 'askhelp';
                }
                HelpService.prototype.getById = function () {
                    console.log(this.url);
                    return this._http.get(this.url)
                        .map(function (res) { return res.json(); });
                };
                return HelpService;
            }());
            HelpService = __decorate([
                core_1.Injectable(),
                __metadata("design:paramtypes", [http_1.Http, router_1.ActivatedRoute])
            ], HelpService);
            exports_1("HelpService", HelpService);
        }
    };
});
//# sourceMappingURL=help.service.js.map