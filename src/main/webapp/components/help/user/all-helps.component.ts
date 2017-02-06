/**
 * Created by rmochetc on 26.01.2017.
 */

import {Component} from "@angular/core";
import {HelpService} from "../../services/helpNotification/help.service";

@Component({
    selector: 'user-all-help',
    templateUrl: 'components/help/user/allHelps.html'
})

export  class UserAllHelps{

    attacks: any[];

    constructor(private helpService:HelpService){
    }


    public ngOnInit(): void {
        this.getActiveHelp();
    }

    getActiveHelp(){
        this.helpService.getActiveHelp()
            .subscribe(
                resp=>{
                    this.attacks = resp;
                    console.log(this.attacks);
                }
            );


    }

}