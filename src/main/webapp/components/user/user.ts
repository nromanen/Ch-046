export class User {
    uuid: string;
    login: string;
    email: string;
    alliance: string;

    constructor(login:string, email:string, uuid?:string, alliance?:string) {
        this.uuid = uuid || null;
        this.login = login;
        this.email = email;
        this.alliance = alliance || null;
    }
    
}