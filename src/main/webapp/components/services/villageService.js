System.register(["@angular/core", "../village/village", "@angular/http"], function(exports_1, context_1) {
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
    var core_1, village_1, http_1;
    var VillageService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (village_1_1) {
                village_1 = village_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            /**
             * Created by okunetc on 20.01.2017.
             */
            VillageService = (function () {
                function VillageService(http) {
                    this.http = http;
                    this.villageURL = "village/";
                }
                VillageService.prototype.update = function (village) {
                    var _this = this;
                    var villageBefore = new village_1.Village("");
                    villageBefore.name = village.name;
                    villageBefore.uuid = village.uuid;
                    villageBefore.armies = village.armies;
                    villageBefore.isCapital = village.isCapital;
                    villageBefore.population = village.population;
                    villageBefore.xCoord = village.xCoord;
                    villageBefore.yCoord = village.yCoord;
                    villageBefore.wall = village.wall;
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    this.http.put(this.villageURL + village.uuid, JSON.stringify(village), {
                        headers: headers
                    }).subscribe(function (response) {
                        console.log(response);
                        var newVillage = response.json();
                        console.log(newVillage);
                        _this.villages[_this.villages.indexOf(village)] = newVillage;
                    }, function (error) { return console.log(error); });
                };
                VillageService.prototype.add = function (village) {
                    var _this = this;
                    var body = JSON.stringify(village);
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    this.http.post(this.villageURL, body, {
                        headers: headers
                    }).map(function (res) { return res.json(); })
                        .subscribe(function (response) {
                        _this.villages.push(response);
                    }, function (error) { return console.log(error); });
                };
                VillageService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [(typeof (_a = typeof http_1.Http !== 'undefined' && http_1.Http) === 'function' && _a) || Object])
                ], VillageService);
                return VillageService;
                var _a;
            }());
            exports_1("VillageService", VillageService);
        }
    }
});
//# sourceMappingURL=villageService.js.map