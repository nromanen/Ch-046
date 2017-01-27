import {Village} from "../village/village";
/**
 * Created by rmochetc on 06.01.2017.
 */

export class Attack {

    village: string;
    enemy: string;
    timeAttack: string;




    constructor( villageName: string, enemy: string, timeAttack: string){
        this.village = villageName;
        this.enemy = enemy;
        this.timeAttack = timeAttack;

    }


}
