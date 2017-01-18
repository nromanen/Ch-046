System.register(["@angular/core"], function(exports_1, context_1) {
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
    var core_1;
    var PlayerHeader;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            }],
        execute: function() {
            PlayerHeader = (function () {
                function PlayerHeader() {
                }
                PlayerHeader = __decorate([
                    core_1.Component({
                        selector: 'player-head',
                        template: "<nav>\n<div class=\"nav-wrapper\">\n<a href=\"#!\" class=\"brand-logo\">Logo</a>\n<a href=\"#\" data-activates=\"mobile-demo\" class=\"button-collapse\"><i class=\"material-icons\">menu</i></a>\n<ul class=\"right hide-on-med-and-down\">\n<li><a href=\"addVillage.html\">Add village</a></li>\n<li><a href=\"villagesList.html\">All villages</a></li>\n<li><a href=\"/logout\" >Log out</a></li>\n<li><a routerLink=\"/edit\" routerLinkActive=\"active\">Crisis Center</a></li>\n</ul>\n<ul class=\"side-nav\" id=\"mobile-demo\">\n<li><a href=\"sass.html\">Show users</a></li>\n<li><a href=\"badges.html\">Add users</a></li>\n<li><a href=\"collapsible.html\">Javascript</a></li>\n<li><a href=\"mobile.html\">Mobile</a></li>\n</ul>\n</div>\n</nav>"
                    }), 
                    __metadata('design:paramtypes', [])
                ], PlayerHeader);
                return PlayerHeader;
            }());
            exports_1("PlayerHeader", PlayerHeader);
        }
    }
});
//# sourceMappingURL=playerHeader.component.js.map