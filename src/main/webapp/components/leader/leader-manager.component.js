System.register(['@angular/core', "../services/user.service"], function(exports_1, context_1) {
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
    var core_1, user_service_1;
    var LeaderManagerComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (user_service_1_1) {
                user_service_1 = user_service_1_1;
            }],
        execute: function() {
            LeaderManagerComponent = (function () {
                function LeaderManagerComponent(userService) {
                    this.userService = userService;
                    this.errorMessage = null;
                    this.successMessage = null;
                    this.selectedMember = null;
                    this.deletedMember = null;
                    this.confirmMsg = null;
                    console.log("LeaderManagerComponent constructor is working");
                }
                LeaderManagerComponent.prototype.ngOnInit = function () {
                    console.log("LeaderManagerComponent.ngOnInit() method is working");
                    this.getUsersByAlliance();
                };
                LeaderManagerComponent.prototype.getUsersByAlliance = function () {
                    var _this = this;
                    console.log("LeaderManagerComponent.getUsersByAlliance() method is working");
                    this.userService.getUsersByAlliance()
                        .subscribe(function (users) {
                        _this.users = users;
                        console.log(JSON.stringify(users));
                    }, function (error) { return console.log(error); });
                };
                LeaderManagerComponent.prototype.selectMember = function (member) {
                    console.log("usersComponent.selectMember() method is working.");
                    this.selectedMember = member;
                };
                LeaderManagerComponent.prototype.closeSuccess = function () {
                    this.successMessage = null;
                };
                LeaderManagerComponent.prototype.closeError = function () {
                    this.errorMessage = null;
                };
                LeaderManagerComponent.prototype.addMember = function (member) {
                    var _this = this;
                    console.log("LeaderManagerComponent.addMember() method is working");
                    member.alliance = "valhala"; // todo change to dynamic set alliance name
                    this.userService.addMember(member)
                        .subscribe(function (user) {
                        _this.users.push(user);
                        _this.successMessage = "User added successfully";
                        _this.errorMessage = null;
                    }, function (error) {
                        console.log(error);
                        _this.errorMessage = error;
                        _this.successMessage = null;
                    });
                };
                LeaderManagerComponent.prototype.updateMember = function (member) {
                    var _this = this;
                    console.log("LeaderManagerComponent.updateMember() method is working. Member value: " + JSON.stringify(member));
                    if (member !== null) {
                        this.userService.updateMember(member)
                            .subscribe(function (user) {
                            console.log("LeaderManagerComponent.updateMember() user value: " + JSON.stringify(user));
                            console.log("User from array: " + JSON.stringify(_this.users[_this.users.indexOf(_this.selectedMember)]));
                            _this.users[_this.users.indexOf(_this.selectedMember)] = user;
                            _this.selectedMember = null;
                        }, function (error) {
                            console.log(error);
                            _this.selectedMember = null;
                        });
                    }
                    else {
                        this.selectedMember = member;
                    }
                };
                LeaderManagerComponent.prototype.deleteMember = function (member) {
                    this.deletedMember = member;
                    this.confirmMsg = "Are you going to delete alliance member: " + member.login + "?";
                };
                LeaderManagerComponent.prototype.onConfirmDelete = function (confirmation) {
                    var _this = this;
                    console.log("LeaderManagerComponent.deleteMember() method is working");
                    if (confirmation) {
                        this.userService.deleteMember(this.deletedMember)
                            .subscribe(function (status) {
                            if (status) {
                                _this.users.splice(_this.users.indexOf(_this.deletedMember), 1);
                                _this.deletedMember = null;
                            }
                        }, function (error) { return console.log(error); });
                    }
                    else {
                        this.deletedMember = null;
                    }
                };
                LeaderManagerComponent = __decorate([
                    core_1.Component({
                        selector: "leader-manager",
                        templateUrl: "components/leader/leader.html",
                        providers: [user_service_1.UserService]
                    }), 
                    __metadata('design:paramtypes', [user_service_1.UserService])
                ], LeaderManagerComponent);
                return LeaderManagerComponent;
            }());
            exports_1("LeaderManagerComponent", LeaderManagerComponent);
        }
    }
});
//# sourceMappingURL=leader-manager.component.js.map