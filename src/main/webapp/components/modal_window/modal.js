/**
 * Created by rmochetc on 12.01.2017.
 */
System.register(['angular2/core'], function(exports_1, context_1) {
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
    var ErrorMessage;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            }],
        execute: function() {
            ErrorMessage = (function () {
                function ErrorMessage() {
                }
                ErrorMessage.prototype.showErrorMessage = function (msg) {
                    console.log("show message");
                    console.log(msg);
                    this.ErrorMsg = msg;
                    this.ErrorMessageIsVisible = true;
                };
                ErrorMessage.prototype.hideErrorMsg = function () {
                    this.ErrorMessageIsVisible = false;
                };
                ErrorMessage = __decorate([
                    core_1.Component({
                        selector: 'app-modal',
                        //templateUrl: 'components/modal_window/modal.html',
                        template: "\n\n<!-- Modal Structure -->\n<div *ngIf=\"ErrorMessageIsVisible\" style=\"z-index: 999;display: block;opacity: 0.5;position: fixed; top: -100px; left: 0; bottom: 0; right: 0; height: 125%; width: 100%; background: #000;\">\n    <div style=\"\n    z-index: 1003;\n    color: black;\n    display: block;\n    opacity: 1;\n    transform: scaleX(1);\n    top: 10%;\n    position: fixed;\n    left: 0;\n    right: 0;\n    background-color: #fafafa;\n    padding: 0;\n    max-height: 70%;\n    width: 55%;\n    margin: auto;\n    overflow-y: auto;\n    border-radius: 2px;\n    box-shadow: 0 8px 10px 1px rgba(0,0,0,0.14), 0 3px 14px 2px rgba(0,0,0,0.12), 0 5px 5px -3px rgba(0,0,0,0.3);\">\n <div style=\"padding: 24px; opacity: 1;\n    box-sizing: inherit;\n    display: block;\">\n        <h4 style=\"    margin-top: 0;\n    font-size: 2.28rem;\n    line-height: 110%;\n    margin: 1.14rem 0 0.912rem 0;\n    font-weight: 400;\n    box-sizing: inherit;\n    display: block;\">Confinm this action</h4>\n        <p>{{ErrorMsg}}</p>\n    </div>\n    <div class=\"modal-footer\">\n        <a href=\"#!\" (click)=\"hideErrorMsg()\" class=\" modal-action modal-close waves-effect waves-green btn-flat\" style=\"float: right; margin: 6px 0; opacity: 1;\">Agree</a>\n        <a href=\"#!\" (click)=\"hideErrorMsg()\" class=\" modal-action modal-close waves-effect waves-green btn-flat\" style=\"float: right; margin: 6px 0; opacity: 1;\">Disagree</a>\n    </div>\n    </div>\n</div>\n\n\n"
                    }), 
                    __metadata('design:paramtypes', [])
                ], ErrorMessage);
                return ErrorMessage;
            }());
            exports_1("ErrorMessage", ErrorMessage);
        }
    }
});
//# sourceMappingURL=modal.js.map