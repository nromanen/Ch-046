System.register(["@angular/core"], function (exports_1, context_1) {
    "use strict";
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __moduleName = context_1 && context_1.id;
    var core_1, PlayerRow;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            }
        ],
        execute: function () {
            PlayerRow = (function () {
                function PlayerRow() {
                }
                return PlayerRow;
            }());
            PlayerRow = __decorate([
                core_1.Component({
                    selector: 'player-row',
                    template: "\n    <tbody>\n    <tr >\n        <td rowspan=\"2\">Village1</td>\n        <td rowspan=\"2\">30</td>\n        <td rowspan=\"2\">35</td>\n        <td rowspan=\"2\">70</td>\n        <td rowspan=\"2\">true</td>\n        <td rowspan=\"2\">50</td>\n        <td>type1</td>\n        <td>63</td>\n    </tr>\n\n    <tr>\n        <td>type2</td>\n        <td>23</td>\n    </tr>\n    </tbody>"
                })
            ], PlayerRow);
            exports_1("PlayerRow", PlayerRow);
        }
    };
});
//# sourceMappingURL=playerRow.component.js.map