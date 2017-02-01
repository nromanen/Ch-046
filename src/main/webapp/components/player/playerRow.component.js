System.register(["@angular/core", "../village/village"], function(exports_1, context_1) {
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
    var core_1, village_1;
    var PlayerRow;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (village_1_1) {
                village_1 = village_1_1;
            }],
        execute: function() {
            /**
             * Created by Oleg on 14.01.2017.
             */
            PlayerRow = (function () {
                function PlayerRow() {
                    this.editClick = new core_1.EventEmitter();
                }
                PlayerRow.prototype.edit = function () {
                    this.editClick.emit(this.v);
                };
                __decorate([
                    core_1.Input(), 
                    __metadata('design:type', village_1.Village)
                ], PlayerRow.prototype, "v", void 0);
                PlayerRow = __decorate([
                    core_1.Component({
                        selector: 'player-row',
                        outputs: ['editClick'],
                        template: "\n             <a (click)=\"edit()\" routerLink=\"/edit\"><button class=\"btn waves-effect waves-light col offset-s1 \" type=\"submit\" name=\"action\" style=\"margin-top: 5px;\"\n        >Edit</button></a>\n"
                    }), 
                    __metadata('design:paramtypes', [])
                ], PlayerRow);
                return PlayerRow;
            }());
            exports_1("PlayerRow", PlayerRow);
        }
    }
});
//# sourceMappingURL=playerRow.component.js.map