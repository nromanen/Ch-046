/**
 * Created by okunetc on 16.01.2017.
 */
System.register(["@angular/core", "@angular/http", "@angular/router"], function(exports_1, context_1) {
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
    var core_1, http_1, router_1;
    var PlayerService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            }],
        execute: function() {
            PlayerService = (function () {
                function PlayerService(_http, activatedRoute) {
                    this._http = _http;
                    this.activatedRoute = activatedRoute;
                    this.url = 'player/1';
                }
                PlayerService.prototype.getById = function () {
                    var _this = this;
                    console.log(this.activatedRoute.params.map);
                    console.log(this.activatedRoute.queryParams.map);
                    var str = this.activatedRoute.params.subscribe(function (param) {
                        console.log();
                        var userId = param['id'];
                        console.log(param['id']);
                        console.log(_this.activatedRoute === undefined);
                    });
                    var str1 = this.activatedRoute.queryParams.subscribe(function (par) {
                        console.log(str1);
                        console.log(par);
                        console.log(par['id']);
                    });
                    this._http.get(this.url)
                        .map(function (res) { return res.json(); })
                        .subscribe(function (response) {
                        console.log(response);
                        if (response != null) {
                            _this.player = response;
                        }
                    }, function (error) { return console.log(error); });
                };
                PlayerService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http, router_1.ActivatedRoute])
                ], PlayerService);
                return PlayerService;
            }());
            exports_1("PlayerService", PlayerService);
        }
    }
});
//# sourceMappingURL=player.service.js.map