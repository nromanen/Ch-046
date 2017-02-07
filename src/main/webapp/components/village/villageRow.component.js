System.register(["@angular/core", "../UnitType/unitType", "./village", "../services/villageService", "@angular/forms", "./addVillageForm"], function (exports_1, context_1) {
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
    var core_1, unitType_1, village_1, villageService_1, forms_1, addVillageForm_1, VillageRow;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (unitType_1_1) {
                unitType_1 = unitType_1_1;
            },
            function (village_1_1) {
                village_1 = village_1_1;
            },
            function (villageService_1_1) {
                villageService_1 = villageService_1_1;
            },
            function (forms_1_1) {
                forms_1 = forms_1_1;
            },
            function (addVillageForm_1_1) {
                addVillageForm_1 = addVillageForm_1_1;
            }
        ],
        execute: function () {
            VillageRow = (function () {
                function VillageRow(villageService, cdr, _fBuilder) {
                    this.villageService = villageService;
                    this._fBuilder = _fBuilder;
                    this.POPULATION_REGEXP = /^\d*$/;
                    this.COORD_REGEXP = /^(-)?[0-9]*$/;
                    this.formErrors = {
                        'name': '',
                        'xCoord': '',
                        'yCoord': '',
                        'population': '',
                        'wall': '',
                        'armies': '',
                    };
                    this.validationMessages = {
                        'name': {
                            'required': 'Name is required!',
                            'minlength': 'Name must be at least 4 characters long!',
                            'maxlength': 'Name cannot be more than 24 characters long!',
                            'pattern': 'Pattern!'
                        },
                        'xCoord': {
                            'required': 'X  is required!',
                            'forbiddenCoordinate': 'X can only range between -400 and 400!',
                            'pattern': "X  can contain numbers only!"
                        },
                        'yCoord': {
                            'required': 'Y coordinate is required.',
                            'forbiddenCoordinate': 'Y can only range between -400 and 400!',
                            'pattern': "Y  can contain numbers only!"
                        },
                        'population': {
                            'required': 'Population is required.',
                            'pattern': "Population can contain numbers only!"
                        },
                        'wall': {
                            'required': 'Wall is required.',
                            'pattern': "Wall level can contain numbers only!"
                        },
                        'armies': {
                            'pattern': 'Count can contain numbers only!'
                        }
                    };
                    this.selectedVillageChanged = new core_1.EventEmitter(false);
                    this.unitValues = [];
                    this.ifSaveChanges = false;
                    this.cdr = cdr;
                    this.cancelEdit = new core_1.EventEmitter();
                    this.errorMessage = new core_1.EventEmitter();
                    this.successMessage = new core_1.EventEmitter();
                    this.editedVillage = new core_1.EventEmitter();
                }
                VillageRow.prototype.ngOnInit = function () {
                    this.getStringUnitTypeValues();
                    // this.buildForm();
                };
                VillageRow.prototype.ngAfterViewInit = function () {
                    this.cdr.detectChanges();
                };
                VillageRow.prototype.getStringUnitTypeValues = function () {
                    for (var m in unitType_1.UnitType) {
                        this.unitValues.push(m);
                    }
                };
                VillageRow.prototype.cellClick = function (village) {
                    this.selectedVillageChanged.emit(village);
                };
                VillageRow.prototype.changeVillage = function () {
                    var _this = this;
                    this.ifSaveChanges = true;
                    var index = this.villageService.villages.indexOf(this.v);
                    var newVillage = this.editVillageForm.value;
                    // this.villageService.villages[index]=newVillage;
                    console.log(newVillage);
                    this.villageService.update(newVillage).subscribe(function (response) {
                        _this.villageService.villages[index] = response;
                        _this.successMessage.emit("Village has successfully been updated ");
                        _this.editedVillage.emit(response);
                    }, function (error) {
                        _this.errorMessage.emit({ error: error._body });
                    });
                    // this.selectedVillageChanged.emit(new Village);
                    this.ifSaveChanges = false;
                };
                VillageRow.prototype.showEdit = function () {
                    this.selectedVillageChanged.emit(this.v);
                    this.errorMessage.emit(null);
                    this.buildForm();
                    // this.cdr.detectChanges();
                };
                VillageRow.prototype.cancelEditing = function () {
                    // this.isForm=false;
                    this.selectedVillageChanged.emit(new village_1.Village);
                    this.cdr.detectChanges();
                };
                VillageRow.prototype.buildForm = function () {
                    var _this = this;
                    this.editVillageForm = this._fBuilder.group({
                        'uuid': [this.v.uuid, []],
                        'name': new forms_1.FormControl(this.v.name, [forms_1.Validators.required, forms_1.Validators.pattern(addVillageForm_1.AddVillageForm.VILLAGE_REGEXP)]),
                        'population': [this.v.population, [forms_1.Validators.required, forms_1.Validators.pattern(this.POPULATION_REGEXP)]
                        ],
                        'xCoord': [this.v.xCoord,
                            [forms_1.Validators.required, this.forbiddenXValidator(), forms_1.Validators.pattern(this.COORD_REGEXP)]
                        ],
                        'yCoord': [this.v.yCoord,
                            [forms_1.Validators.required, this.forbiddenXValidator(), forms_1.Validators.pattern(this.COORD_REGEXP)]
                        ],
                        'wall': [this.v.wall,
                            [forms_1.Validators.required, forms_1.Validators.pattern(this.POPULATION_REGEXP)]
                        ],
                        'isCapital': [this.v.isCapital],
                        'armies': this._fBuilder.array([]),
                    });
                    this.armiesArrayControl = this.editVillageForm.controls['armies'];
                    // for (let i=0; i<this.v.armies.length; i++) {
                    //     this.armiesArrayControl.push(this.initArmies(this.v.armies[i]));
                    // }
                    for (var i = 0; i < 27; i++) {
                        this.armiesArrayControl.push(this.initArmies(this.v.armies[i]));
                    }
                    this.editVillageForm.valueChanges
                        .subscribe(function (data) { return _this.onValueChanged(); });
                    this.onValueChanged();
                };
                VillageRow.prototype.initArmies = function (army) {
                    return this._fBuilder.group({
                        count: ['', [forms_1.Validators.pattern(this.POPULATION_REGEXP)]],
                        type: [army.type, []],
                        ownUnit: [army.ownUnit, []],
                        uuid: [army.uuid, []]
                    });
                };
                VillageRow.prototype.cancelEditingInRow = function (event) {
                    if (event === 27) {
                        this.selectedVillageChanged.emit(new village_1.Village);
                    }
                };
                VillageRow.prototype.forbiddenXValidator = function () {
                    return function (control) {
                        var coord = control.value;
                        if (coord < -400 || coord > 400)
                            return { 'forbiddenCoordinate': { coord: coord } };
                        return null;
                    };
                };
                VillageRow.prototype.onValueChanged = function (data) {
                    if (!this.editVillageForm) {
                        return;
                    }
                    var form = this.editVillageForm;
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
                    // console.log(this.editVillageForm);
                    var armiesControl = form.get('armies');
                    if (!armiesControl.valid) {
                        this.formErrors['armies'] = 'Count can contain numbers only!';
                    }
                    if (!this.editVillageForm.valid) {
                        this.errorMessage.emit(this.formErrors);
                    }
                    else
                        this.errorMessage.emit(null);
                };
                return VillageRow;
            }());
            __decorate([
                core_1.Input(),
                __metadata("design:type", village_1.Village)
            ], VillageRow.prototype, "v", void 0);
            __decorate([
                core_1.Input(),
                __metadata("design:type", Boolean)
            ], VillageRow.prototype, "isForm", void 0);
            __decorate([
                core_1.Input(),
                __metadata("design:type", Object)
            ], VillageRow.prototype, "editVillageForm", void 0);
            __decorate([
                core_1.Output(),
                __metadata("design:type", core_1.EventEmitter)
            ], VillageRow.prototype, "cancelEdit", void 0);
            __decorate([
                core_1.Output(),
                __metadata("design:type", core_1.EventEmitter)
            ], VillageRow.prototype, "errorMessage", void 0);
            __decorate([
                core_1.Output(),
                __metadata("design:type", core_1.EventEmitter)
            ], VillageRow.prototype, "successMessage", void 0);
            __decorate([
                core_1.Output(),
                __metadata("design:type", Object)
            ], VillageRow.prototype, "editedVillage", void 0);
            VillageRow = __decorate([
                core_1.Component({
                    selector: '[player-ro]',
                    outputs: ['selectedVillageChanged'],
                    styleUrls: ['styles/style.css'],
                    template: "\n<td [formGroup]=\"editVillageForm\" >\n    <div *ngIf=\"!isForm\" class=\"ss1\">{{v.name}}</div>\n    <input *ngIf=\"isForm\" type=\"text\" formControlName=\"name\" (keyup)=\"cancelEditingInRow($event.keyCode)\">\n    </td>\n  \n<td [formGroup]=\"editVillageForm\">\n    <div *ngIf=\"!isForm\">{{v.population}}</div>\n    <input *ngIf=\"isForm\"  type=\"text\"  formControlName=\"population\">\n</td>\n<td [formGroup]=\"editVillageForm\">\n    <div *ngIf=\"!isForm\" >{{this.v.xCoord}}</div>\n    <input  *ngIf=\"isForm\" type=\"text\"  formControlName=\"xCoord\">\n</td>\n<td [formGroup]=\"editVillageForm\">\n    <div *ngIf=\"!isForm\">{{v.yCoord}}</div>\n    <input *ngIf=\"isForm\" type=\"text\"  formControlName=\"yCoord\" >\n</td>\n<td [formGroup]=\"editVillageForm\">\n    <div *ngIf=\"!isForm\">\n    <i *ngIf=\"v.isCapital\" class=\"small material-icons check\">done</i>\n    </div>\n    <input *ngIf=\"isForm\" type=\"checkbox\" formControlName=\"isCapital\" id=\"isCapital\" class=\"filled-in\">\n    <label *ngIf=\"isForm\" for=\"isCapital\"></label>\n</td>\n\n<td [formGroup]=\"editVillageForm\">\n<div *ngIf=\"!isForm\">{{v.wall}}</div>\n<input *ngIf=\"isForm\" type=\"text\" formControlName=\"wall\">\n</td>\n\n<td *ngFor=\"let tp of unitValues; let i=index;\" >\n    <army-cell [type]=\"tp\" [village]=\"v\" [group]=\"editVillageForm.controls.armies?editVillageForm.controls.armies.controls[i]:null\"\n    (cancelEdit)=\"cancelEditing($event)\" [armiesArrayControl]=\"armiesArrayControl\" [index]=\"i\"\n    (cellClicked)=\"cellClick($event)\" [isInput]=\"isForm\" [ifSave]=\"ifSaveChanges\"></army-cell>\n</td>\n<td>\n    <button (click)=\"!isForm?showEdit():changeVillage()\" type=\"button\"\n    class=\"btn waves-effect waves-light col offset-s3\"  name=\"action\" \n    [disabled]=\"!editVillageForm.valid && isForm\" style=\"margin-top: 5px;\" >\n            {{!isForm?\"Edit\":\"Save\"}}      \n    </button>\n</td>\n\n"
                }),
                __metadata("design:paramtypes", [villageService_1.VillageService, core_1.ChangeDetectorRef,
                    forms_1.FormBuilder])
            ], VillageRow);
            exports_1("VillageRow", VillageRow);
        }
    };
});
//# sourceMappingURL=villageRow.component.js.map