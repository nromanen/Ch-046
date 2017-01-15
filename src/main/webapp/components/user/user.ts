export class User {
    login: string;
    password: string;
    email: string;
    alliance: string;
    uuid: string;


    constructor(login:string, password:string, email:string, alliance:string) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.alliance = alliance;
    }
}