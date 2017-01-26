/**
 * Created by rmochetc on 19.01.2017.
 */
import {Observable} from 'rxjs/Rx';
import {Component, OnInit, Input} from "@angular/core";

@Component({
    selector: 'my-timer',
    template: `<div>{{message}}</div>`
})
export class TimerComponent implements OnInit {

    private future:Date;
    private diff:number;
    private message: any;

    @Input() futureString: string;

    dhms(time){
        let days, hours, minutes, seconds;
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
    }

    ngOnInit() {
        console.log("this.futureString");
        console.log(this.futureString);
        this.future = new Date(+this.futureString);
        Observable.interval(1000).map((x) => {
            this.diff = Math.floor((this.future.getTime() - new Date().getTime()) / 1000);
        }).subscribe((x) => {
            this.message = this.dhms(this.diff);
        });
    }
}