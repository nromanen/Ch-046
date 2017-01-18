import {Component, OnInit} from '@angular/core';
import {User} from '../user/user';
import {UserService} from "../services/user.service";


@Component({
    selector: "leader-manager",
    templateUrl: "components/leader/leader.html", // todo add logic when click delete button
    providers: [UserService]
})

export class LeaderManagerComponent implements OnInit {
    leader:User;
    users:User[];

    constructor(public userService:UserService) {
        console.log(`LeaderManagerComponent constructor is working`);
    }

    ngOnInit():void {
        console.log(`LeaderManagerComponent.ngOnInit() method is working`);
        this.getUsersByAlliance();
    }

    getUsersByAlliance():void {
        console.log("LeaderManagerComponent.getUsersByAlliance() method is working");
        this.userService.getUsersByAlliance()
            .subscribe(
                users => {
                    this.users = users;
                    console.log(JSON.stringify(users));
                },
                error => console.log(error)
            );
    }

    addMember(member:User) {
        console.log(`LeaderManagerComponent.addMember() method is working`);
        member.alliance = "valhala"; // todo change to dynamic set alliance name
        this.userService.addMember(member)
            .subscribe(
                user => this.users.push(user),
                error => console.log(error)
            );
    }

    updateMember(member:User) {
        console.log(`LeaderManagerComponent.updateMember() method is working`);
        this.userService.updateMember(member)
            .subscribe(
                user => {
                    console.log(`LeaderManagerComponent.updateMember() user value: ${JSON.stringify(user)}`);
                    this.users[this.users.indexOf(user)] = user;
                    console.log(`User from array: ${JSON.stringify(this.users[this.users.indexOf(user)])}`)
                },
                error => console.log(error)
            );
    }

    deleteMember(member:User) {
        console.log(`LeaderManagerComponent.deleteMember() method is working`);
        this.userService.deleteMember(member)
            .subscribe(
                status => {
                    if (status) {
                        this.users.splice(this.users.indexOf(member), 1);
                    }
                },
                error => console.log(error)
            );
    }
}