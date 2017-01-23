/**
 * Created by rmochetc on 22.01.2017.
 */

import {Component} from "@angular/core";
import {HelpService} from "./help.service";
@Component({
    selector: 'ask-help',
    templateUrl: 'components/help/askHelp.html'
})

export class HelpComponent{

    constructor(private helpService:HelpService){

    }

    ngOnInit(): void {
        this.helpService.getById()
            .subscribe(
                resp=>{
                    console.log(resp);
                }
            );
    }

}