System.register(["@angular/core", "./alliance", "@angular/forms", "../services/alliance-service"], function(exports_1, context_1) {
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
    var core_1, alliance_1, forms_1, alliance_service_1;
    var EditAllianceComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (alliance_1_1) {
                alliance_1 = alliance_1_1;
            },
            function (forms_1_1) {
                forms_1 = forms_1_1;
            },
            function (alliance_service_1_1) {
                alliance_service_1 = alliance_service_1_1;
            }],
        execute: function() {
            /**
             * Created by rmochetc on 14.01.2017.
             */
            EditAllianceComponent = (function () {
                function EditAllianceComponent(fb, _allianceService) {
                    this.fb = fb;
                    this._allianceService = _allianceService;
                    this.notify = new core_1.EventEmitter();
                    this.EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
                    this.ALLIANCE_NAME = /^[a-z]{3,9}$/;
                    this.USER_LOGIN = /^[a-z1-9]{3,9}$/;
                    this.EMAIL_ERROR = "Enter correct email, please!";
                    this.NAME_ERROR = "Enter from 3 to 10 letters";
                    this.LOGIN_ERROR = "Enter from 3 to 10 letters";
                }
                EditAllianceComponent.prototype.ngOnInit = function () {
                    this.editForm = this.fb.group({
                        'allianceName': [this.editedAlliance.name, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern(this.ALLIANCE_NAME)])],
                        'leaderLogin': [this.editedAlliance.leaderLogin, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern(this.USER_LOGIN)])],
                        'leaderEmail': [this.editedAlliance.leaderEmail, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern(this.EMAIL_REGEXP)])]
                    });
                };
                EditAllianceComponent.prototype.updateAlliance = function (value) {
                    console.log("Edit form: " + value);
                    var updatedAlliance = new alliance_1.Alliance(value.allianceName, value.leaderLogin, value.leaderEmail);
                    updatedAlliance.uuid = this.editedAlliance.uuid;
                    console.log(updatedAlliance);
                    this._allianceService.updateAlliance(this.editedAlliance, updatedAlliance);
                    //
                    // this.editForm.setValue({
                    //     allianceName : null,
                    //     leaderLogin : null,
                    //     leaderEmail : null
                    // });
                    // for (let name in this.editForm.controls) {
                    //     this.editForm.controls[name].setErrors(null);
                    // }
                    this.notify.emit(null);
                };
                EditAllianceComponent.prototype.cancelEditing = function () {
                    this.notify.emit(null);
                };
                __decorate([
                    core_1.Input(), 
                    __metadata('design:type', alliance_1.Alliance)
                ], EditAllianceComponent.prototype, "editedAlliance", void 0);
                __decorate([
                    core_1.Output(), 
                    __metadata('design:type', (typeof (_a = typeof core_1.EventEmitter !== 'undefined' && core_1.EventEmitter) === 'function' && _a) || Object)
                ], EditAllianceComponent.prototype, "notify", void 0);
                EditAllianceComponent = __decorate([
                    core_1.Component({
                        selector: 'edit-alliance',
                        templateUrl: 'components/alliance/editAlliance.html',
                    }), 
                    __metadata('design:paramtypes', [(typeof (_b = typeof forms_1.FormBuilder !== 'undefined' && forms_1.FormBuilder) === 'function' && _b) || Object, alliance_service_1.AllianceService])
                ], EditAllianceComponent);
                return EditAllianceComponent;
                var _a, _b;
            }());
            exports_1("EditAllianceComponent", EditAllianceComponent);
        }
    }
});
//# sourceMappingURL=editalliance.component.js.map