System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Army;
    return {
        setters:[],
        execute: function() {
            /**
             * Created by okunetc on 19.01.2017.
             */
            Army = (function () {
                function Army(count, type, ownUnit) {
                    this.type = type || null;
                    this.count = count || 0;
                    this.ownUnit = ownUnit || false;
                }
                return Army;
            }());
            exports_1("Army", Army);
        }
    }
});
//# sourceMappingURL=army.js.map