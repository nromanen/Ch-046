/**
 * Created by okunetc on 19.01.2017.
 */
import {Component, Input, OnInit, EventEmitter, OnChanges, SimpleChanges} from "@angular/core";
import {Village} from "../village/village";
import {Army} from "./army";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";
import {UnitType} from "../UnitType/unitType";
@Component({
    selector: 'army-cell',
    outputs: ['cellClicked'],
    template: `
     <div title="{{this.type}}" (dblclick)="hide()" *ngIf="!isInput" (click)="becomeDiv()">{{army.count!=-1?this.army.count:"0"}}</div>
     <div class="input-field"  *ngIf="isInput">
     <input class="validate"  type="text"  style=" 
     width: 20px;height: 22px" [ngModel]="this.army.count!=-1?this.army.count:0" 
     name="value" (ngModelChange)="addOrEdit($event)"
     #name="ngModel">
     </div>

`
})
export class ArmyCellComponent implements OnInit,OnChanges {
    @Input() type: string;
    @Input() village: Village;
    army: Army;
    newArmy: Army;
    @Input() isInput: boolean;
    @Input() ifSave: boolean;
    cellClicked: EventEmitter<Village>;

    ngOnChanges(changes: SimpleChanges): void {
        if (this.ifSave && this.army != null) {
            this.army.count = this.newArmy.count;
        }

        if (changes['isInput'] != null)
            if (changes['isInput'].currentValue === true && this.army.count != -1) {
                this.currVillageArmiesService.armies.push(this.newArmy);
            }

    }

    ngOnInit(): void {
        this.army=new Army(-1,UnitType[this.type],true);
        this.army.type=UnitType[this.type];
        console.log('inside cell');
        console.log(UnitType[this.type]);
        this.newArmy = new Army(this.army.count, this.army.type, this.army.ownUnit);
        this.newArmy.type=this.army.type;
        this.newArmy.uuid = this.army.uuid;
        this.village.armies.forEach((army) => {
            if (this.type == army.type.toString() || UnitType[army.type] == this.type) {
                this.army = army;
                this.newArmy = new Army(this.army.count, this.army.type, this.army.ownUnit);
                this.newArmy.uuid = this.army.uuid;
            }
        });
    }

    constructor(private currVillageArmiesService: CurrVillageArmiesService) {
        this.isInput = true;
        this.cellClicked = new EventEmitter<Village>();

    }

    hide() {
        this.isInput = true;
        this.cellClicked.emit(this.village);
    }

    becomeDiv() {
        this.cellClicked.emit(null);
    }

    addOrEdit(event:any){
        this.newArmy.count=event;
        if (this.army.count==-1){
            if (this.currVillageArmiesService.armies.indexOf(this.newArmy)==-1)
                this.currVillageArmiesService.armies.push(this.newArmy);
        } else
            this.newArmy.count=event;
    }

}