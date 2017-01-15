
import {Alliance} from "./alliance";
// import {ErrorMessage} from "../modal_window/modal";
import {Component, ViewChild} from "@angular/core";
import {AllianceService} from "../services/alliance-service";
import {FormBuilder, Validators, FormGroup} from "@angular/forms";
import {ConfirmComponent} from "../modal_window/modal";


@Component({
    selector: 'my-alliance',
    templateUrl: 'components/alliance/alliance.html',
    // providers: [AllianceService],
})

export class AllianceComponent{

    alliances: Array<Alliance> = [new Alliance('Valhala', 'borg', 'test@net.com'), new Alliance('Torr', 'viking', 'test@nmetacom')];

    selectedAlliance: Alliance = null;
    deletedAlliance: Alliance = null;
    name: string;
    login: string;
    email: string;

    confirmMsg: string;

   // @ViewChild(ConfirmComponent) confirmDelete: ConfirmComponent;  // ErrorMessage is a ViewChild
   //

    constructor(private _allianceService: AllianceService){

    }


    ontest(){
        console.log("ontest");
    }

    onNotify(alliance : Alliance){
        this.selectedAlliance = alliance;
    }

    onNotifyDelete(confitmation : boolean){
        if (confitmation){
            this._allianceService.deleteAlliance(this.deletedAlliance);
        }
        this.deletedAlliance = null;
    }

    editAlliance(al: Alliance){
        this.selectedAlliance = al;
    }

    deleteAlliance(al: Alliance){
        console.log("ontest delete");
        this.confirmMsg = "Are you sure you want to delete alliance " + al.name + "?";
        this.deletedAlliance = al;

        // this.confirmDelete.showErrorMessage();
        //this._allianceService.deleteAlliance(al);
        // this.alliances.splice(this.alliances.indexOf(al), 1);
    }

    cancelEditing(){
        this.selectedAlliance = null;
    }



    addAlliance(){
        console.log("AddAlliance");
        let newAlliance = new Alliance(this.name, this.login, this.email);
        this.name = "";
        this.login = "";
        this.email = "";
        this._allianceService.addAlliance(newAlliance);
        // this.alliances.push(newAlliance);

    }


}