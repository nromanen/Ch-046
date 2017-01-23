System.register(["@angular/core"], function (exports_1, context_1) {
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
    var core_1, AddVillageForm;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            }
        ],
        execute: function () {
            AddVillageForm = (function () {
                function AddVillageForm() {
                    this.a = [];
                    this.a.push(5);
                    this.str = "kjjkkjkj";
                }
                AddVillageForm.prototype.ngOnInit = function () {
                    this.str = 'gdgfdgd';
                };
                return AddVillageForm;
            }());
            AddVillageForm = __decorate([
                core_1.Component({
                    selector: "add-vill-form",
                    template: "\n    <!---->\n    <!--<form class=\"col s12\">-->\n       <!--<div class=\"input-field col s6 offset-s3\">-->\n          <!--<input id=\"last_name\" type=\"text\" class=\"validate\">-->\n          <!--<label for=\"last_name\">Last Name</label>-->\n        <!--</div>-->\n\n    <!--</form>-->\n \n      <form #heroForm=\"ngForm\">\n  <div class=\"row container\">\n  <div class=\"input-field col s6 offset-s3\">\n          <input id=\"name\" type=\"text\" class=\"validate\" [(ngModel)]=\"this.str\" name=\"name\" #name=\"ngModel\" required pattern=\"^[0-9]{1,3}\">\n          <label for=\"name\">Name</label>\n          <div *ngIf=\"name.errors && (name.dirty || name.touched)\"\n             class=\"alert alert-danger\">\n            <div [hidden]=\"!name.errors.required\">\n              Name is required\n            </div>\n            <div [hidden]=\"!name.errors.requiredpattern\">\n              Name must be at least 4 characters long.\n            </div>\n            <div [hidden]=\"!name.errors.maxlength\">\n              Name cannot be more than 24 characters long.\n            </div>\n        </div>\n        </div>\n  \n  <div class=\"input-field col s6 offset-s3\">\n          <input id=\"x\" type=\"text\" class=\"validate\">\n          <label for=\"x\">X</label>\n        </div>\n        <div class=\"input-field col s6 offset-s3\">\n          <input id=\"y\" type=\"text\" class=\"validate\">\n          <label for=\"y\">Y</label>\n        </div>\n\n<div class=\"input-field col s6 offset-s3\">\n          <input id=\"population\" type=\"text\" class=\"validate\">\n          <label for=\"population\">Population</label>\n        </div>\n\n\n  \t <div class=\"input-field col s6 offset-s3\" >\n    <select class=\"browser-default\" id=\"select\" >\n    <option value=\"\" disabled selected>Choose your option</option>\n    <option value=\"1\">Option 1</option>\n    <option value=\"2\">Option 2</option>\n    <option value=\"3\">Option 3</option>\n  </select>\n    </div>\n  \n<div class=\"input-field col s6 offset-s3\">\nIs capital?\n</div>\n<div class=\"switch \">\n    <label class=\"col offset-s3\">\n         No\n      <input type=\"checkbox\">\n      <span class=\"lever\"></span>\n      Yes\n    </label>\n  </div>\n\n<div class=\"input-field col s6 offset-s3\">\n<!--<button class=\"btn waves-effect waves-light col offset-s5 \" type=\"submit\" name=\"action\">Add-->\n    <!--<i class=\"material-icons right\">send</i>-->\n  <!--</button>-->\n  \n <button type=\"submit\" class=\"btn btn-default\"\n             [disabled]=\"!heroForm.form.valid\">Submit</button>\n</div>\n  </div>  \t\n  </form>\n"
                }),
                __metadata("design:paramtypes", [])
            ], AddVillageForm);
            exports_1("AddVillageForm", AddVillageForm);
        }
    };
});
//# sourceMappingURL=addVillageForm.js.map