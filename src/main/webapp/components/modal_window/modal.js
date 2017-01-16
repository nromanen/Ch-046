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
    var core_1, ConfirmComponent;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            }
        ],
        execute: function () {
            ConfirmComponent = (function () {
                /**
                 * Created by rmochetc on 12.01.2017.
                 */
                function ConfirmComponent() {
                    this.notify = new core_1.EventEmitter();
                }
                // private ErrorMsg: string;
                // public ErrorMessageIsVisible: boolean;
                ConfirmComponent.prototype.showErrorMessage = function () {
                    console.log("show message");
                    // this.ErrorMsg = "TEST";
                    // this.ErrorMessageIsVisible = true;
                    // console.log(this.ErrorMessageIsVisible);
                };
                ConfirmComponent.prototype.onConfirm = function () {
                    this.notify.emit(true);
                };
                ConfirmComponent.prototype.onCancel = function () {
                    this.notify.emit(false);
                };
                return ConfirmComponent;
            }());
            __decorate([
                core_1.Input(),
                __metadata("design:type", String)
            ], ConfirmComponent.prototype, "confirmMsg", void 0);
            __decorate([
                core_1.Output(),
                __metadata("design:type", core_1.EventEmitter)
            ], ConfirmComponent.prototype, "notify", void 0);
            ConfirmComponent = __decorate([
                core_1.Component({
                    selector: 'app-modal',
                    //templateUrl: 'components/modal_window/modal.html',
                    template: "\n    <div id=\"dialogoverlay\"></div>\n<div id=\"dialogbox\">\n    <div>\n        <div id=\"dialogboxhead\">Confirm</div>\n        <div id=\"dialogboxbody\">{{confirmMsg}}</div>\n        <div id=\"dialogboxfoot\"><button class='btn btn-danger' (click) = \"onConfirm()\">Confirm</button>&nbsp;&nbsp;&nbsp;&nbsp;<button class='btn btn-success' (click) = \"onCancel()\">Cancel</button></div>\n    </div>\n</div>\n\n",
                    styles: ["#dialogoverlay{\n    display: block;\n    opacity: .8;\n    position: fixed;\n    top: 0px;\n    left: 0px;\n    background: black;\n    width: 100%;\n    height: 100%;\n    z-index: 10;\n}\n#dialogbox{\n    display: block;\n    position: absolute;\n    background: #fff;\n    border-radius:7px;\n    width:550px;\n    z-index: 10;\n     top: 20%;\n  left: 35%;\n}\n#dialogbox > div{ background: #d4e1d9; margin:8px; }\n#dialogbox > div > #dialogboxhead{ background: #fff; font-size:24px; padding:10px; color: #000000;  border-bottom: 1px solid lightgray;}\n#dialogbox > div > #dialogboxbody{ background: #fff; font-size:19px; padding:20px; color: #000000; border-bottom: 1px solid lightgray;}\n#dialogbox > div > #dialogboxfoot{ background: #fff; padding:10px; text-align:right; }"
                    ]
                })
            ], ConfirmComponent);
            exports_1("ConfirmComponent", ConfirmComponent);
        }
    };
});
//# sourceMappingURL=modal.js.map