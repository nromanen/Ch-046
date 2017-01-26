/**
 * Created by rmochetc on 22.01.2017.
 */
System.register(["./help.service", "@angular/core", "@angular/forms", "./attack", "./stomp.service"], function (exports_1, context_1) {
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
    var help_service_1, core_1, forms_1, attack_1, stomp_service_1, HelpComponent;
    return {
        setters: [
            function (help_service_1_1) {
                help_service_1 = help_service_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (forms_1_1) {
                forms_1 = forms_1_1;
            },
            function (attack_1_1) {
                attack_1 = attack_1_1;
            },
            function (stomp_service_1_1) {
                stomp_service_1 = stomp_service_1_1;
            }
        ],
        execute: function () {/**
             * Created by rmochetc on 22.01.2017.
             */
            HelpComponent = (function () {
                function HelpComponent(helpService, formBuilder, stompService) {
                    this.helpService = helpService;
                    this.formBuilder = formBuilder;
                    this.stompService = stompService;
                    // EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
                    // ALLIANCE_NAME = /^[a-z]{3,9}$/;
                    this.VILLAGE = /^[A-Za-z1-9.]{3,9}$/;
                    this.DATE = /^[1-9/-0]{3,20}/;
                    this.ENEMY = "Enter correct email, please!";
                    this.NAME_ERROR = "Enter from 3 to 10 letters";
                    this.LOGIN_ERROR = "Enter from 3 to 10 letters";
                    this.successMessage = null;
                    this.errorMessage = null;
                    this.helpForm = this.formBuilder.group({
                        'villageName': ['', forms_1.Validators.compose([forms_1.Validators.required])],
                        'enemy': ['', forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern(this.VILLAGE)])],
                        'timeAttack': ['', forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern(this.DATE)])]
                    });
                }
                HelpComponent.prototype.ngOnInit = function () {
                    this.getPlayer();
                };
                HelpComponent.prototype.getPlayer = function () {
                    var _this = this;
                    this.helpService.getById()
                        .subscribe(function (resp) {
                        _this.player = resp;
                        _this.villages = _this.player.villages;
                        console.log(_this.player);
                        console.log(_this.villages);
                    });
                };
                HelpComponent.prototype.submitForm = function (value) {
                    console.log("Complex form: " + value);
                    var newAttack = new attack_1.Attack(value.villageName, value.enemy, value.timeAttack);
                    this.send(newAttack);
                    this.helpForm.controls['villageName'].setValue("");
                    this.helpForm.controls['enemy'].setValue("");
                    this.helpForm.controls['timeAttack'].setValue("");
                };
                HelpComponent.prototype.send = function (attack) {
                    var _this = this;
                    this.helpService.addAttack(attack)
                        .subscribe(function (resp) {
                        _this.successMessage = "Ask help added successfully";
                        _this.errorMessage = null;
                        _this.stompService.send(_this.player.login);
                    }, function (error) {
                        _this.errorMessage = error;
                        _this.successMessage = null;
                    });
                };
                HelpComponent.prototype.closeSuccess = function () {
                    this.successMessage = null;
                };
                HelpComponent.prototype.closeError = function () {
                    this.errorMessage = null;
                };
                return HelpComponent;
            }());
            HelpComponent = __decorate([
                core_1.Component({
                    selector: 'ask-help',
                    templateUrl: 'components/help/askHelp.html'
                }),
                __metadata("design:paramtypes", [help_service_1.HelpService, forms_1.FormBuilder, stomp_service_1.StompService])
            ], HelpComponent);
            exports_1("HelpComponent", HelpComponent);
        }
    };
});
//# sourceMappingURL=ask-help.component.js.map