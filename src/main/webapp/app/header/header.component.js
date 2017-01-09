/**
 * Created by rmochetc on 06.01.2017.
 */
System.register(['angular2/core'], function(exports_1, context_1) {
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
    var HeaderComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            }],
        execute: function() {
            HeaderComponent = (function () {
                function HeaderComponent() {
                }
                HeaderComponent = __decorate([
                    core_1.Component({
                        selector: 'my-header',
                        template: "\n<nav>\n    <div class=\"nav-wrapper\">\n        <a href=\"#!\" class=\"brand-logo\">Logo</a>\n        <a href=\"#\" data-activates=\"mobile-demo\" class=\"button-collapse\"><i class=\"material-icons\">menu</i></a>\n        <ul class=\"right hide-on-med-and-down\">\n            <li><a href=\"todo\">Todo</a></li>\n            <li><a href=\"/\">Alliances</a></li>\n            <li><a href=\"#\">Jingle</a></li>\n            <li><a href=\"#\">Mobile</a></li>\n        </ul>\n        <ul class=\"side-nav\" id=\"mobile-demo\">\n            <li><a href=\"#\">Show users</a></li>\n            <li><a href=\"#\">Add users</a></li>\n            <li><a href=\"#l\">Javascript</a></li>\n            <li><a href=\"#l\">Mobile</a></li>\n        </ul>\n    </div>\n</nav>\n\n    ",
                    }), 
                    __metadata('design:paramtypes', [])
                ], HeaderComponent);
                return HeaderComponent;
            }());
            exports_1("HeaderComponent", HeaderComponent);
        }
    }
});
//# sourceMappingURL=header.component.js.map