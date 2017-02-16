/**
 * Created by okunetc on 23.01.2017.
 */
import {Component, OnInit, ViewChild, AfterViewChecked, Input, Output, EventEmitter} from "@angular/core";
import {Village} from "../village/village";
import {Army} from "../army/army";
import {VillageService} from "../services/villageService";
import {FormGroup, FormBuilder, Validators, FormArray, AbstractControl} from "@angular/forms";

import {UnitType} from "../UnitType/unitType";
import {moreThanZeroValidator} from "../validators/validators";
@Component({
    selector: "add-vill-form",
    templateUrl: "components/village/addVillageForm.html",
    styleUrls:['styles/style.css']
})
export class AddVillageForm implements OnInit,AfterViewChecked {
    @Input() player;
    @Output() wasSubmitted: EventEmitter<any>;
    village: Village;
    submitted = false;
    addVillageForm;
    COORD_REGEXP=/^(-)?[0-9]+$/;
    POPULATION_REGEXP=/^(-)?\d+$/;
    @Output() successMessage:EventEmitter<string>;
    @Output() errorMessage:EventEmitter<string>;
    private unitTypeStrings: Array<string>;
    ngOnInit(): void {
        this.buildForm();
    }

    constructor(private villageService: VillageService, private _fBuilder: FormBuilder) {
        this.village = new Village;
        this.village.armies = [];
        this.village.isCapital = false;
        this.wasSubmitted = new EventEmitter();
        this.errorMessage=new EventEmitter<string>();
        this.successMessage=new EventEmitter<string>();
        this.unitTypeStrings=[];
        this.initUnitTypes();
    }

    ngAfterViewChecked() {
    }

    buildForm() {
        this.addVillageForm = this._fBuilder.group({
            'name': [
                this.village.name,
                [Validators.required,
                ]
            ],
            'xCoord': [this.village.xCoord,
                [Validators.required, this.forbiddenXValidator(),Validators.pattern(this.COORD_REGEXP),
               ]
            ],
            'yCoord':[this.village.yCoord,
                [Validators.required,this.forbiddenXValidator(),Validators.pattern(this.COORD_REGEXP)]
            ],
            'population':[this.village.population,[Validators.required,Validators.pattern(this.POPULATION_REGEXP),moreThanZeroValidator()]
            ],
            'wall':[this.village.wall,
                [Validators.required,Validators.pattern(this.COORD_REGEXP),AddVillageForm.forbiddenWallValidator()]
            ],
            'isCapital':[this.village.isCapital],
            'armies':this._fBuilder.array([this.initArmies()])
        });
        this.addVillageForm.valueChanges
            .subscribe(data => this.onValueChanged());
        this.onValueChanged();


    }

    initArmies() {
        return this._fBuilder.group({
            type: ['', Validators.required],
            count: ['',[Validators.required,Validators.pattern(this.POPULATION_REGEXP),moreThanZeroValidator()]],
            ownUnit:[false,[]]
        });
    }



    addArmies() {
        this.village.armies.push(new Army);
        const control = <FormArray>this.addVillageForm.controls['armies'];
        control.push(this.initArmies());
    }

    onSubmit(village: Village) {
        let allArmies:Array<Army>=[];
        for (let type in UnitType)
        {
            let army=new Army(0,UnitType[UnitType[type]],false);
            allArmies.push(army);
        }
        this.submitted = true;
        this.village = this.addVillageForm.value;
        this.village.armies.forEach(army=>{
            allArmies.forEach(armyInAll=>{
                if (army.type==armyInAll.type){
                    armyInAll.count=army.count;
                    armyInAll.ownUnit=army.ownUnit;
                }
            })
        });
        this.village.player=this.player;
        this.village.armies=allArmies;
        this.villageService.add(this.village).subscribe(
            response=>{
                this.player.villages.push(response);
                this.successMessage.emit('The village has successfully been created!');
                this.wasSubmitted.emit(false);
            },
                    error=>{
                        this.errorMessage.emit(error._body);
                    }
        );

    }

    onValueChanged(data?: any) {
        if (!this.addVillageForm) {
            return;
        }
        const form = this.addVillageForm;
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


    }

    formErrors = {
        'name': '',
        'xCoord': '',
        'yCoord':'',
        'population':'',
        'wall':'',
    };

    validationMessages = {
        'name': {
            'required': 'Name is required!',
            'minlength': 'Name must be at least 4 characters long!',
            'maxlength': 'Name cannot be more than 24 characters long!',
        },
        'xCoord': {
            'required': 'X  is required!',
            'forbiddenCoordinate': 'X can only range between -400 and 400!',
            'pattern':"X  can contain numbers only!",
        },
        'yCoord': {
            'required': 'Y is required!',
            'forbiddenCoordinate': 'Y can only range between -400 and 400!',
            'pattern':"Y can contain numbers only!",

        },
        'population':{
            'required': 'Population is required!',
            'pattern':"Population can contain numbers only!",
            'moreThanZero':"Population must be more than zero!"
        },
        'wall':{
            'required': 'Wall is required!',
            'pattern':"Wall level can contain numbers only!",
            'forbiddenWall':"Wall can only range in 0 and 127!",

        },
        'armies':{
            'pattern':'Count can contain numbers only!',
            'cannotStartWithZero':"Count can't start with zero!",
            'moreThanZero':"Count must be more than zero!"
        },
    };

     forbiddenXValidator() {
         return (control: AbstractControl): {[key: string]: any} => {
             const coord = control.value;
             if (coord < -400 || coord > 400)
                 return {'forbiddenCoordinate': {coord}};
             return null;
         };
     }

     static forbiddenWallValidator(){
         return (control: AbstractControl): {[key: string]: any} => {
             const wall = control.value;
             if (wall < 0 || wall > 127)
                 return {'forbiddenWall': {wall}};
             return null;
         };
     }

    cannotStartWithZeroValidator(){
        return (control: AbstractControl): {[key: string]: any} => {
            const coord = control.value;
            if (coord&&coord[0]=='0')
                return {'cannotStartWithZero': {coord}};
            return null;
        };
    }

    initUnitTypes(){
        for(let type in UnitType){
            this.unitTypeStrings.push(type);
        }
    }

    updateTypes(types){
        this.unitTypeStrings=types;
    }
}