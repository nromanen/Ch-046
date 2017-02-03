/**
 * Created by okunetc on 23.01.2017.
 */
import {Component, OnInit, ViewChild, AfterViewChecked, Input, Output, EventEmitter} from "@angular/core";
import {Village} from "../village/village";
import {Army} from "../army/army";
import {VillageService} from "../services/villageService";
import {FormGroup, FormBuilder, Validators, FormArray, AbstractControl} from "@angular/forms";

import {UnitType} from "../UnitType/unitType";
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
    addVillageForm: FormGroup;
    COORD_REGEXP=/^(-)?[0-9]*$/;
    POPULATION_REGEXP=/^\d*$/;
    static VILLAGE_REGEXP=/^[A-z]*$/;
    @Output() successMessage:EventEmitter<string>;
    @Output() errorMessage:EventEmitter<string>;
    ngOnInit(): void {
        console.log(this.village);
        this.buildForm();
    }

    constructor(private villageService: VillageService, private _fBuilder: FormBuilder) {
        this.village = new Village;
        this.village.armies = [];
        this.village.isCapital = false;
        this.wasSubmitted = new EventEmitter();
        this.errorMessage=new EventEmitter<string>();
        this.successMessage=new EventEmitter<string>();
    }

    ngAfterViewChecked() {
    }

    buildForm() {
        this.addVillageForm = this._fBuilder.group({
            'name': [
                this.village.name,
                [Validators.required, Validators.minLength(5),
                ]
            ],
            'xCoord': [this.village.xCoord,
                [Validators.required, this.forbiddenXValidator(),Validators.pattern(this.COORD_REGEXP)]
            ],
            'yCoord':[this.village.yCoord,
                [Validators.required,this.forbiddenXValidator(),Validators.pattern(this.COORD_REGEXP)]
            ],
            'population':[this.village.population,[Validators.required,Validators.pattern(this.POPULATION_REGEXP)]
            ],
            'wall':[this.village.wall,
                [Validators.required,Validators.pattern(this.POPULATION_REGEXP)]
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
            count: ['',[Validators.required,Validators.pattern(this.POPULATION_REGEXP)]],
            ownUnit:[false,[]]
        });
    }


    addArmies() {
        this.village.armies.push(new Army);
        const control = <FormArray>this.addVillageForm.controls['armies'];
        control.push(this.initArmies());
        console.log(this.village);
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
        console.log(this.village);
        this.village.armies.forEach(army=>{
            allArmies.forEach(armyInAll=>{
                if (army.type==armyInAll.type){
                    console.log(army.type);
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
            'required': 'Name is required.',
            'minlength': 'Name must be at least 4 characters long.',
            'maxlength': 'Name cannot be more than 24 characters long.',
            'pattern':'Name can contain chars only'
        },
        'xCoord': {
            'required': 'X coordinate is required.',
            'forbiddenCoordinate': 'X can only range between -400 and 400.',
            'pattern':"X coordinate can contain numbers only"
        },
        'yCoord': {
            'required': 'Y coordinate is required.',
            'forbiddenCoordinate': 'Y can only range between -400 and 400.',
            'pattern':"Y coordinate can contain numbers only"
        },
        'population':{
            'required': 'Population is required.',
            'pattern':"Population can contain numbers only"
        },
        'wall':{
            'required': 'Wall is required.',
            'pattern':"Wall level can contain numbers only"
        }
    };

     forbiddenXValidator() {
         return (control: AbstractControl): {[key: string]: any} => {
             const coord = control.value;
             if (coord < -400 || coord > 400)
                 return {'forbiddenCoordinate': {coord}};
             return null;
         };
     }
}