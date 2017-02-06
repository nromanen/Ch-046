export class User {
    uuid:string;
    login:string;
    email:string;
    alliance:string;
    isLeader:boolean;

    constructor(login?:string, email?:string, uuid?:string, alliance?:string, isLeader?:boolean) {
        this.uuid = uuid || null;
        this.login = login;
        this.email = email;
        this.alliance = alliance || null;
        this.isLeader = isLeader;
    }

}