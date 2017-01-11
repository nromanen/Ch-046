import {Component} from 'angular2/core'
import {AllianceService} from "./alliance-service";
import {Alliance} from "./alliance";


@Component({
    selector: 'my-alliance',
    templateUrl: 'app/alliance/alliance.html',
    providers: [AllianceService],
})

export class AllianceComponent{

    alliances: Array<Alliance> = [];

    selectedAlliance: Alliance = null;
    name: string;
    login: string;
    email: string;

    editName: string;
    editLogin: string;
    editEmail: string;

    constructor(private _allianceService: AllianceService){

    }

    editAlliance(al: Alliance){
        this.selectedAlliance = al;
        this.editName = al.name;
        this.editLogin = al.leaderLogin;
        this.editEmail = al.leaderEmail;
    }

    deleteAlliance(al: Alliance){
        this._allianceService.deleteAlliance(al);
    }

    cancelEditing(){
        this.selectedAlliance = null;
    }

    addAlliance(){
        console.log("AddAlliance");
        var newAlliance = new Alliance(this.name, this.login, this.email);
        this.name = "";
        this.login = "";
        this.email = "";
        this._allianceService.addAlliance(newAlliance);

    }

    updateAlliance() {
        console.log("update alliance")
        var updatedAlliance = new Alliance(this.editName, this.editLogin, this.editEmail);
        this._allianceService.updateAlliance(this.selectedAlliance, updatedAlliance);
    }

}