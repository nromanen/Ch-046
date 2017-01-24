System.register(["@angular/core", "../village/village", "@angular/forms", "../army/army", "../services/villageService"], function (exports_1, context_1) {
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
    var core_1, village_1, forms_1, army_1, villageService_1, AddVillageForm;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (village_1_1) {
                village_1 = village_1_1;
            },
            function (forms_1_1) {
                forms_1 = forms_1_1;
            },
            function (army_1_1) {
                army_1 = army_1_1;
            },
            function (villageService_1_1) {
                villageService_1 = villageService_1_1;
            }
        ],
        execute: function () {
            AddVillageForm = (function () {
                function AddVillageForm(villageService) {
                    this.villageService = villageService;
                    this.formErrors = {
                        'name': '',
                        'power': ''
                    };
                    this.validationMessages = {
                        'name': {
                            'required': 'Name is required.',
                            'minlength': 'Name must be at least 4 characters long.',
                            'maxlength': 'Name cannot be more than 24 characters long.',
                            'forbiddenName': 'Someone named "Bob" cannot be a hero.'
                        },
                        'power': {
                            'required': 'Power is required.'
                        }
                    };
                    this.village = new village_1.Village;
                    this.village.armies = [];
                    this.village.isCapital = false;
                }
                AddVillageForm.prototype.ngOnInit = function () {
                };
                AddVillageForm.prototype.ngAfterViewChecked = function () {
                    // console.log(this.village);
                    // this.formChanged();
                };
                AddVillageForm.prototype.formChanged = function () {
                    var _this = this;
                    // console.log(this.currentForm);
                    // console.log(this.str);
                    // alert(this.heroForm===null);
                    if (this.currentForm === this.heroForm) {
                        // alert("dgfdfg");
                        // console.log(this.currentForm);
                        // console.log(this.heroForm);
                        return;
                    }
                    this.heroForm = this.currentForm;
                    if (this.heroForm) {
                        this.heroForm.valueChanges
                            .subscribe(function (data) { return _this.onValueChanged(data); });
                    }
                };
                AddVillageForm.prototype.onValueChanged = function (data) {
                    // alert("dgfdfg");
                    if (!this.heroForm) {
                        return;
                    }
                    var form = this.heroForm.form;
                    for (var field in this.formErrors) {
                        // clear previous error message (if any)
                        this.formErrors[field] = '';
                        var control = form.get(field);
                        if (control && control.dirty && !control.valid) {
                            var messages = this.validationMessages[field];
                            for (var key in control.errors) {
                                this.formErrors[field] += messages[key] + ' ';
                            }
                        }
                    }
                };
                AddVillageForm.prototype.addArmies = function () {
                    this.village.armies.push(new army_1.Army);
                    console.log(this.village);
                };
                AddVillageForm.prototype.onSubmit = function (village) {
                    alert(JSON.stringify(village));
                    this.player.villages.push(village);
                    this.villageService.add(village);
                    this.village = new village_1.Village;
                };
                return AddVillageForm;
            }());
            __decorate([
                core_1.Input(),
                __metadata("design:type", Object)
            ], AddVillageForm.prototype, "player", void 0);
            __decorate([
                core_1.Output(),
                __metadata("design:type", core_1.EventEmitter)
            ], AddVillageForm.prototype, "em", void 0);
            __decorate([
                core_1.ViewChild('heroForm'),
                __metadata("design:type", forms_1.NgForm)
            ], AddVillageForm.prototype, "currentForm", void 0);
            AddVillageForm = __decorate([
                core_1.Component({
                    selector: "add-vill-form",
                    template: "\n    <!---->\n    <!--<form class=\"col s12\">-->\n       <!--<div class=\"input-field col s6 offset-s3\">-->\n          <!--<input id=\"last_name\" type=\"text\" class=\"validate\">-->\n          <!--<label for=\"last_name\">Last Name</label>-->\n        <!--</div>-->\n\n    <!--</form>-->\n \n      <form #heroForm=\"ngForm\" (ngSubmit)=\"onSubmit(village)\" >\n  <div class=\"row container\">\n  <div class=\"input-field col s6 offset-s3\">\n          <input id=\"name\" type=\"text\" class=\"validate\" required [(ngModel)]=\"this.village.name\" name=\"name\" #name=\"ngModel\" minlength=\"3\" maxlength=\"14\" pattern=\"[A-z]*\">\n          <label for=\"name\">Name</label>\n          <div *ngIf=\"name.errors && (name.dirty|| x.touched)\"\n             class=\"alert alert-danger\">\n            <div [hidden]=\"!name.errors.required\">\n              Name is required\n            </div>\n            <div [hidden]=\"!name.errors.minlength\">\n             Name cannot be less than 3 characters long.\n            </div>\n            <div [hidden]=\"!name.errors.pattern\">\n             Name is incorrect!\n            </div>\n            <div [hidden]=\"!name.errors.maxlength\">\n              Name cannot be more than 14 characters long.\n            </div>\n        </div>\n         <!--<div *ngIf=\"formErrors.name\" class=\"alert alert-danger\">-->\n          <!--{{ formErrors.name }}-->\n        <!--</div>-->\n        </div>\n  \n  <div class=\"input-field col s6 offset-s3\">\n          <input id=\"x\" type=\"text\" class=\"validate\" [(ngModel)]=\"this.village.xCoord\" required name=\"x\" pattern=\"[0-9]*\" #x=\"ngModel\">\n          <label for=\"x\">X</label>\n          <div *ngIf=\"x.errors && (x.dirty || x.touched)\"\n             class=\"alert alert-danger\">\n            <div [hidden]=\"!x.errors.required\">\n              X coordinate is required\n            </div>\n        \n            <div [hidden]=\"!x.errors.pattern\">\n             Coordinates can contain chars only!\n            </div>\n            \n        </div>\n        </div>\n        <div class=\"input-field col s6 offset-s3\">\n          <input id=\"y\" type=\"text\" class=\"validate\" [(ngModel)]=\"village.yCoord\" name=\"y\" pattern=\"[0-9]*\" required #y=\"ngModel\">\n          <label for=\"y\">Y</label>\n          <div *ngIf=\"y.errors && (y.dirty || y.touched)\"\n             class=\"alert alert-danger\">\n            <div [hidden]=\"!y.errors.required\">\n              Y coordinate is required\n            </div>\n        \n            <div [hidden]=\"!y.errors.pattern\">\n             Coordinates can contain chars only!\n            </div>\n        </div>\n        </div>\n\n<div class=\"input-field col s6 offset-s3\">\n          <input id=\"population\" type=\"text\" class=\"validate\" required [(ngModel)]=\"village.population\"\n           name=\"population\" pattern=\"[0-9]*\" #population=\"ngModel\">\n          <label for=\"population\">Population</label>\n          <div *ngIf=\"population.errors && (population.dirty || population.touched)\"\n             class=\"alert alert-danger\">\n            <div [hidden]=\"!population.errors.required\">\n              Population  is required\n            </div>\n        \n            <div [hidden]=\"!population.errors.pattern\">\n             Population value can contain chars only!\n            </div>\n        </div>\n        </div>\n  \n<div class=\"input-field col s6 offset-s3\">\nIs capital?\n</div>\n<div class=\"switch \">\n    <label class=\"col offset-s3\">\n         No\n      <input type=\"checkbox\" [(ngModel)]=\"village.isCapital\" name=\"isCapital\">\n      <span class=\"lever\"></span>\n      Yes\n    </label>\n  </div>\n\n\n  <div class=\"input-field col s6 offset-s3\">\n               <a (click)=\"addArmies()\" class=\"btn-floating btn-large waves-effect waves-light red\"><i class=\"material-icons\">add</i></a>\n</div>\n  \n  <div add-army *ngFor=\"let army of village.armies\" [army]=\"army\"></div>\n  <div class=\"input-field col s6 offset-s3\">\n <button type=\"submit\" class=\"btn btn-default\"\n             [disabled]=\"!heroForm.form.valid\">Submit</button>\n</div>\n  </div>  \t\n  </form>\n"
                }),
                __metadata("design:paramtypes", [villageService_1.VillageService])
            ], AddVillageForm);
            exports_1("AddVillageForm", AddVillageForm);
        }
    };
});
//# sourceMappingURL=addVillageForm.js.map