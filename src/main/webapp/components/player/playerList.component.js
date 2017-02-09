/**
 * Created by Oleg on 14.01.2017.
 */
System.register(["./player", "@angular/core", "../services/newVillageArmiesService", "../services/villageService", "@angular/forms"], function (exports_1, context_1) {
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
    var player_1, core_1, newVillageArmiesService_1, villageService_1, forms_1, PlayerList;
    return {
        setters: [
            function (player_1_1) {
                player_1 = player_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (newVillageArmiesService_1_1) {
                newVillageArmiesService_1 = newVillageArmiesService_1_1;
            },
            function (villageService_1_1) {
                villageService_1 = villageService_1_1;
            },
            function (forms_1_1) {
                forms_1 = forms_1_1;
            }
        ],
        execute: function () {/**
             * Created by Oleg on 14.01.2017.
             */
            PlayerList = (function () {
                function PlayerList(currVillageService, villageService, _fBuilder, cdRef) {
                    this.currVillageService = currVillageService;
                    this.villageService = villageService;
                    this._fBuilder = _fBuilder;
                    this.unitValues = [];
                    this.cdRef = cdRef;
                }
                PlayerList.prototype.ngDoCheck = function () {
                };
                PlayerList.prototype.ngOnChanges = function (changes) {
                    // console.log(this.player);
                };
                PlayerList.prototype.ngOnInit = function () {
                    this.villageService.villages = this.player.villages;
                    this.editVillageForm = this._fBuilder.group({});
                };
                PlayerList.prototype.changeSelectedVillage = function (village) {
                    var _this = this;
                    this.selectedVillage = village;
                    console.log("v changed");
                    // this.buildForm();
                    this.editVillageForm.valueChanges
                        .subscribe(function (data) {
                        _this.cdRef.detectChanges();
                    });
                };
                PlayerList.prototype.onSubmit = function () {
                    var v = this.editVillageForm.value;
                    v.armies = this.currVillageService.armies;
                    console.log(this.editVillageForm.value);
                    // this.editVillageForm.valid=false;
                };
                PlayerList.prototype.cancelEditing = function (event) {
                };
                PlayerList.prototype.showEditError = function (event) {
                    this.editError = null;
                    this.successMessage = null;
                    if (event != null) {
                        this.editError = '';
                        for (var field in event) {
                            this.editError += event[field] + " ";
                        }
                        console.log(this.editError);
                    }
                };
                PlayerList.prototype.showSuccessMessage = function (event) {
                    this.editError = null;
                    this.successMessage = null;
                    this.successMessage = event;
                };
                PlayerList.prototype.closeDialog = function () {
                    this.editError = null;
                    this.successMessage = null;
                };
                return PlayerList;
            }());
            __decorate([
                core_1.Input('player'),
                __metadata("design:type", player_1.Player)
            ], PlayerList.prototype, "player", void 0);
            PlayerList = __decorate([
                core_1.Component({
                    selector: 'player-list',
                    styleUrls: ['styles/style.css'],
                    template: "\n\n<div class=\"row \">\n<form  (ngSubmit)=\"onSubmit()\">\n    <table class=\"fi responsive-table\">\n        <thead>\n        <tr>\n            <th>Village</th>\n            <th>Population</th>\n            <th>X</th>\n            <th>Y</th>\n            <th>Capital?</th>\n            <th>Wall level</th>\n            <th><img src=\"images/Gauls/GalFal.gif\"></th>\n            <th><img src=\"images/Gauls/GalSwordsman.gif\"></th>\n            <th><img src=\"images/Gauls/GalPathFinder.gif\"></th>\n            <th><img src=\"images/Gauls/GalTewtThunder.gif\"></th>\n            <th><img src=\"images/Gauls/GalDruid.gif\"></th>\n            <th><img src=\"images/Gauls/Edui.gif\"></th>\n            <th><img src=\"images/Gauls/GalRam.gif\"></th>\n            <th><img src=\"images/Gauls/GalCatapult.gif\"></th>\n            <th><img src=\"images/Gauls/GaLeader.gif\"></th>\n            <th><img src=\"images/Germans/Clubswinger.gif\"></th>\n            <th><img src=\"images/Germans/Spearman.gif\"></th>\n            <th><img src=\"images/Germans/Toporshchik.gif\"></th>\n            <th><img src=\"images/Germans/Skaut.gif\"></th>\n            <th><img src=\"images/Germans/Paladin.gif\"></th>\n            <th><img src=\"images/Germans/Tevtonskaya-konnitsa.gif\"></th>\n            <th><img src=\"images/Germans/Taran-ger.gif\"></th>\n            <th><img src=\"images/Germans/Katapulta-ger.gif\"></th>\n            <th><img src=\"images/Germans/Leader.gif\"></th>\n            <th><img src=\"images/Rome/Legioner.gif\"></th>\n            <th><img src=\"images/Rome/Praetorian.gif\"></th>\n            <th><img src=\"images/Rome/Imperianets.gif\"></th>\n            <th><img src=\"images/Rome/Konnyy-razvedchik.gif\"></th>\n            <th><img src=\"images/Rome/Konnitsa-imperatora.gif\"></th>\n            <th><img src=\"images/Rome/Konnitsa-Tsezarya.gif\"></th>\n            <th><img src=\"images/Rome/Taran-rim.gif\"></th>\n            <th><img src=\"images/Rome/Ognennaya-katapulta.gif\"></th>\n            <th><img src=\"images/Rome/Senator.gif\"></th>\n            <th></th>\n        </tr>\n        </thead>\n\n        <tbody *ngFor=\"let v of player.villages\">\n       \n        <tr player-ro [v]=\"v\"  [isForm]=\"v==selectedVillage\" (errorMessage)=\"showEditError($event)\"\n        (successMessage)=\"showSuccessMessage($event)\" (editedVillage)=\"this.editedVillage=$event\"\n            (selectedVillageChanged)=\"changeSelectedVillage($event)\" [editVillageForm]=\"editVillageForm\" (keyup)=\"cancelEditing($event.keyCode)\">       \n</tr>\n<tr>\n<td *ngIf=\"(editError!=undefined && v==selectedVillage)|| (successMessage!=undefined && editedVillage==v)\" colspan=\"33\">\n <div *ngIf=\"successMessage!=null||editError!=null\" class=\"col s4 offset-s4 \">\n    <div  [ngClass]=\"{'card':true, 'green':successMessage!=null, 'red':editError!=null, 'lighten-5':true}\">\n        <div [ngClass]=\"{'card-content':true , 'green-text':successMessage!=null,'red-text':editError!=null }\">\n            <p class=\"centerCard\">{{editError!=null?editError:successMessage}} <span (click)=\"closeDialog()\" class=\"right\">x</span></p>\n        </div>\n    </div>\n</div>\n</td>\n</tr>\n         \n        </tbody>\n    </table>\n    </form>\n</div>\n\n"
                }),
                __metadata("design:paramtypes", [newVillageArmiesService_1.CurrVillageArmiesService, villageService_1.VillageService,
                    forms_1.FormBuilder, core_1.ChangeDetectorRef])
            ], PlayerList);
            exports_1("PlayerList", PlayerList);
        }
    };
});
//# sourceMappingURL=playerList.component.js.map