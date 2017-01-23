System.register(["@angular/core", "../services/newVillageArmiesService"], function (exports_1, context_1) {
    "use strict";
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var __moduleName = context_1 && context_1.id;
    var core_1, newVillageArmiesService_1, EditVillageComponent;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (newVillageArmiesService_1_1) {
                newVillageArmiesService_1 = newVillageArmiesService_1_1;
            }
        ],
        execute: function () {
            EditVillageComponent = (function () {
                function EditVillageComponent(currVillageService) {
                    this.currVillageService = currVillageService;
                    this.village = currVillageService.village;
                }
                EditVillageComponent.prototype.ngOnInit = function () {
                    console.log(this.village.isCapital);
                };
                return EditVillageComponent;
            }());
            EditVillageComponent = __decorate([
                core_1.Component({
                    selector: 'edit-village',
                    template: "<player-head></player-head>\n<div>hello</div>\n<div class=\"container\">\n    <div class=\"row\">\n        <form class=\"col s12\">\n            <div class=\"row\">\n                <div class=\"input-field col s6 offset-s3\">\n                    <input name=\"name\"  [(ngModel)]=\"village.name\" value=\"{{village.name}}\" id=\"name\" type=\"text\" class=\"validate\" #newName>\n                    <label class=\"active\" for=\"name\">Name</label>\n                </div>\n            </div>\n\n            <!--<div class=\"row\">-->\n                <!--<div class=\"input-field col s6 offset-s3\">-->\n                    <!--<input value=\"{{village.xCoord}}\" id=\"xCoord\" type=\"text\" class=\"validate\" #newX>-->\n                    <!--<label class=\"active\" for=\"xCoord\">X coordinate</label>-->\n                <!--</div>-->\n            <!--</div>-->\n\n            <!--<div class=\"row\">-->\n                <!--<div class=\"input-field col s6 offset-s3\">-->\n                    <!--<input value=\"{{village.yCoord}}\" id=\"yCoord\" type=\"text\" class=\"validate\" #newY>-->\n                    <!--<label class=\"active\" for=\"yCoord\">Y coordinate</label>-->\n                <!--</div>-->\n            <!--</div>-->\n\n            <!--<div class=\"row\">-->\n                <!--<div class=\"input-field col s6 offset-s3\">-->\n                    <!--<input value=\"{{village.population}}\" id=\"population\" type=\"text\" class=\"validate\" #newPopulation>-->\n                    <!--<label class=\"active\" for=\"population\">Population</label>-->\n                <!--</div>-->\n            <!--</div>-->\n\n\n            <!--<div class=\"row\">-->\n                <!--<div class=\"input-field col s6 offset-s3\">-->\n                    <!--<input value=\"{{village.wall}}\" id=\"isCapital\" type=\"text\" class=\"validate\" #newWall>-->\n                    <!--<label class=\"active\" for=\"isCapital\">Wall</label>-->\n                <!--</div>-->\n            <!--</div>-->\n\n            <!---->\n\n            <!--<div class=\"row\">-->\n                <!--<div class=\"input-field col s6 offset-s3\">-->\n                    <!--<div class=\"switch\">-->\n                        <!--<label>-->\n                            <!--No-->\n                            <!--<input type=\"checkbox\" name=\"isCapital\" required (ngModel)=\"village.isCapital()\">-->\n                            <!--<span class=\"lever\"></span>-->\n                            <!--Yes-->\n                        <!--</label>-->\n                    <!--</div>-->\n                <!--</div>-->\n            <!--</div>-->\n\n            <!--<div class=\"row\">-->\n                <!--<div class=\"col offset-s5\">-->\n                    <!--<button class=\"btn waves-effect waves-light\" style=\"margin-top: 27px\">Save</button>-->\n                <!--</div>-->\n            <!--</div>-->\n        </form>\n    </div>\n</div>\n"
                }),
                __metadata("design:paramtypes", [newVillageArmiesService_1.CurrVillageArmiesService])
            ], EditVillageComponent);
            exports_1("EditVillageComponent", EditVillageComponent);
        }
    };
});
//# sourceMappingURL=editViallage.component.js.map