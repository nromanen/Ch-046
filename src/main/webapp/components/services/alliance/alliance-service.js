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
    var http_1, core_1, Rx_1, AllianceService;
    return {
        setters: [
            function (Rx_1_1) {
                Rx_1 = Rx_1_1;
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
                }
                AllianceService.prototype.getAlliances = function () {
                    return this._http.get(this.url)
                        .map(function (res) {
                        console.log(res);
                        return res.json();
                    }) // ...and calling .json() on the response to return data
                        .catch(function (error) { return Rx_1.Observable.throw(error.json().error || 'Server error'); }); //...errors if
                };
                AllianceService.prototype.addAlliance = function (alliance) {
                    var body = JSON.stringify({
                        name: alliance.name,
                        leaderLogin: alliance.leaderLogin,
                        leaderEmail: alliance.leaderEmail
                    });
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    return this._http.post(this.url, body, {
                        headers: headers
                    }).map(function (res) { return res.json(); }) // ...and calling .json() on the response to return data
                        .catch(function (error) {
                        if (error.status == 400) {
                            return Rx_1.Observable.throw('ERROR!!! Invalid data. Please check entered data!');
                        }
                        else if (error.status == 409) {
                            return Rx_1.Observable.throw('ERROR!!! User whit the same login or e-mail is in DB!');
                        }
                        else if (error.status == 403) {
                            return Rx_1.Observable.throw('ERROR!!! Alliance whit the same name is in DB!');
                        }
                        else {
                            return Rx_1.Observable.throw('UNKNOWN ERROR!!!');
                        }
                    }); //...errors if
                };
                AllianceService.prototype.deleteAlliance = function (alliance) {
                    var result = true;
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    this._http.delete(this.url + alliance.allianceUuid, {
                        headers: headers
                    }).subscribe(function (response) {
                        console.log('Alliance deleted, id = ' + alliance.allianceUuid);
                        result = true;
                    }, function (error) {
                        console.log(error);
                        result = false;
                    });
                    return result;
                };
                AllianceService.prototype.updateAlliance = function (newAlliance) {
                    var body = JSON.stringify({
                        name: newAlliance.name,
                        leaderLogin: newAlliance.leaderLogin,
                        leaderEmail: newAlliance.leaderEmail,
                        leaderUuid: newAlliance.leaderUuid
                    });
                    console.log("body");
                    console.log(body);
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    return this._http.put(this.url + newAlliance.allianceUuid, body, {
                        headers: headers
                    }).map(function (res) { return res.json(); }) // ...and calling .json() on the response to return data
                        .catch(function (error) {
                        if (error.status == 400) {
                            return Rx_1.Observable.throw('ERROR!!! Invalid data. Please check entered data!');
                        }
                        else if (error.status == 409) {
                            return Rx_1.Observable.throw('ERROR!!! User whit the same login or e-mail is in DB!');
                        }
                        else {
                            return Rx_1.Observable.throw('UNKNOWN ERROR!!!');
                        }
                    }); //...errors if
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