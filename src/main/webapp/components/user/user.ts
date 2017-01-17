import {Role} from "../roles/role";
import {Player} from "../player/player";
/**
 * Created by okunetc on 17.01.2017.
 */
export class User{
    login:string;
    password:string;
    email:string;
    roles:Role[];
    player:Player;
}
