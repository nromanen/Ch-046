/**
 * Created by okunetc on 19.01.2017.
 */
import {
    Component, Input, OnInit, EventEmitter, OnChanges, SimpleChanges,
    Output, DoCheck, group
} from "@angular/core";
import {Village} from "../village/village";
import {Army} from "./army";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";
import {UnitType} from "../UnitType/unitType";
import {Validators, FormBuilder, FormGroup, FormArray} from "@angular/forms";
@Component({
    selector: 'army-cell',
    outputs: ['cellClicked'],
    styleUrls:['components/army/army.css'],
    template: `
          <div class="cell" title="{{this.type}}" (dblclick)="hide()" *ngIf="!isInput" (click)="becomeDiv()">{{this.army.count==0?" ":this.army.count}}</div>
     <div class="input-field"  *ngIf="isInput" [formGroup]="group">
     <input class="validate"  type="text"  style=" width: 20px;" [ngModel]="this.army.count!=0?this.army.count:''" (keyup)="cancelEditing($event.keyCode)"
     formControlName="count" (ngModelChange)="addOrEdit($event)">
     </div>
`
})
export class ArmyCellComponent implements OnInit,OnChanges,DoCheck {
    ngDoCheck(): void {
    }
    @Input() type: string;
    @Input() village: Village;
    @Input() group:FormGroup;
    @Input() isInput: boolean;
    @Input() ifSave: boolean;
    @Input() armiesArrayControl:FormArray;
    @Input() index;
    army: Army;
    newArmy: Army;
    cellClicked: EventEmitter<Village>;
    @Output() cancelEdit:EventEmitter<Village>;

    constructor(private currVillageArmiesService: CurrVillageArmiesService, private _fBuilder:FormBuilder) {
        this.isInput = true;
        this.cellClicked = new EventEmitter<Village>();
        this.cancelEdit=new EventEmitter<Village>();

    }

    ngOnInit(): void {
        // this.armiesArrayControl[this.index]=this.initArmies(this.type);

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


        if (changes['village']){
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

        if (changes['isInput'] != null)
            // console.log(changes['armiesArrayControl']);
            // this.armiesArrayControl[this.index] = this.initArmies(this.type);
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