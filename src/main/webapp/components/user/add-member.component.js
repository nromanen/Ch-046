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
    var MemberForm;
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
            MemberForm = (function () {
                function MemberForm(fb) {
                    this.fb = fb;
                    this.addMemberForm = new core_1.EventEmitter();
                    this.USER_LOGIN = /^[a-z1-9]{3,9}$/;
                    this.EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
                    this.LOGIN_ERROR = "Login should be from 3 to 10 letters";
                    this.EMAIL_ERROR = "Enter correct email, please!";
                    console.log("MemberForm.constructor() is working");
                    this.memberForm = fb.group({
                        'uuid': [''],
                        'login': ['', forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern(this.USER_LOGIN)])],
                        'email': ['', forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern(this.EMAIL_REGEXP)])],
                        'alliance': [''],
                        'leader': ['']
                    });
                }
                MemberForm.prototype.addMember = function (value) {
                    console.log("MemberForm.addMember() method is working");
                    console.log("Form value is: " + JSON.stringify(value));
                    var member = new user_1.User(value.login, value.email, value.uuid, value.alliance, value.leader);
                    this.addMemberForm.emit(member);
                    this.memberForm.reset();
                };
                __decorate([
                    core_1.Output(), 
                    __metadata('design:type', core_1.EventEmitter)
                ], MemberForm.prototype, "addMemberForm", void 0);
                MemberForm = __decorate([
                    core_1.Component({
                        selector: 'add-member',
                        templateUrl: 'components/user/add-member-form.html',
                    }), 
                    __metadata('design:paramtypes', [forms_1.FormBuilder])
                ], MemberForm);
                return MemberForm;
            }());
            exports_1("MemberForm", MemberForm);
        }
    }
});
//# sourceMappingURL=add-member.component.js.map