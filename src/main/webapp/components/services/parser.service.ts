/**
 * Created by vyach on 13.01.2017.
 */

import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import {User} from "../user/user";
import {Observable} from "rxjs/Observable";
import {Credentials} from "../modal_parsing_window/Credentials";

@Injectable()
export class ParserService {

    parserControllerUrl = "parser";

    constructor(private http:Http) {
    }

    pars(creds:Credentials) {

        const body = JSON.stringify({
            login: creds.login,
            password: creds.password
        });
        console.log(`Creds value is: ${body}`);

        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});

        return this.http.post(`${this.parserControllerUrl}`, body, options)
            .map(response => response.json())
            .catch((error:any) => {
                    console.log(error);
                    return Observable.throw(error._body);
                }
            );
    }
}

