/**
 * Created by rmochetc on 06.01.2017.
 */
System.register([], function (exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Alliance;
    return {
        setters: [],
        execute: function () {/**
             * Created by rmochetc on 06.01.2017.
             */
            Alliance = (function () {
                function Alliance(name, leaderLogin, leaderEmail) {
                    this.name = name;
                    this.leaderEmail = leaderEmail;
                    this.leaderLogin = leaderLogin;
                }
                return Alliance;
            }());
            exports_1("Alliance", Alliance);
        }
    };
});
//# sourceMappingURL=alliance.js.map