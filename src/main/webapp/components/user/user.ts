export class User {
    uuid: string;
    login: string;
    email: string;
    alliance: string;
    role: string;

    constructor(login: string, email: string, uuid?: string, alliance?: string, role?: boolean) {
        this.uuid = uuid || null;
        this.login = login;
        this.email = email;
        this.alliance = alliance || null;
        this.role = role==true ? 'LEADER': null;
    }

}