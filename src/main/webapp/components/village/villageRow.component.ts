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
import {FormControl, Validators, FormArray, FormBuilder, AbstractControl, FormGroup} from "@angular/forms";
import {AddVillageForm} from "./addVillageForm";
import {TranslateService} from "ng2-translate";
import {moreThanZeroValidator} from "../validators/validators";



@Component({
    selector: '[village-row]',
    outputs:['selectedVillageChanged'],
    styleUrls:['styles/style.css'],
    template:`
<td [formGroup]="editVillageForm" >
    <div *ngIf="!isForm" class="ss1">{{v.name}}</div>
    <input *ngIf="isForm" type="text" formControlName="name" (keyup)="cancelEditingInRow($event.keyCode)">
    </td>
  
<td [formGroup]="editVillageForm">
    <div *ngIf="!isForm">{{v.population}}</div>
    <input *ngIf="isForm"  type="text"  formControlName="population" (keyup)="cancelEditingInRow($event.keyCode)">
</td>
<td [formGroup]="editVillageForm">
    <div *ngIf="!isForm" >{{this.v.xCoord}}</div>
    <input  *ngIf="isForm" type="text"  formControlName="xCoord" (keyup)="cancelEditingInRow($event.keyCode)">
</td>
<td [formGroup]="editVillageForm">
    <div *ngIf="!isForm">{{v.yCoord}}</div>
    <input *ngIf="isForm" type="text"  formControlName="yCoord" (keyup)="cancelEditingInRow($event.keyCode)">
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
<input *ngIf="isForm" type="text" formControlName="wall" (keyup)="cancelEditingInRow($event.keyCode)">
</td>

<td *ngFor="let tp of unitValues; let i=index;" >
    <army-cell [type]="tp" [village]="v" [group]="editVillageForm.controls.armies?editVillageForm.controls.armies.controls[i]:null"
    (cancelEdit)="cancelEditing($event)" [armiesArrayControl]="armiesArrayControl" [index]="i"
    (cellClicked)="cellClick($event)" [isInput]="isForm" [ifSave]="ifSaveChanges"></army-cell>
</td>
<td *ngIf="isPlayerPage">
    <button (click)="!isForm?showEdit():changeVillage()" type="button"
    class="btn edit waves-effect waves-light col offset-s3"  name="action" 
    [disabled]="!editVillageForm.valid && isForm" style="margin-top: 5px;" >
           <i  class="material-icons">{{!isForm?'mode_edit':'play_arrow'}}</i>
           
    </button>  
</td>

`
})
export class VillageRow implements OnInit,AfterViewInit{
    POPULATION_REGEXP=/^(-)?[0-9]+$/;
    COORD_REGEXP=/^(-)?[0-9]+$/;
    @Input() v:Village;
    @Input() isForm:boolean;
    @Input() editVillageForm;
    @Output() cancelEdit:EventEmitter<Village>;
    @Output() errorMessage:EventEmitter<{}>;
    @Output() successMessage:EventEmitter<string>;
    @Output() editedVillage;
    @Input() isPlayerPage;
    unitValues:Array<string>;
    selectedVillageChanged:EventEmitter<Village>;
    ifSaveChanges:boolean;
    private cdr: ChangeDetectorRef;
    private armiesArrayControl: FormArray;
    private stringSuccessMessage:string;
    private stringErrorMessage:string;
    constructor( private villageService:VillageService,cdr: ChangeDetectorRef,
                 private _fBuilder:FormBuilder,private translate: TranslateService){
        this.selectedVillageChanged=new EventEmitter<Village>(false);
        this.unitValues=[];
        this.ifSaveChanges=false;
        this.cdr = cdr;
        this.cancelEdit=new EventEmitter<Village>();
        this.errorMessage=new EventEmitter<{}>();
        this.successMessage=new EventEmitter<string>();
        this.editedVillage=new EventEmitter<Village>();
        translate.get('Village was updated successfully.').subscribe(message=>{
            this.stringSuccessMessage=message;
        });

        this.stringErrorMessage="Error message";
    }

    ngOnInit(): void {
        this.getStringUnitTypeValues();
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
        this.villageService.update(newVillage).subscribe(
            response=>{
                 this.villageService.villages[index]=response;
                this.successMessage.emit(this.stringSuccessMessage);
                this.editedVillage.emit(response);
            },
            error=>{
                this.errorMessage.emit({error:error._body});
            }
        );
        this.ifSaveChanges=false;

    }

    showEdit(){
        this.selectedVillageChanged.emit(this.v);
        this.errorMessage.emit(null);
        this.buildForm();
    }

    cancelEditing(){
        this.selectedVillageChanged.emit(new Village);
        this.cdr.detectChanges();

    }

    buildForm(){
        this.editVillageForm=this._fBuilder.group({
            'uuid':[this.v.uuid,[]],
            'name':new FormControl(this.v.name,[Validators.required]),
            'population':[this.v.population,
                [Validators.required,Validators.pattern(this.POPULATION_REGEXP),moreThanZeroValidator()]
            ],
            'xCoord': [this.v.xCoord,
                [Validators.required, this.forbiddenXValidator(),Validators.pattern(this.COORD_REGEXP),
                    ]
            ],
            'yCoord':[this.v.yCoord,
                [Validators.required,this.forbiddenXValidator(),Validators.pattern(this.COORD_REGEXP),
                    ]
            ],
            'wall':[this.v.wall,
                [Validators.required,Validators.pattern(this.COORD_REGEXP),AddVillageForm.forbiddenWallValidator()]
            ],
            'isCapital':[this.v.isCapital],
            'armies':this._fBuilder.array([]),
        });
        this.armiesArrayControl = <FormArray>this.editVillageForm.controls['armies'];


        for (let i=0; i<27; i++) {
            this.armiesArrayControl.push(this.initArmies(this.v.armies[i]));
        }

        this.editVillageForm.valueChanges
            .subscribe(data => this.onValueChanged());
        this.onValueChanged();
    }

    initArmies(army:Army) {
        return this._fBuilder.group({
            count:['',[Validators.pattern(this.POPULATION_REGEXP),moreThanZeroValidator()]],
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



        let armiesControl:FormArray=form.get('armies');
        armiesControl.controls.forEach((control:FormGroup)=>{

            if (!armiesControl.valid)
                if (control.controls['count'].errors) {
                    for (const key in control.controls['count'].errors) {
                        this.formErrors['armies'] += this.validationMessages['armies'][key] + ' ';
                    }
                }
        });
        if (!armiesControl.controls) {
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
            'pattern':'Pattern!',

        },
        'xCoord': {
            'required': 'X  is required!',
            'forbiddenCoordinate': 'X can only range between -400 and 400!',
            'pattern':"X  can contain numbers only!",

        },
        'yCoord': {
            'required': 'Y coordinate is required!',
            'forbiddenCoordinate': 'Y can only range between -400 and 400!',
            'pattern':"Y  can contain numbers only!",

        },
        'population':{
            'required': 'Population is required!',
            'pattern':"Population can contain numbers only!",
            'cannotStartWithZero':"Population can't start with zero!",
            'moreThanZero':"Population must be more than zero!"
        },
        'wall':{
            'required': 'Wall is required!',
            'pattern':"Wall level can contain numbers only!",
            'cannotStartWithZero':"Wall can't start with zero!",
            'forbiddenWall':"Wall can only range in 0 and 127!"
        },
        'armies':{
            'pattern':'Count can contain numbers only!',
            'cannotStartWithZero':"Count can't start with zero!",
            'moreThanZero':"Count must be more than zero!"
        },

    };

}