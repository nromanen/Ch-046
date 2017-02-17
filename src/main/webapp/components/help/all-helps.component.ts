/**
 * Created by rmochetc on 26.01.2017.
 */

import {Component} from "@angular/core";
import {HelpService} from "../services/helpNotification/help.service";
import {UserService} from "../services/user.service";
import {User} from "../user/user";

@Component({
    selector: 'ask-help',
    templateUrl: 'components/help/allHelps.html',
    styleUrls: ['components/help/help.css']
})

export  class AllHelps{

    attacks: any[];
    leader:User;

    constructor(private helpService:HelpService, private userService: UserService){
        this.leader = new User();
    }

    public ngOnInit(): void {
        this.getActiveHelp();
        this.getLeader();
    }

    getLeader():void {
        console.log(`LeaderMainComponent getLeader() method is working`);

        this.userService.getLeader()
            .subscribe(
                leader => {
                    this.leader = leader;
                    console.log(`LeaderMainComponent getLeader() leader value: ${JSON.stringify(this.leader)}`);
                },
                error => console.log(error)
            );
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