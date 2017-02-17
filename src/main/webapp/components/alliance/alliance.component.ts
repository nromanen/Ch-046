import {Alliance} from "./alliance";
import {Component, OnInit} from "@angular/core";
import {AllianceService} from "../services/alliance/alliance-service";
import {TranslateService} from "ng2-translate";


@Component({
    selector: 'my-alliance',
    templateUrl: 'components/alliance/alliance.html',
    styleUrls: ['components/alliance/alliance.css']
})

export class AllianceComponent implements OnInit{

    alliances: Array<Alliance> = [];
    errorMessage: string = null;
    successMessage: string = null;
    addNewAlliance: boolean = false;
    selectedAlliance: Alliance = null;
    deletedAlliance: Alliance = null;
    confirmMsg: string;

    constructor(private _allianceService: AllianceService, private translate:TranslateService){
    }

    ngOnInit() {
        this.getAlliances();
    }

    getAlliances() {
        this._allianceService.getAlliances()
            .subscribe(
                alliances => {
                    if(alliances != null) {
                        this.alliances = alliances
                    }
                },
                error =>  this.errorMessage = <any>error
            );
    }

    onNotifyUpdate(alliance : Alliance){
        if (alliance !== null){
            this._allianceService.updateAlliance(alliance)
                .subscribe(
                    resp => {
                        this.alliances[this.alliances.indexOf(this.selectedAlliance)] = resp;
                        this.successMessage = "Alliance updated successfully";
                        this.errorMessage = null;
                        this.selectedAlliance = null;
                    },
                    error =>  {
                        this.errorMessage = <any>error;
                        this.successMessage = null;
                        this.selectedAlliance = null;
                    }
                );
        } else {
            this.selectedAlliance = alliance;
        }
    }

    onNotifyDelete(confitmation : boolean){
        if (confitmation){
            if(this._allianceService.deleteAlliance(this.deletedAlliance)){
                this.alliances.splice(this.alliances.indexOf(this.deletedAlliance), 1);
                this.successMessage = "Alliance deleted successfully";
                this.errorMessage = null;
            }
        }
        this.deletedAlliance = null;
    }

    onNotifyCreate(alliance : Alliance){
        this._allianceService.addAlliance(alliance)
            .subscribe(
                resp => {
                    this.alliances.push(resp);
                    this.successMessage = "Alliance added successfully";
                    this.errorMessage = null;
                },
                error =>  {
                    this.errorMessage = <any>error;
                    this.successMessage = null;
                }
            );
        this.addNewAlliance = false;
    }

    newAlliance(){
        this.addNewAlliance = true;
    }

    editAlliance(alliance: Alliance){
        this.selectedAlliance = alliance;
    }

    deleteAlliance(alliance: Alliance){
        this.confirmMsg = this.translate.instant("DeleteAlliance") + alliance.name + "?";
        this.deletedAlliance = alliance;
    }

    cancelEditing(){
        this.selectedAlliance = null;
    }

    closeSuccess(){
        this.successMessage = null;
    }

    closeError(){
        this.errorMessage = null;
    }
}