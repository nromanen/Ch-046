System.register(["@angular/core", "../village/village", "../army/army", "../services/villageService", "@angular/forms", "../UnitType/unitType"], function (exports_1, context_1) {
    "use strict";
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var __moduleName = context_1 && context_1.id;
    var core_1, village_1, army_1, villageService_1, forms_1, unitType_1, AddVillageForm, AddVillageForm_1;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (village_1_1) {
                village_1 = village_1_1;
            },
            function (army_1_1) {
                army_1 = army_1_1;
            },
            function (villageService_1_1) {
                villageService_1 = villageService_1_1;
            },
            function (forms_1_1) {
                forms_1 = forms_1_1;
            },
            function (unitType_1_1) {
                unitType_1 = unitType_1_1;
            }
        ],
        execute: function () {
            AddVillageForm = AddVillageForm_1 = (function () {
                function AddVillageForm(villageService, _fBuilder) {
                    this.villageService = villageService;
                    this._fBuilder = _fBuilder;
                    this.submitted = false;
                    this.COORD_REGEXP = /^(-)?[0-9]*$/;
                    this.POPULATION_REGEXP = /^\d*$/;
                    this.formErrors = {
                        'name': '',
                        'xCoord': '',
                        'yCoord': '',
                        'population': '',
                        'wall': '',
                    };
                    this.validationMessages = {
                        'name': {
                            'required': 'Name is required.',
                            'minlength': 'Name must be at least 4 characters long.',
                            'maxlength': 'Name cannot be more than 24 characters long.',
                            'pattern': 'Name can contain chars only'
                        },
                        'xCoord': {
                            'required': 'X  is required.',
                            'forbiddenCoordinate': 'X can only range between -400 and 400.',
                            'pattern': "X  can contain numbers only"
                        },
                        'yCoord': {
                            'required': 'Y  is required!\n',
                            'forbiddenCoordinate': 'Y can only range between -400 and 400.\n',
                            'pattern': "Y can contain numbers only"
                        },
                        'population': {
                            'required': 'Population is required.',
                            'pattern': "Population can contain numbers only"
                        },
                        'wall': {
                            'required': 'Wall is required.',
                            'pattern': "Wall level can contain numbers only"
                        }
                    };
                    this.village = new village_1.Village;
                    this.village.armies = [];
                    this.village.isCapital = false;
                    this.wasSubmitted = new core_1.EventEmitter();
                    this.errorMessage = new core_1.EventEmitter();
                    this.successMessage = new core_1.EventEmitter();
                }
                AddVillageForm.prototype.ngOnInit = function () {
                    console.log(this.village);
                    this.buildForm();
                };
                AddVillageForm.prototype.ngAfterViewChecked = function () {
                };
                AddVillageForm.prototype.buildForm = function () {
                    var _this = this;
                    this.addVillageForm = this._fBuilder.group({
                        'name': [
                            this.village.name,
                            [forms_1.Validators.required, forms_1.Validators.minLength(5), forms_1.Validators.maxLength(14), forms_1.Validators.pattern(AddVillageForm_1.VILLAGE_REGEXP)
                            ]
                        ],
                        'xCoord': [this.village.xCoord,
                            [forms_1.Validators.required, this.forbiddenXValidator(), forms_1.Validators.pattern(this.COORD_REGEXP)]
                        ],
                        'yCoord': [this.village.yCoord,
                            [forms_1.Validators.required, this.forbiddenXValidator(), forms_1.Validators.pattern(this.COORD_REGEXP)]
                        ],
                        'population': [this.village.population, [forms_1.Validators.required, forms_1.Validators.pattern(this.POPULATION_REGEXP)]
                        ],
                        'wall': [this.village.wall,
                            [forms_1.Validators.required, forms_1.Validators.pattern(this.POPULATION_REGEXP)]
                        ],
                        'isCapital': [this.village.isCapital],
                        'armies': this._fBuilder.array([this.initArmies()])
                    });
                    this.addVillageForm.valueChanges
                        .subscribe(function (data) { return _this.onValueChanged(); });
                    this.onValueChanged();
                };
                AddVillageForm.prototype.initArmies = function () {
                    return this._fBuilder.group({
                        type: ['', forms_1.Validators.required],
                        count: ['', [forms_1.Validators.required, forms_1.Validators.pattern(this.POPULATION_REGEXP)]],
                        ownUnit: [false, []]
                    });
                };
                AddVillageForm.prototype.addArmies = function () {
                    this.village.armies.push(new army_1.Army);
                    var control = this.addVillageForm.controls['armies'];
                    control.push(this.initArmies());
                    console.log(this.village);
                };
                AddVillageForm.prototype.onSubmit = function (village) {
                    var _this = this;
                    var allArmies = [];
                    for (var type in unitType_1.UnitType) {
                        var army = new army_1.Army(0, unitType_1.UnitType[unitType_1.UnitType[type]], false);
                        allArmies.push(army);
                    }
                    this.submitted = true;
                    this.village = this.addVillageForm.value;
                    console.log(this.village);
                    this.village.armies.forEach(function (army) {
                        allArmies.forEach(function (armyInAll) {
                            if (army.type == armyInAll.type) {
                                console.log(army.type);
                                armyInAll.count = army.count;
                                armyInAll.ownUnit = army.ownUnit;
                            }
                        });
                    });
                    this.village.player = this.player;
                    this.village.armies = allArmies;
                    this.villageService.add(this.village).subscribe(function (response) {
                        _this.player.villages.push(response);
                        _this.successMessage.emit('The village has successfully been created!');
                        _this.wasSubmitted.emit(false);
                    }, function (error) {
                        _this.errorMessage.emit(error._body);
                    });
                };
                AddVillageForm.prototype.onValueChanged = function (data) {
                    if (!this.addVillageForm) {
                        return;
                    }
                    var form = this.addVillageForm;
                    for (var field in this.formErrors) {
                        // clear previous error message (if any)
                        this.formErrors[field] = '';
                        var control = form.get(field);
                        if (control && control.dirty && !control.valid) {
                            var messages = this.validationMessages[field];
                            for (var key in control.errors) {
                                this.formErrors[field] += messages[key] + ' ';
                            }
                        }
                    }
                };
                AddVillageForm.prototype.forbiddenXValidator = function () {
                    return function (control) {
                        var coord = control.value;
                        if (coord < -400 || coord > 400)
                            return { 'forbiddenCoordinate': { coord: coord } };
                        return null;
                    };
                };
                return AddVillageForm;
            }());
            AddVillageForm.VILLAGE_REGEXP = /^[A-z]*$/;
            __decorate([
                core_1.Input(),
                __metadata("design:type", Object)
            ], AddVillageForm.prototype, "player", void 0);
            __decorate([
                core_1.Output(),
                __metadata("design:type", core_1.EventEmitter)
            ], AddVillageForm.prototype, "wasSubmitted", void 0);
            __decorate([
                core_1.Output(),
                __metadata("design:type", core_1.EventEmitter)
            ], AddVillageForm.prototype, "successMessage", void 0);
            __decorate([
                core_1.Output(),
                __metadata("design:type", core_1.EventEmitter)
            ], AddVillageForm.prototype, "errorMessage", void 0);
            AddVillageForm = AddVillageForm_1 = __decorate([
                core_1.Component({
                    selector: "add-vill-form",
                    templateUrl: "components/village/addVillageForm.html",
                    styleUrls: ['styles/style.css']
                }),
                __metadata("design:paramtypes", [villageService_1.VillageService, forms_1.FormBuilder])
            ], AddVillageForm);
            exports_1("AddVillageForm", AddVillageForm);
        }
    };
});
//# sourceMappingURL=addVillageForm.js.map