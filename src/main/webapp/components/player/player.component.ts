import {Component} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
/**
 * Created by okunetc on 13.01.2017.
 */

@Component({
    selector: 'player',
    templateUrl : 'components/player/player.html'
})
export class PlayerComponent{

    id;
    constructor(private route:ActivatedRoute){
        this.route.params.subscribe((param:any)=>
        {
            this.id=param['id'];
        });


    }

}
