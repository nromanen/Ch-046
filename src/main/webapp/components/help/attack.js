System.register([], function (exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Attack;
    return {
        setters: [],
        execute: function () {
            /**
             * Created by rmochetc on 06.01.2017.
             */
            Attack = (function () {
                function Attack(villageName, enemy, timeAttack) {
                    this.village = villageName;
                    this.enemy = enemy;
                    this.timeAttack = timeAttack;
                }
                return Attack;
            }());
            exports_1("Attack", Attack);
        }
    };
});
//# sourceMappingURL=attack.js.map