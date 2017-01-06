/**
 * Created by rmochetc on 06.01.2017.
 */

export class Alliance {

    name: string;
    leaderLogin: string;
    leaderEmail: string;
    uuid: string;



    constructor(uuid: string, name: string, leaderLogin: string, leaderEmail: string){
        this.name = name;
        this.leaderEmail = leaderEmail;
        this.leaderLogin = leaderLogin;
        this.uuid = uuid;
    }


}
