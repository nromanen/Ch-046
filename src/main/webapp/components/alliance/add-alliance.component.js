System.register(["@angular/core", "./alliance", "@angular/forms"], function (exports_1, context_1) {
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
    var core_1, alliance_1, forms_1, AllianceForm;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (alliance_1_1) {
                alliance_1 = alliance_1_1;
            },
            function (forms_1_1) {
                forms_1 = forms_1_1;
            }
        ],
        execute: function () {
            AllianceForm = (function () {
                function AllianceForm(fb) {
                    this.fb = fb;
                    this.notify = new core_1.EventEmitter();
                    //Constants regex
                    this.EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
                    this.ALLIANCE_NAME = /^[a-z]{3,9}$/;
                    this.USER_LOGIN = /^[a-z1-9]{3,9}$/;
                    // Constants errors messages
                    this.EMAIL_ERROR = "Enter correct email, please!";
                    this.NAME_ERROR = "Enter from 3 to 10 letters";
                    this.LOGIN_ERROR = "Enter from 3 to 10 letters";
                    this.complexForm = fb.group({
                        'allianceName': [null, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern(this.ALLIANCE_NAME)])],
                        'leaderLogin': [null, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern(this.USER_LOGIN)])],
                        'leaderEmail': [null, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern(this.EMAIL_REGEXP)])]
                    });
                }
                AllianceForm.prototype.submitForm = function (value) {
                    console.log("Complex form: " + value);
                    console.log(value.allianceName);
                    var newAlliance = new alliance_1.Alliance(value.allianceName, value.leaderLogin, value.leaderEmail);
                    this.notify.emit(newAlliance);
                    this.complexForm.reset();
                };
                return AllianceForm;
            }());
            __decorate([
                core_1.Output(),
                __metadata("design:type", core_1.EventEmitter)
            ], AllianceForm.prototype, "notify", void 0);
            AllianceForm = __decorate([
                core_1.Component({
                    selector: 'add-alliance',
                    templateUrl: 'components/alliance/allianceForm.html',
                }),
                __metadata("design:paramtypes", [forms_1.FormBuilder])
            ], AllianceForm);
            exports_1("AllianceForm", AllianceForm);
        }
    };
});
//# sourceMappingURL=add-alliance.component.js.map