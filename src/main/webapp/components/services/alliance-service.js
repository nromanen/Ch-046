System.register(["rxjs/Rx", "@angular/http", "@angular/core"], function (exports_1, context_1) {
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
    var http_1, core_1, AllianceService;
    return {
        setters: [
            function (_1) {
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            }
        ],
        execute: function () {
            AllianceService = (function () {
                function AllianceService(_http) {
                    this._http = _http;
                    this.url = 'admin/allianceDTO/';
                    this.alliances = new Array();
                    this.getFromServer();
                }
                AllianceService.prototype.getAlliances = function () {
                    return this.alliances;
                };
                AllianceService.prototype.getFromServer = function () {
                    var _this = this;
                    console.log("it works from another path");
                    this._http.get(this.url)
                        .map(function (res) { return res.json(); })
                        .subscribe(function (response) {
                        console.log(response);
                        if (response != null) {
                            _this.alliances = response;
                        }
                    }, function (error) { return console.log(error); });
                };
                AllianceService.prototype.addAlliance = function (alliance) {
                    var _this = this;
                    var body = JSON.stringify({ name: alliance.name, leaderLogin: alliance.leaderLogin, leaderEmail: alliance.leaderEmail });
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    this._http.post(this.url, body, {
                        headers: headers
                    }).map(function (res) { return res.json(); })
                        .subscribe(function (response) {
                        console.log('Alliance created successful');
                        _this.alliances.push(response);
                    }, function (error) { return console.log(error); });
                };
                AllianceService.prototype.deleteAlliance = function (alliance) {
                    var _this = this;
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    this._http.delete(this.url + alliance.uuid, {
                        headers: headers
                    }).subscribe(function (response) {
                        console.log('Alliance deleted, id = ' + alliance.uuid);
                        _this.alliances.splice(_this.alliances.indexOf(alliance), 1);
                    }, function (error) { return console.log(error); });
                };
                AllianceService.prototype.updateAlliance = function (alliance, newAlliance) {
                    var _this = this;
                    var body = JSON.stringify({ name: newAlliance.name, leaderLogin: newAlliance.leaderLogin, leaderEmail: newAlliance.leaderEmail });
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    this._http.put(this.url + alliance.uuid, body, {
                        headers: headers
                    }).subscribe(function (response) {
                        console.log('Alliance updated, id = ' + alliance.uuid + ', status = ' + response.status);
                        _this.alliances[_this.alliances.indexOf(alliance)] = newAlliance;
                    }, function (error) { return console.log(error); });
                };
                return AllianceService;
            }());
            AllianceService = __decorate([
                core_1.Injectable(),
                __metadata("design:paramtypes", [http_1.Http])
            ], AllianceService);
            exports_1("AllianceService", AllianceService);
        }
    };
});
//# sourceMappingURL=alliance-service.js.map