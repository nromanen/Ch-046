/**
 * Created by rmochetc on 22.01.2017.
 */
System.register(['rxjs/Rx', "@angular/core", "@angular/http"], function(exports_1, context_1) {
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
    var core_1, http_1, Rx_1;
    var HelpService;
    return {
        setters:[
            function (Rx_1_1) {
                Rx_1 = Rx_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            HelpService = (function () {
                function HelpService(http) {
                    this.http = http;
                    this.url = 'askhelp';
                }
                HelpService.prototype.getById = function () {
                    return this.http.get(this.url)
                        .map(function (res) { return res.json(); });
                };
                HelpService.prototype.addAttack = function (attack) {
                    var body = JSON.stringify({
                        villageId: attack.village,
                        enemy: attack.enemy,
                        attackTime: attack.timeAttack
                    });
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    return this.http.post(this.url, body, {
                        headers: headers
                    }).map(function (res) { return res.json(); })
                        .catch(function (error) {
                        return Rx_1.Observable.throw(error);
                    });
                };
                HelpService.prototype.getActiveHelp = function () {
                    console.log(this.url);
                    return this.http.get("allAttack")
                        .map(function (res) { return res.json(); });
                };
                HelpService.prototype.getAlliance = function () {
                    console.log("INIT ALLIANCE");
                    return this.http.get("user/helpInit/")
                        .map(function (res) { return res.json(); });
                };
                HelpService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], HelpService);
                return HelpService;
            }());
            exports_1("HelpService", HelpService);
        }
    }
});
//# sourceMappingURL=help.service.js.map