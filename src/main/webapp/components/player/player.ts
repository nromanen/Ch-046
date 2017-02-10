import {Race} from "../race/race";
import {Village} from "../village/village";
import {Alliance} from "../alliance/alliance";
/**
 * Created by Oleg on 14.01.2017.
 */

export class Player {

    login: string;
    password: string;
    email: string;
    race: Race;
    villages: Village[];
    uuid: string;
    isLeader: boolean;
    alliance:Alliance;

    constructor() {

    }
}