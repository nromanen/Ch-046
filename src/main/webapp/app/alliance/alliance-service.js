System.register(['angular2/core', 'angular2/http', 'rxjs/Rx'], function(exports_1, context_1) {
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
    var core_1, http_1;
    var AllianceService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (_1) {}],
        execute: function() {
            AllianceService = (function () {
                function AllianceService(_http) {
                    // this.alliances[0] = new Alliance("test_id", "test_name", "test_leaderLogin", "test_leaderEmail");
                    // this.alliances[0] = new Alliance("test_id2", "test_name2", "test_leaderLogin2", "test_leaderEmail2");
                    // this.alliances[0] = new Alliance("test_id3", "test_name3", "test_leaderLogin3", "test_leaderEmail3");
                    this._http = _http;
                    this.alliances = [];
                    this.getFromServer();
                }
                AllianceService.prototype.getCookie = function (name) {
                    var value = "; " + document.cookie;
                    var parts = value.split("; " + name + "=");
                    if (parts.length == 2)
                        return parts.pop().split(";").shift();
                };
                AllianceService.prototype.getAlliances = function () {
                    return this.alliances;
                };
                AllianceService.prototype.getFromServer = function () {
                    var _this = this;
                    console.log("it works");
                    this._http.get('/allianceDTO')
                        .map(function (res) { return res.json(); })
                        .subscribe(function (response) {
                        console.log(response);
                        _this.alliances = response;
                    }, function (error) { return console.log(error); });
                };
                AllianceService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], AllianceService);
                return AllianceService;
            }());
            exports_1("AllianceService", AllianceService);
        }
    }
});
//
//
// import {Injectable, provide} from 'angular2/core';
// import {Http, Headers} from 'angular2/http';
// import 'rxjs/Rx';
// import {Alliance} from './alliance';
//
// @Injectable()
// export class AllianceService {
//
//     alliances: Array<Alliance> = [];
//
//
//     constructor(private _http: Http){
//         this.alliances[0] = new Alliance("test_id", "test_name", "test_leaderLogin", "test_leaderEmail");
//         this.alliances[1] = new Alliance("test_id2", "test_name2", "test_leaderLogin2", "test_leaderEmail2");
//         this.alliances[2] = new Alliance("test_id3", "test_name3", "test_leaderLogin3", "test_leaderEmail3");
//     }
//
//
//     getAlliances() : any {
//         return this.alliances;
//     }
//
// }
//# sourceMappingURL=alliance-service.js.map