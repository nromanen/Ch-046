/**
 * Created by rmochetc on 22.01.2017.
 */

import 'rxjs/Rx';
import {Injectable} from "@angular/core";
import {Http, Headers, Response} from "@angular/http";
import {Observable} from 'rxjs/Rx';
import {Player} from "../../player/player";
import {Attack} from "../../help/attack";
import {Alliance} from "../../alliance/alliance";

@Injectable()
export class HelpService {

    url = 'askhelp';

    constructor(private http: Http) {
    }

    getById(): Observable<Player> {
        return this.http.get(this.url)
            .map(res => res.json());
    }

    addAttack(attack: Attack):  Observable<Attack> {
        let time = (attack.timeAttack + "");
        const body = JSON.stringify({
            villageId: attack.village,
            enemy: attack.enemy,
            attackTime: time
        });

        console.log("BODY");
        console.log(body);
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this.http.post(this.url, body, {
            headers: headers
        }).map((res: Response) => res.json())
            .catch(
                (error: any) => {
                        return Observable.throw(error);
                }
            );
    }

    getActiveHelp(): Observable<any[]> {
        console.log(this.url);
        return this.http.get("allAttack")
            .map(res => res.json());
    }

    getAlliance(): Observable<Alliance> {
        console.log("INIT ALLIANCE");
        return this.http.get("user/helpInit/")
            .map(res => res.json());
    }

}