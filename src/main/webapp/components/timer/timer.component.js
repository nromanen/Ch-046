System.register(['rxjs/Rx', "@angular/core"], function(exports_1, context_1) {
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
    var Rx_1, core_1;
    var TimerComponent;
    return {
        setters:[
            function (Rx_1_1) {
                Rx_1 = Rx_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            }],
        execute: function() {
            TimerComponent = (function () {
                function TimerComponent(elm) {
                    this.futureString = elm.nativeElement.getAttribute('inputDate');
                }
                TimerComponent.prototype.dhms = function (t) {
                    var days, hours, minutes, seconds;
                    days = Math.floor(t / 86400);
                    t -= days * 86400;
                    hours = Math.floor(t / 3600) % 24;
                    t -= hours * 3600;
                    minutes = Math.floor(t / 60) % 60;
                    t -= minutes * 60;
                    seconds = t % 60;
                    return [
                        days + 'd',
                        hours + 'h',
                        minutes + 'm',
                        seconds + 's'
                    ].join(' ');
                };
                TimerComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    this.future = new Date(this.futureString);
                    Rx_1.Observable.interval(1000).map(function (x) {
                        _this.diff = Math.floor((_this.future.getTime() - new Date().getTime()) / 1000);
                    }).subscribe(function (x) {
                        _this.message = _this.dhms(_this.diff);
                    });
                };
                TimerComponent = __decorate([
                    core_1.Component({
                        selector: 'my-timer',
                        template: "\n  <div>\n    {{message}}\n  </div>\n"
                    }), 
                    __metadata('design:paramtypes', [core_1.ElementRef])
                ], TimerComponent);
                return TimerComponent;
            }());
            exports_1("TimerComponent", TimerComponent);
        }
    }
});
//# sourceMappingURL=timer.component.js.map