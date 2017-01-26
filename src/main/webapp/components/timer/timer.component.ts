/**
 * Created by rmochetc on 19.01.2017.
 */
import {Observable} from 'rxjs/Rx';
import {Component, OnInit, ElementRef, Input} from "@angular/core";

@Component({
    selector: 'my-timer',
    template: `
  <div>
    {{message}}
  </div>
`
})
export class TimerComponent implements OnInit {

    private future:Date;
    private diff:number;
    private message: any;

    @Input() futureString: string;

    dhms(t){
        let days, hours, minutes, seconds;
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