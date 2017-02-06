/**
 * Created by vyach on 13.01.2017.
 */

import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import {User} from "../user/user";
import {Observable} from "rxjs/Observable";

@Injectable()
export class UserService {

    userControllerUrl = "user";

    constructor(private http:Http) {
    }

    getUsersByAlliance() {
        console.log(`UserService.getUsersByAlliance() method is working.`);

        return this.http.get(`${this.userControllerUrl}/alliance-users`)
            .map(response => response.json());
    }

    getLeader() {
        console.log(`UserService.getLeader() method is working`);

        return this.http.get(`${this.userControllerUrl}`)
            .map(response => {
                console.log(`Leader value: ${JSON.stringify(response)}`);
                return response.json()
            });
    }

    addMember(member:User) {
        console.log(`UserService.addMember() method is working`);

        const body = JSON.stringify({
            uuid: null,
            login: member.login,
            email: member.email,
            isLeader: member.isLeader,
            alliance: member.alliance
        });
        console.log(`Member value is: ${body}`);

        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});

        return this.http.post(`${this.userControllerUrl}/add`, body, options)
            .map(response => response.json())
            .catch((error:any) => {
                    console.log(error);
                    return Observable.throw(error._body);
                }
            );
    }

    updateMember(member:User) {
        console.info(`UserService.updateMember() method is working`);

        const body = JSON.stringify({
            uuid: member.uuid,
            login: member.login,
            email: member.email,
            alliance: member.alliance,
            isLeader: member.isLeader
        });
        console.info(`Member value is: ${body}`);

        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});

        return this.http.put(`${this.userControllerUrl}/update/${member.uuid}`, body, options)
            .map(response => response.json())
            .catch((error:any) => {
                    console.log(error);
                    return Observable.throw(error._body);
                }
            );
    }

    deleteMember(member:User) {
        console.log(`UserService.updateMember() method is working`);

        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});

        return this.http.delete(`${this.userControllerUrl}/delete/${member.uuid}`, options)
            .map(ok => true)
            .catch((error:any) => {
                    console.log(error);
                    return Observable.throw(error._body);
                }
            );
    }
}

