/**
 * Created by rmochetc on 14.02.2017.
 */

import {Component, Output, EventEmitter, OnInit} from "@angular/core";
import {FormGroup, FormBuilder, Validators, FormControl} from "@angular/forms";
import {Attack} from "./attack";
import {Player} from "../player/player";
import {Village} from "../village/village";
import {HelpService} from "../services/helpNotification/help.service";
import {StompService} from "../services/helpNotification/stomp.service";
import {UserService} from "../services/user.service";
import {User} from "../user/user";
import {AttackArchiveService} from "../services/helpNotification/attack-archive.service";

@Component({
    selector: 'attack-archive',
    templateUrl: 'components/help/attackArchive.html',
    styleUrls: ['components/alliance/alliance.css']
})

export class AttackArchiveComponent implements OnInit{
    leader:User;
    attacks: any[];
    result:any;

    currentAttack: any;

    constructor(private attackArchiveService: AttackArchiveService, private userService: UserService){
        this.leader = new User();
    }

    public ngOnInit(): void {
        this.getLeader();
        this.getAttackArchive();
    }
    getAttackArchive():void {
        console.log("AttackArchive works!");
        this.attackArchiveService.getAttackArchive()
            .subscribe(
                attacks => {
                    this.attacks = attacks;
                    console.log(attacks);
                },
                error => console.log(error)
            );
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

    getById(at:any){
        console.log("get by id = " + at.uuid);
        this.attackArchiveService.getById(at.uuid)
            .subscribe(
                res => {
                    this.result = res;
                    console.log(this.result);
                    this.currentAttack = at;
                },
                error => console.log(error)
            );
    }

    setAttack(at:any){
        this.currentAttack = at;
    }

}

