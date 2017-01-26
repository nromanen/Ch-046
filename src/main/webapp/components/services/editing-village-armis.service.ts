import {Injectable} from "@angular/core";
import {Village} from "../village/village";
import {Army} from "../army/army";
/**
 * Created by Oleg on 18.01.2017.
 */

@Injectable()
export class EditingVillageArmiesService {
    public village: Village;
    public armies: Array<Army>;

    constructor() {
        this.armies = [];
    }
}