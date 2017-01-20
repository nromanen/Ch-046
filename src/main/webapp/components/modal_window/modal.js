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
    var ConfirmComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            }],
        execute: function() {
            /**
             * Created by rmochetc on 12.01.2017.
             */
            ConfirmComponent = (function () {
                function ConfirmComponent() {
                    this.notify = new core_1.EventEmitter();
                }
                // private ErrorMsg: string;
                // public ErrorMessageIsVisible: boolean;
                ConfirmComponent.prototype.keyboardInput = function (event) {
                    if (event.key === "Escape") {
                        this.onCancel();
                    }
                };
                ConfirmComponent.prototype.onConfirm = function () {
                    this.notify.emit(true);
                };
                ConfirmComponent.prototype.onCancel = function () {
                    this.notify.emit(false);
                };
                __decorate([
                    core_1.Input(), 
                    __metadata('design:type', String)
                ], ConfirmComponent.prototype, "confirmMsg", void 0);
                __decorate([
                    core_1.Output(), 
                    __metadata('design:type', core_1.EventEmitter)
                ], ConfirmComponent.prototype, "notify", void 0);
                __decorate([
                    core_1.HostListener('window:keydown', ['$event']), 
                    __metadata('design:type', Function), 
                    __metadata('design:paramtypes', [Object]), 
                    __metadata('design:returntype', void 0)
                ], ConfirmComponent.prototype, "keyboardInput", null);
                ConfirmComponent = __decorate([
                    core_1.Component({
                        selector: 'app-modal',
                        templateUrl: 'components/modal_window/modal.html',
                        styleUrls: ['components/modal_window/modal.css']
                    }), 
                    __metadata('design:paramtypes', [])
                ], ConfirmComponent);
                return ConfirmComponent;
            }());
            exports_1("ConfirmComponent", ConfirmComponent);
        }
    }
});
//# sourceMappingURL=modal.js.map