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

    cancelEditing(){
        this.selectedAlliance = null;
    }

}