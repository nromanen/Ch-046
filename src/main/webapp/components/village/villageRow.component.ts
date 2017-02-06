/**
 * Created by okunetc on 19.01.2017.
 */
import {
    Component, Input, EventEmitter, OnInit, ChangeDetectorRef,
    AfterViewInit, Output
} from "@angular/core";
import {UnitType} from "../UnitType/unitType";
import {Village} from "./village";
import {VillageService} from "../services/villageService";
import {Army} from "../army/army";
import {FormControl, Validators, FormArray, FormBuilder, AbstractControl} from "@angular/forms";
import {AddVillageForm} from "./addVillageForm";
import {el} from "@angular/platform-browser/testing/browser_util";
@Component({
    selector: '[player-ro]',
    outputs:['selectedVillageChanged'],
    styleUrls:['styles/style.css'],
    template:`
<td [formGroup]="editVillageForm" >
    <div *ngIf="!isForm" class="ss1">{{v.name}}</div>
    <input *ngIf="isForm" type="text" formControlName="name" (keyup)="cancelEditingInRow($event.keyCode)">
    </td>
  
<td [formGroup]="editVillageForm">
    <div *ngIf="!isForm">{{v.population}}</div>
    <input *ngIf="isForm"  type="text"  formControlName="population">
</td>
<td [formGroup]="editVillageForm">
    <div *ngIf="!isForm" >{{this.v.xCoord}}</div>
    <input  *ngIf="isForm" type="text"  formControlName="xCoord">
</td>
<td [formGroup]="editVillageForm">
    <div *ngIf="!isForm">{{v.yCoord}}</div>
    <input *ngIf="isForm" type="text"  formControlName="yCoord" >
</td>
<td [formGroup]="editVillageForm">
    <div *ngIf="!isForm">
    <i *ngIf="v.isCapital" class="small material-icons check">done</i>
    </div>
    <input *ngIf="isForm" type="checkbox" formControlName="isCapital" id="isCapital" class="filled-in">
    <label *ngIf="isForm" for="isCapital"></label>
</td>

<td [formGroup]="editVillageForm">
<div *ngIf="!isForm">{{v.wall}}</div>
<input *ngIf="isForm" type="text" formControlName="wall">
</td>

<td *ngFor="let tp of unitValues; let i=index;" >
    <army-cell [type]="tp" [village]="v" [group]="editVillageForm.controls.armies?editVillageForm.controls.armies.controls[i]:null"
    (cancelEdit)="cancelEditing($event)" [armiesArrayControl]="armiesArrayControl" [index]="i"
    (cellClicked)="cellClick($event)" [isInput]="isForm" [ifSave]="ifSaveChanges"></army-cell>
</td>
<td>
    <button (click)="!isForm?showEdit():changeVillage()" type="button"
    class="btn waves-effect waves-light col offset-s3"  name="action" 
    [disabled]="!editVillageForm.valid && isForm" style="margin-top: 5px;" >
            {{!isForm?"Edit":"Save"}}      
    </button>
</td>

`
})
export class VillageRow implements OnInit,AfterViewInit{
    POPULATION_REGEXP=/^\d*$/;
    COORD_REGEXP=/^(-)?[0-9]*$/;
    @Input() v:Village;
    @Input() isForm:boolean;
    @Input() editVillageForm;
    @Output() cancelEdit:EventEmitter<Village>;
    @Output() errorMessage:EventEmitter<{}>;
    @Output() successMessage:EventEmitter<string>;
    @Output() editedVillage;
    unitValues:Array<string>;
    selectedVillageChanged:EventEmitter<Village>;
    ifSaveChanges:boolean;
    private cdr: ChangeDetectorRef;
    private armiesArrayControl: FormArray;

    constructor( private villageService:VillageService,cdr: ChangeDetectorRef,
                 private _fBuilder:FormBuilder){
        this.selectedVillageChanged=new EventEmitter<Village>(false);
        this.unitValues=[];
        this.ifSaveChanges=false;
        this.cdr = cdr;
        this.cancelEdit=new EventEmitter<Village>();
        this.errorMessage=new EventEmitter<{}>();
        this.successMessage=new EventEmitter<string>();
        this.editedVillage=new EventEmitter<Village>();
    }

    ngOnInit(): void {
        this.getStringUnitTypeValues();
        // this.buildForm();
    }

    ngAfterViewInit(): void {
        this.cdr.detectChanges();
    }

    getStringUnitTypeValues(){
        for (let m in UnitType){
            this.unitValues.push(m);
        }

    }

    cellClick(village:Village){
        this.selectedVillageChanged.emit(village);
    }

