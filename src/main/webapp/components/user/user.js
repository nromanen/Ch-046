System.register([], function (exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var User;
    return {
        setters: [],
        execute: function () {
            User = (function () {
                function User(login, email, uuid, alliance, role) {
                    this.uuid = uuid || null;
                    this.login = login;
                    this.email = email;
                    this.alliance = alliance || null;
                    this.role = role == true ? 'LEADER' : null;
                }
                return User;
            }());
            exports_1("User", User);
        }
    };
});
//# sourceMappingURL=user.js.map