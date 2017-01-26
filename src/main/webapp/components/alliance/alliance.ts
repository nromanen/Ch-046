/**
 * Created by rmochetc on 06.01.2017.
 */

export class Alliance {

    allianceUuid: string;
    leaderUuid: string;
    name: string;
    leaderLogin: string;
    leaderEmail: string;

    constructor( name: string, leaderLogin: string, leaderEmail: string){
        this.name = name;
        this.leaderEmail = leaderEmail;
        this.leaderLogin = leaderLogin;
    }
}
