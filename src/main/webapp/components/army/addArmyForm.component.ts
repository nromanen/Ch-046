/**
 * Created by okunetc on 23.01.2017.
 */
import {
    Component, Input, OnInit, AfterViewChecked, Output, EventEmitter, ViewChild, OnChanges,
    SimpleChanges
} from "@angular/core";
import {Army} from "./army";
import {UnitType} from "../UnitType/unitType";
import {Village} from "../village/village";
import {NgForm, FormGroup, FormArray} from "@angular/forms";
@Component({
    selector: "[add-army]",
    templateUrl: "components/army/addArmyForm.html",
    styleUrls: ['styles/style.css']
})
export class AddArmyForm implements AfterViewChecked,OnInit,OnChanges {


    ngOnInit(): void {
        // this.initUnitTypeStringsCopy();
    }

    @Input() group: FormGroup;
    @Input() army: Army;
    @Input() village: Village;
    @Output() armyIsValid: EventEmitter<boolean>;
    unitTypes: Array<string>;
    @Input() index: number;
    @Input() unitTypeStrings: Array<string>;
    @Input() armiesControl: FormArray;
    @Output() newUnitTypeStrings: EventEmitter<Array<string>>;
    allUnitTypes;
    @ViewChild('selectedType') selectedType;
    unitTypeStringsCopy;
    prevType: string;

    constructor() {
        this.unitTypes = [];
        this.armyIsValid = new EventEmitter<boolean>();
        this.unitTypeStrings = [];
        this.unitTypeStringsCopy = [];
        this.newUnitTypeStrings = new EventEmitter<string[]>();
        // this.initAllUnitTypes();
        this.allUnitTypes=[];
        this.initAllUnitTypes();
    }

    ngOnChanges(changes: SimpleChanges): void {
        let newUnittypesstr=[];
        if (changes['unitTypeStrings']){
            this.unitTypeStringsCopy=[];
            this.initUnitTypeStringsCopy();
            if (this.prevType!=null) {
                this.unitTypeStringsCopy.splice(this.getIndexOfType(this.prevType),0,this.prevType);
            }
            this.selectedType.value=this.prevType;
        }

    }

    ngAfterViewChecked(): void {
        this.armyIsValid.emit(true);
    }


    removeArmy() {

        this.newUnitTypeStrings.emit(this.unitTypeStringsCopy);
        this.armiesControl.controls.splice(this.index, 1);
    }

    initUnitTypeStringsCopy() {
        this.unitTypeStrings.forEach(type => {
            this.unitTypeStringsCopy.push(type);
        })
    }

    removeType(value) {
        let newUnittypesstr = [];
        this.unitTypeStrings.forEach(type => {
            newUnittypesstr.push(type);
        });
        if (this.prevType!=null){
            newUnittypesstr.push(this.prevType);
        }
        for (let i = 0; i < this.unitTypeStrings.length; i++) {
            let type: string = this.unitTypeStrings[i];
            if (type === value) {
                newUnittypesstr.splice(i, 1);
                this.prevType = value;
                break;
            }
        }

       this.prevType=value;
        this.newUnitTypeStrings.emit(newUnittypesstr);
        console.log(this.unitTypeStrings);
    }

    initAllUnitTypes(){
        for(let type in UnitType){
            this.allUnitTypes.push(type);
        }
    }

    getIndexOfType(type:string){
        for (let i=0;i<this.allUnitTypes.length;i++){
            let typeI=this.allUnitTypes[i];
            if (typeI==type){
                return i;
            }
        }
        return -1;
    }


}