/**
 * Created by okunetc on 19.01.2017.
 */
import {
    Component, Input, OnInit, EventEmitter, OnChanges, SimpleChanges,
    Output, DoCheck, group
} from "@angular/core";
import {Village} from "../village/village";
import {Army} from "./army";
import {UnitType} from "../UnitType/unitType";
import {FormBuilder, FormGroup, FormArray} from "@angular/forms";
@Component({
    selector: 'army-cell',
    outputs: ['cellClicked'],
    styleUrls: ['components/army/army.css'],
    template: `
          <div class="cell" title="{{this.type}}" *ngIf="!isInput" 
          [ngStyle]="this.army.count==0||army.count==null?{'visibility':'hidden'}:{'visibility':'visible'}">
          {{this.army.count==0||army.count==null?"m":this.army.count}}</div>
     <div class="input-field"  *ngIf="isInput" [formGroup]="group">
     <input class="validate"  type="text" [ngModel]="this.army.count!=0?this.army.count:''" (keyup)="cancelEditing($event.keyCode)"
     formControlName="count" >
     </div>
  
`
})
export class ArmyCellComponent implements OnInit,OnChanges {
    @Input() type: string;
    @Input() village: Village;
    @Input() group: FormGroup;
    @Input() isInput: boolean;
    @Input() ifSave: boolean;
    @Input() armiesArrayControl: FormArray;
    @Input() index;
    army: Army;
    newArmy: Army;
    cellClicked: EventEmitter<Village>;
    @Output() cancelEdit: EventEmitter<Village>;

    constructor( private _fBuilder: FormBuilder) {
        this.isInput = true;
        this.cellClicked = new EventEmitter<Village>();
        this.cancelEdit = new EventEmitter<Village>();

    }

    ngOnInit(): void {
        this.army = new Army(-1, UnitType[this.type], true);
        this.army.type = UnitType[this.type];
        this.newArmy = new Army(this.army.count, this.army.type, this.army.ownUnit);
        this.newArmy.type = this.army.type;
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
        if (changes['village']) {
            this.army = new Army(-1, UnitType[this.type], true);
            this.army.type = UnitType[this.type];
            this.newArmy = new Army(this.army.count, this.army.type, this.army.ownUnit);
            this.newArmy.type = this.army.type;
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
            if (changes['isInput'].currentValue === true && this.army.count != -1) {

            }
    }

    cancelEditing(event) {
        if (event === 27)
            this.cancelEdit.emit(null);

    }

}