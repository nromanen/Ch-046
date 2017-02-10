System.register(["rxjs/Rx", "@angular/core"], function (exports_1, context_1) {
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
    var Rx_1, core_1, TimerComponent;
    return {
        setters: [
            function (Rx_1_1) {
                Rx_1 = Rx_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            }
        ],
        execute: function () {
            TimerComponent = (function () {
                function TimerComponent() {
                }
                TimerComponent.prototype.dhms = function (time) {
                    var days, hours, minutes, seconds;
                    days = Math.floor(time / 86400);
                    time -= days * 86400;
                    hours = Math.floor(time / 3600) % 24;
                    time -= hours * 3600;
                    minutes = Math.floor(time / 60) % 60;
                    time -= minutes * 60;
                    seconds = time % 60;
                    return [
                        days + 'd',
                        hours + 'h',
                        minutes + 'm',
                        seconds + 's'
                    ].join(' ');
                };
                TimerComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    console.log("this.futureString");
                    console.log(this.futureString);
                    this.future = new Date(+this.futureString);
                    Rx_1.Observable.interval(1000).map(function (x) {
                        _this.diff = Math.floor((_this.future.getTime() - new Date().getTime()) / 1000);
                    }).subscribe(function (x) {
                        _this.message = _this.dhms(_this.diff);
                    });
                };
                return TimerComponent;
            }());
            __decorate([
                core_1.Input(),
                __metadata("design:type", String)
            ], TimerComponent.prototype, "futureString", void 0);
            TimerComponent = __decorate([
                core_1.Component({
                    selector: 'my-timer',
                    template: "<div>{{message}}</div>"
                })
            ], TimerComponent);
            exports_1("TimerComponent", TimerComponent);
        }
    };
});
//# sourceMappingURL=timer.component.js.map