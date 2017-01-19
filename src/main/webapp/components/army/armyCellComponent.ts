/**
 * Created by okunetc on 19.01.2017.
 */
import {Component, Input, OnInit, EventEmitter} from "@angular/core";
import {Village} from "../village/village";
@Component({
      selector:'army-cell',
      outputs:['cellClicked'],
      template:`
{{diagnostic}}
     <div title="{{this.type}}" (dblclick)="hide()" *ngIf="isVisible">{{value}}</div>
     <input  type="text" *ngIf="!isVisible"  style="margin: 0;width: 20px;height: 22px" [(ngModel)]="this.value" name="value" #spy>
`
})
export class ArmyCellComponent implements OnInit{
    ngOnInit(): void {
        console.log(this.village.armies.length);
        this.village.armies.forEach( (army)=> {
            console.log(army.type);
            console.log(this.type.toString());
            console.log(this.type===army.type.toString());
            if(this.type==army.type.toString()){
                this.value=army.count;
            }
        });
    }
  @Input() type:string;
  @Input() village:Village;
  emitter:EventEmitter<Village>;
  @Input() isForm:boolean;
 value:number;
  constructor(){
      this.value=0;
      this.isForm=true;
      this.emitter=new EventEmitter<Village>();
  }

  hide(){
      this.isForm=false;
      // this.emitter.emit(this.village);
  }
}