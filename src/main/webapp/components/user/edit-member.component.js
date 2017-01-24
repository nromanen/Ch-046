System.register(["@angular/core", "@angular/forms", "./user"], function(exports_1, context_1) {
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
    var core_1, forms_1, user_1;
    var EditMemberComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (forms_1_1) {
                forms_1 = forms_1_1;
            },
            function (user_1_1) {
                user_1 = user_1_1;
            }],
        execute: function() {
            /**
             * Created by rmochetc on 14.01.2017.
             */
            EditMemberComponent = (function () {
                function EditMemberComponent(fb) {
                    this.fb = fb;
                    this.editMemberForm = new core_1.EventEmitter();
                    this.USER_LOGIN = /^[a-z1-9]{3,9}$/;
                    this.EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?\.[a-z0-9]{2,}$/i;
                    this.LOGIN_ERROR = "Enter from 3 to 10 letters";
                    this.EMAIL_ERROR = "Enter correct email, please!";
                    console.log("EditMemberComponent.constructor() is working");
                }
                EditMemberComponent.prototype.ngOnInit = function () {
                    console.log("EditMemberComponent.ngOnInit() is working");
                    this.memberForm = this.fb.group({
                        'login': [this.editedMember.login, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern(this.USER_LOGIN)])],
                        'email': [this.editedMember.email, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern(this.EMAIL_REGEXP)])],
                    });
                };
                EditMemberComponent.prototype.updateMember = function (value) {
                    console.log("EditMemberComponent.updateMember() is working. Member value is: " + JSON.stringify(value));
                    if (this.hasMemberDataChange(value)) {
                        var member = new user_1.User(value.login, value.email, this.editedMember.uuid, this.editedMember.alliance);
                        this.editMemberForm.emit(member);
                    }
                    else {
                        this.editMemberForm.emit(null);
                    }
                    this.memberForm.reset();
                };
                EditMemberComponent.prototype.hasMemberDataChange = function (value) {
                    return value.login != this.editedMember.login || value.email != this.editedMember.email;
                };
                EditMemberComponent.prototype.cancelEditing = function () {
                    console.log("EditMemberComponent.cancelEditing() is working");
                    this.editMemberForm.emit(null);
                };
                __decorate([
                    core_1.Input(), 
                    __metadata('design:type', user_1.User)
                ], EditMemberComponent.prototype, "editedMember", void 0);
                __decorate([
                    core_1.Output(), 
                    __metadata('design:type', core_1.EventEmitter)
                ], EditMemberComponent.prototype, "editMemberForm", void 0);
                EditMemberComponent = __decorate([
                    core_1.Component({
                        selector: 'edit-member',
                        templateUrl: 'components/user/edit-member-form.html',
                    }), 
                    __metadata('design:paramtypes', [forms_1.FormBuilder])
                ], EditMemberComponent);
                return EditMemberComponent;
            }());
            exports_1("EditMemberComponent", EditMemberComponent);
        }
    }
});
//# sourceMappingURL=edit-member.component.js.map