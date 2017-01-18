import {Component, Input, OnInit, EventEmitter} from "@angular/core";
import {Village} from "../village/village";
/**
 * Created by Oleg on 14.01.2017.
 */

@Component
({
    selector:'player-row',
    outputs:['editClick'],
    template:`
             <a (click)="edit()" routerLink="/edit"><button class="btn waves-effect waves-light col offset-s1 " type="submit" name="action" style="margin-top: 5px;"
        >Edit</button></a>
`
})
export class PlayerRow implements OnInit{
    ngOnInit(): void {
        console.log(this.v);
    }
    @Input() v:Village;
    editClick:EventEmitter<Village>;
    edit(){
        console.log('clicked');
        this.editClick.emit(this.v);
    }

    constructor(){
        this.editClick=new EventEmitter<Village>();
    }
}