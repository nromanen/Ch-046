/**
 * Created by vyach on 13.01.2017.
 */
System.register(['@angular/core', '@angular/http', "rxjs/Observable"], function(exports_1, context_1) {
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
    var core_1, http_1, Observable_1;
    var UserService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (Observable_1_1) {
                Observable_1 = Observable_1_1;
            }],
        execute: function() {
            UserService = (function () {
                function UserService(http) {
                    this.http = http;
                    this.userControllerUrl = "user";
                }
                UserService.prototype.getUsersByAlliance = function () {
                    console.log("UserService.getUsersByAlliance() method is working");
                    return this.http.get(this.userControllerUrl + "/alliance-users/valhala") // todo change to dynamic name
                        .map(function (response) { return response.json(); });
                };
                UserService.prototype.addMember = function (member) {
                    console.log("UserService.addMember() method is working");
                    var body = JSON.stringify({
                        uuid: null,
                        login: member.login,
                        email: member.email,
                        role: member.role,
                        alliance: "valhala" // todo change to dynamic
                    });
                    console.log("Member value is: " + body);
                    var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
                    var options = new http_1.RequestOptions({ headers: headers });
                    return this.http.post(this.userControllerUrl + "/add", body, options)
                        .map(function (response) { return response.json(); }).catch(function (error) {
                        if (error.status == 403) {
                            return Observable_1.Observable.throw('ERROR!!! E-mail did not send, check internet connection!');
                        }
                        else if (error.status == 406) {
                            return Observable_1.Observable.throw('ERROR!!! User with entered login or e-mail already exist!');
                        }
                        else {
                            return Observable_1.Observable.throw('Error was occured, try again later!!!');
                        }
                    });
                };
                UserService.prototype.updateMember = function (member) {
                    console.log("UserService.updateMember() method is working");
                    var body = JSON.stringify({
                        uuid: member.uuid,
                        login: member.login,
                        email: member.email,
                        alliance: "valhala" // todo change to dynamic
                    });
                    console.log("Member value is: " + body);
                    var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
                    var options = new http_1.RequestOptions({ headers: headers });
                    return this.http.put(this.userControllerUrl + "/update/" + member.uuid, body, options)
                        .map(function (response) { return response.json(); });
                };
                UserService.prototype.deleteMember = function (member) {
                    console.log("UserService.updateMember() method is working");
                    var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
                    var options = new http_1.RequestOptions({ headers: headers });
                    return this.http.delete(this.userControllerUrl + "/delete/" + member.uuid, options)
                        .map(function (ok) { return true; });
                };
                UserService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], UserService);
                return UserService;
            }());
            exports_1("UserService", UserService);
        }
    }
});
//# sourceMappingURL=user.service.js.map