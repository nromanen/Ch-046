/**
 * Created by Micro on 2/13/2017.
 */
export class Credentials {
    login:string;
    password:string;
    letPasrs: boolean;

    constructor(login?:string, password?:string, letPars?: boolean) {
        this.login = login;
        this.password = password;
        this.letPasrs = letPars;
    }
}