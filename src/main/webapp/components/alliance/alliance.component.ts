
import {Alliance} from "./alliance";
import {Component, OnInit} from "@angular/core";
import {AllianceService} from "../services/alliance/alliance-service";


@Component({
    selector: 'my-alliance',
    templateUrl: 'components/alliance/alliance.html',
    styleUrls: ['components/alliance/alliance.css']
})



export class AllianceComponent implements OnInit{

    alliances: Array<Alliance> ;
    errorMessage: string = null;
    successMessage: string = null;
    addNewAlliance: boolean = false;

    selectedAlliance: Alliance = null;
    deletedAlliance: Alliance = null;

    confirmMsg: string;


    constructor(private _allianceService: AllianceService){
    }

    ngOnInit() {
        this.getAlliances();
    }

    newAlliance(){
        this.addNewAlliance = true;
    }

    closeSuccess(){
        this.successMessage = null;
    }

    closeError(){
        this.errorMessage = null;
    }

    onNotifyUpdate(alliance : Alliance){
        if (alliance !== null){
            console.log(alliance);
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

    editAlliance(al: Alliance){
        this.selectedAlliance = al;
    }

    deleteAlliance(al: Alliance){
        console.log("ontest delete");
        this.confirmMsg = "Are you sure you want to delete alliance " + al.name + "?";
        this.deletedAlliance = al;

    }

    cancelEditing(){
        this.selectedAlliance = null;
    }

    getAlliances() {
        this._allianceService.getAlliances()
            .subscribe(
                alliances => this.alliances = alliances,
                error =>  this.errorMessage = <any>error
            );
    }

}