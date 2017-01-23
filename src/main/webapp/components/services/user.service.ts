/**
 * Created by vyach on 13.01.2017.
 */

import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import {User} from "../user/user.ts";
import {Observable} from "rxjs/Observable";

@Injectable()
export class UserService {

    userControllerUrl = "user";

    constructor(private http:Http) {
    }

    getUsersByAlliance():Observable<User[]> {
        console.log(`UserService.getUsersByAlliance() method is working`);

        return this.http.get(`${this.userControllerUrl}/alliance-users/valhala`) // todo change to dynamic name
            .map(response => response.json()
            );
    }

    addMember(member:User):Observable<User> {
        console.log(`UserService.addMember() method is working`);
        
        const body = JSON.stringify({
            uuid: null,
            login: member.login,
            email: member.email,
            alliance: "valhala" // todo change to dynamic
        });
        console.log(`Member value is: ${body}`);
        
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});

        return this.http.post(`${this.userControllerUrl}/add`, body, options)
            .map(response => response.json());
    }

    updateMember(member:User):Observable<User> {
        console.log(`UserService.updateMember() method is working`);

        const body = JSON.stringify({
            uuid: member.uuid,
            login: member.login,
            email: member.email,
            alliance: member.alliance
        });
        console.log(`Member value is: ${body}`);

        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});

        return this.http.put(`${this.userControllerUrl}/update/${member.uuid}`, body, options)
            .map(response => response.json());
    }

    deleteMember(member:User):Observable<boolean> {
        console.log(`UserService.updateMember() method is working`);

        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});

        return this.http.delete(`${this.userControllerUrl}/delete/${member.uuid}`, options)
            .map(ok => true);

    }
}

