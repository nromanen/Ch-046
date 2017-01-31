/**
 * Created by okunetc on 23.01.2017.
 */
import {Component, OnInit, ViewChild, AfterViewChecked, Input, Output, EventEmitter} from "@angular/core";
import {Village} from "../village/village";
import {Army} from "../army/army";
import {VillageService} from "../services/villageService";
import {FormGroup, FormBuilder, Validators, FormArray} from "@angular/forms";
import {forbiddenXValidator} from "./forbidden-x.directive";
@Component({
    selector: "add-vill-form",
    templateUrl: "components/village/addVillageForm.html"
})
export class AddVillageForm implements OnInit,AfterViewChecked {

    submitted = false;
    addVillageForm: FormGroup;
    COORD_REGEXP=/^[0-9]*$/;
    POPULATION_REGEXP=/^\d*$/;

    ngOnInit(): void {
        console.log(this.village);
        this.buildForm();
    }

    showAddArmyButton: boolean;
    village: Village;
    @Input() player;
    @Output() wasSubmitted: EventEmitter<any>;

    constructor(private villageService: VillageService, private _fBuilder: FormBuilder) {
        this.village = new Village;
        this.village.armies = [];
        // this.village.name="";
        this.village.isCapital = false;
        this.wasSubmitted = new EventEmitter();
        this.showAddArmyButton = true;
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
                [Validators.required, forbiddenXValidator(),Validators.pattern(this.COORD_REGEXP)]
            ],
            'yCoord':[this.village.yCoord,
                [Validators.required,forbiddenXValidator(),Validators.pattern(this.COORD_REGEXP)]
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
        // initialize our address
        return this._fBuilder.group({
            type: ['', Validators.required],
            count: ['',[Validators.required,Validators.pattern(this.POPULATION_REGEXP)]],
            isOwnUnit:[false,[]]
        });
    }


    addArmies() {
        this.village.armies.push(new Army);
        const control = <FormArray>this.addVillageForm.controls['armies'];
        control.push(this.initArmies());
        console.log(this.village);
        // this.showAddArmyButton = false;
    }

    onSubmit(village: Village) {
        // this.player.villages.push(village);
        // this.village = new Village;
        // this.village.armies = [];

        this.submitted = true;
        this.village = this.addVillageForm.value;
        this.village.player=this.player;
        console.log(this.village);
        this.villageService.add(this.village);
        this.wasSubmitted.emit(false);
    }

    showAddArmy(ifShow: boolean) {
        this.showAddArmyButton = ifShow;
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

    s: string;
    validationMessages = {
        'name': {
            'required': 'Name is required.',
            'minlength': 'Name must be at least 4 characters long.',
            'maxlength': 'Name cannot be more than 24 characters long.',
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

}