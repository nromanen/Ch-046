System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Role;
    return {
        setters:[],
        execute: function() {
            /**
             * Created by okunetc on 17.01.2017.
             */
            (function (Role) {
                Role[Role["ADMIN"] = 0] = "ADMIN";
                Role[Role["LEADER"] = 1] = "LEADER";
                Role[Role["USER"] = 2] = "USER";
            })(Role || (Role = {}));
            exports_1("Role", Role);
        }
    }
});
//# sourceMappingURL=role.js.map