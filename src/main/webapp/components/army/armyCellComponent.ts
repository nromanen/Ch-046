/**
 * Created by okunetc on 19.01.2017.
 */
import {Component, Input, OnInit, EventEmitter, OnChanges, SimpleChanges} from "@angular/core";
import {Village} from "../village/village";
import {Army} from "./army";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";
import {isUndefined} from "util";
import {UnitType} from "../UnitType/unitType";
@Component({
      selector:'army-cell',
      outputs:['cellClicked'],
      template:`
     <div title="{{this.type}}" (dblclick)="hide()" *ngIf="!isInput" (click)="becomeDiv()">{{army!=null?this.army.count:"0"}}</div>
     <div class="input-field"  *ngIf="isInput">
     <input class="validate"  type="text"  style=" 
     width: 20px;height: 22px" [ngModel]="army!=null?this.army.count:'0'" 
     name="value" (ngModelChange)="newArmy.count=$event"
     minlength="5" maxlength="7" #name="ngModel">
     </div>

`
})
export class ArmyCellComponent implements OnInit,OnChanges{
    ngOnChanges(changes: SimpleChanges): void {
        if (this.ifSave && this.army!=null){
            this.army.count=this.newArmy.count;
            console.log(this.village.armies);
        }

        if (changes['isInput']!=null)
        if (changes['isInput'].currentValue===true && this.army!=null){
            console.log('isInput');
            this.currVillageArmiesService.armies.push(this.newArmy);
            // console.log(this.newArmy);
            // console.log(this.currVillageArmiesService.armies);
            // alert(JSON.stringify(this.army));
            // alert(JSON.stringify(this.newArmy));

        }

    }
    ngOnInit(): void {
        this.village.armies.forEach( (army)=> {
            // console.log(UnitType[army.type]);
            // console.log(army.type);
            // console.log(this.type);
            if(this.type==army.type.toString()|| UnitType[army.type]==this.type){
                // alert(this.type==army.type.toString());
                this.army=army;
                this.newArmy=new Army(this.army.count,this.army.type,this.army.ownUnit);
                this.newArmy.uuid=this.army.uuid;
                // alert(JSON.stringify(this.army));
                // this.newArmy.count=this.army.count;
                // this.currVillageArmiesService.armies.push(this.newArmy);
            }
        });
        // console.log(this.currVillageArmiesService.armies);
    }
  @Input() type:string;
  @Input() village:Village;
  army:Army;
  newArmy:Army;
  @Input() isInput:boolean;
  @Input() ifSave:boolean;
  cellClicked:EventEmitter<Village>;
  constructor(private currVillageArmiesService:CurrVillageArmiesService){
      this.isInput=true;
      this.cellClicked=new EventEmitter<Village>();
      // this.newArmy=new Army();

  }

  hide(){
      this.isInput=true;
      this.cellClicked.emit(this.village);
  }

    becomeDiv(){
      this.cellClicked.emit(null);
    }

}