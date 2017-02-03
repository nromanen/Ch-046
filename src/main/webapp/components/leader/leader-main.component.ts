/**
 * Created by vyach on 25.01.2017.
 */
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

import {Player} from '../player/player'
import {User} from '../user/user';
import {UserService} from "../services/user.service";
import {PlayerService} from "../services/player.service";


@Component({
    selector: "leader-main",
    templateUrl: "components/leader/leader-main.html",
    providers: [PlayerService, UserService]
})

export class LeaderMainComponent implements OnInit {
    leader:User;
    players:Player[];

    constructor(private playerService:PlayerService, private userService:UserService) {
        console.log(`LeaderMainComponent constructor is working`);
        this.leader = new User();
        this.players = [];
    }

    ngOnInit():void {
        console.log(`LeaderMainComponent ngOnInit() is working`);

        this.getLeader();
        this.getPlayersByAlliance();
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

    getPlayersByAlliance():void {
        console.log(`LeaderMainComponent getPlayersByAlliance() method is working`);

        this.playerService.getPlayersByAlliance()
            .subscribe(
                players => {
                    this.players = players;
                    console.log(`LeaderMainComponent getPlayersByAlliance() players value: ${JSON.stringify(this.players)}`);
                },
                error => console.log(error)
            );
    }
}