    changeVillage(){
        this.ifSaveChanges=true;
        let index:number=this.villageService.villages.indexOf(this.v);
        let newVillage=this.editVillageForm.value;
        // this.villageService.villages[index]=newVillage;
        console.log(newVillage);
        this.villageService.update(newVillage).subscribe(
            response=>{
                 this.villageService.villages[index]=response;
                this.successMessage.emit("Village has successfully been updated ");
                this.editedVillage.emit(response);
            },
            error=>{
                this.errorMessage.emit({error:error._body});
            }
        );
        // this.selectedVillageChanged.emit(new Village);
        this.ifSaveChanges=false;

    }

    showEdit(){
        this.selectedVillageChanged.emit(this.v);
        this.errorMessage.emit(null);
        this.buildForm();
        // this.cdr.detectChanges();
    }

    cancelEditing(){
        // this.isForm=false;
        this.selectedVillageChanged.emit(new Village);
        this.cdr.detectChanges();

    }

    buildForm(){
        this.editVillageForm=this._fBuilder.group({
            'uuid':[this.v.uuid,[]],
            'name':new FormControl(this.v.name,[Validators.required,Validators.pattern(AddVillageForm.VILLAGE_REGEXP)]),
            'population':[this.v.population,[Validators.required,Validators.pattern(this.POPULATION_REGEXP)]
            ],
            'xCoord': [this.v.xCoord,
                [Validators.required, this.forbiddenXValidator(),Validators.pattern(this.COORD_REGEXP)]
            ],
            'yCoord':[this.v.yCoord,
                [Validators.required,this.forbiddenXValidator(),Validators.pattern(this.COORD_REGEXP)]
            ],
            'wall':[this.v.wall,
                [Validators.required,Validators.pattern(this.POPULATION_REGEXP)]
            ],
            'isCapital':[this.v.isCapital],
            'armies':this._fBuilder.array([]),
        });
        this.armiesArrayControl = <FormArray>this.editVillageForm.controls['armies'];
        // for (let i=0; i<this.v.armies.length; i++) {
        //     this.armiesArrayControl.push(this.initArmies(this.v.armies[i]));
        // }

        for (let i=0; i<27; i++) {
            this.armiesArrayControl.push(this.initArmies(this.v.armies[i]));
        }

        this.editVillageForm.valueChanges
            .subscribe(data => this.onValueChanged());
        this.onValueChanged();
    }

    initArmies(army:Army) {
        return this._fBuilder.group({
            count:['',[Validators.pattern(this.POPULATION_REGEXP)]],
            type:[army.type,[] ],
            ownUnit:[army.ownUnit,[]],
            uuid:[army.uuid,[]]
        });
    }

    cancelEditingInRow(event){
        if (event===27){
            this.selectedVillageChanged.emit(new Village);
        }
    }

    forbiddenXValidator() {
        return (control: AbstractControl): {[key: string]: any} => {
            const coord = control.value;
            if (coord < -400 || coord > 400)
                return {'forbiddenCoordinate': {coord}};
            return null;
        };
    }

    onValueChanged(data?: any) {
        if (!this.editVillageForm) {
            return;
        }
        const form = this.editVillageForm;
        for (const field in this.formErrors) {
            // clear previous error message (if any)
            this.formErrors[field] = '';
            const control = form.get(field);
            if (control && control.dirty && !control.valid) {
                const messages = this.validationMessages[field];
                for (const key in control.errors) {
                    this.formErrors[field] += messages[key] + ' ';
                }
            }
        }


        // console.log(this.editVillageForm);
        let armiesControl:FormArray=form.get('armies');
        if (!armiesControl.valid) {
            this.formErrors['armies']='Count can contain numbers only!';
        }
        if (!this.editVillageForm.valid) {
            this.errorMessage.emit(this.formErrors);
        } else  this.errorMessage.emit(null);
    }

    formErrors = {
        'name': '',
        'xCoord': '',
        'yCoord':'',
        'population':'',
        'wall':'',
        'armies':'',
    };

    validationMessages = {
        'name': {
            'required': 'Name is required!',
            'minlength': 'Name must be at least 4 characters long!',
            'maxlength': 'Name cannot be more than 24 characters long!',
            'pattern':'Pattern!'
        },
        'xCoord': {
            'required': 'X  is required!',
            'forbiddenCoordinate': 'X can only range between -400 and 400!',
            'pattern':"X  can contain numbers only!"
        },
        'yCoord': {
            'required': 'Y coordinate is required.',
            'forbiddenCoordinate': 'Y can only range between -400 and 400!',
            'pattern':"Y  can contain numbers only!"
        },
        'population':{
            'required': 'Population is required.',
            'pattern':"Population can contain numbers only!"
        },
        'wall':{
            'required': 'Wall is required.',
            'pattern':"Wall level can contain numbers only!"
        },
        'armies':{
            'pattern':'Count can contain numbers only!'
        }
    };

}