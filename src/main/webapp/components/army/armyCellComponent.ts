/**
 * Created by okunetc on 19.01.2017.
 */
import {
    Component, Input, OnInit, EventEmitter, OnChanges, SimpleChanges,
    Output
} from "@angular/core";
import {Village} from "../village/village";
import {Army} from "./army";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";
import {UnitType} from "../UnitType/unitType";
@Component({
    selector: 'army-cell',
    outputs: ['cellClicked'],
    styleUrls:['css/army.css'],
//     styles:[`
//         div {
//             text-align: center;
// }
//     `],
    template: `
     <div title="{{this.type}}" (dblclick)="hide()" *ngIf="!isInput" (click)="becomeDiv()">{{army.count!=-1?this.army.count:"-1"}}</div>
     <div class="input-field"  *ngIf="isInput" [formGroup]="group">
     <input class="validate"  type="text"  style=" width: 20px;height: 22px" [ngModel]="this.army.count!=-1?this.army.count:0" (keyup)="cancelEditing($event.keyCode)"
     formControlName="value" (ngModelChange)="addOrEdit($event)">
     </div>
`
})
export class ArmyCellComponent implements OnInit,OnChanges {
    @Input() type: string;
    @Input() village: Village;
    @Input() group;
    @Input() isInput: boolean;
    @Input() ifSave: boolean;
    army: Army;
    newArmy: Army;
    cellClicked: EventEmitter<Village>;
    @Output() cancelEdit:EventEmitter<Village>;

    constructor(private currVillageArmiesService: CurrVillageArmiesService) {
        this.isInput = true;
        this.cellClicked = new EventEmitter<Village>();
        this.cancelEdit=new EventEmitter<Village>();

    }

    ngOnInit(): void {
        this.army=new Army(-1,UnitType[this.type],true);
        this.army.type=UnitType[this.type];

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

    ngOnChanges(changes: SimpleChanges): void {
        if (this.ifSave && this.army != null) {
            this.army.count = this.newArmy.count;
        }

        if (changes['isInput'] != null)
            if (changes['isInput'].currentValue === true && this.army.count != -1) {
                this.currVillageArmiesService.armies.push(this.newArmy);
            }
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
            if (this.currVillageArmiesService.armies.indexOf(this.newArmy)==-1 && this.newArmy.count>0)
                this.currVillageArmiesService.armies.push(this.newArmy);
        } else
            this.newArmy.count=event;
    }

    cancelEditing(event){
        if (event===27)
        this.cancelEdit.emit(null);

    }

}