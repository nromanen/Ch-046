System.register(['angular2/core', '../user/user', "../../services/user/user-service"], function(exports_1, context_1) {
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
    var core_1, user_1, user_service_1;
    var LeaderManageComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (user_1_1) {
                user_1 = user_1_1;
            },
            function (user_service_1_1) {
                user_service_1 = user_service_1_1;
            }],
        execute: function() {
            LeaderManageComponent = (function () {
                function LeaderManageComponent(userService) {
                    this.userService = userService;
                }
                LeaderManageComponent.prototype.getAllianceUsers = function () {
                    this.users = [
                        new user_1.User("user1", "111", "sdf@ds.com", "al1"),
                        new user_1.User("user2", "222", "sdf@ds.com", "al2")
                    ];
                    return this.users;
                    // return this.userService.getUsersByAlliance();
                };
                LeaderManageComponent = __decorate([
                    core_1.Component({
                        selector: "leader-manage",
                        templateUrl: "components/leader/leader.html",
                        providers: [user_service_1.UserService],
                    }), 
                    __metadata('design:paramtypes', [user_service_1.UserService])
                ], LeaderManageComponent);
                return LeaderManageComponent;
            }());
            exports_1("LeaderManageComponent", LeaderManageComponent);
        }
    }
});
//# sourceMappingURL=leader.manage.component.js.map