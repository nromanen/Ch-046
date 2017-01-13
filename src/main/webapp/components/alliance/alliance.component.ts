
import {Alliance} from "./alliance";
// import {ErrorMessage} from "../modal_window/modal";
import {Component} from "@angular/core";
import {AllianceService} from "../services/alliance-service";


@Component({
    selector: 'my-alliance',
    templateUrl: 'components/alliance/alliance.html',
    // providers: [AllianceService],
})

export class AllianceComponent{

    alliances: Array<Alliance> = [new Alliance('Valhala', 'borg', 'test@net.com'), new Alliance('Torr', 'viking', 'test@nmetacom')];

    selectedAlliance: Alliance = null;
    name: string;
    login: string;
    email: string;

    editName: string;
    editLogin: string;
    editEmail: string;

   // @ViewChild(ErrorMessage) errorMsg: ErrorMessage;  // ErrorMessage is a ViewChild
   //
    constructor(private _allianceService: AllianceService){

    }

    static ontest(){
        console.log("ontest");
        // this.errorMsg.showErrorMessage("Are you sure you want to delete this alliance?");
    }

    editAlliance(al: Alliance){
        this.selectedAlliance = al;
        this.editName = "TestUpdate";
        this.editLogin = "TestUpdateLogin";
        this.editEmail = "TestUpdate@Email.com";
    }

    deleteAlliance(al: Alliance){
        this._allianceService.deleteAlliance(al);
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

    updateAlliance() {
        console.log("update alliance");

        let updatedAlliance = new Alliance(this.editName, this.editLogin, this.editEmail);
        updatedAlliance.uuid = this.selectedAlliance.uuid;
        console.log(updatedAlliance);
        this._allianceService.updateAlliance(this.selectedAlliance, updatedAlliance);
        this.selectedAlliance = null;
        // this.alliances[this.alliances.indexOf(this.selectedAlliance)] = updatedAlliance;
    }

}