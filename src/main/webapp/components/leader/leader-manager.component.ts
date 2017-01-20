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
    allianceMembers:User[];

    selectedMember:User = null;

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
                allianceMembers => {
                    this.allianceMembers = allianceMembers;
                    console.log(JSON.stringify(allianceMembers));
                },
                error => console.log(error)
            );
    }

    selectMember(member:User) {
        console.log(`AllianceMembersComponent.selectMember() method is working.`);

        this.selectedMember = member;
    }

    addMember(member:User) {
        console.log(`LeaderManagerComponent.addMember() method is working`);
        member.alliance = "valhala"; // todo change to dynamic set alliance name
        this.userService.addMember(member)
            .subscribe(
                user => this.allianceMembers.push(user),
                error => console.log(error)
            );
    }

    updateMember(member:User) {
        console.log(`LeaderManagerComponent.updateMember() method is working. Member value: ${JSON.stringify(member)}`);
        if (member !== null) {
            this.userService.updateMember(member)
                .subscribe(
                    user => {
                        console.log(`LeaderManagerComponent.updateMember() user value: ${JSON.stringify(user)}`);
                        console.log(`User from array: ${JSON.stringify(this.allianceMembers[this.allianceMembers.indexOf(this.selectedMember)])}`)
                        this.allianceMembers[this.allianceMembers.indexOf(this.selectedMember)] = user;
                        this.selectedMember = null;
                    },
                    error => {
                        console.log(error);
                        this.selectedMember = null;
                    }
                );
        } else {
            this.selectedMember = member;
        }
    }

    deleteMember(member:User) {
        console.log(`LeaderManagerComponent.deleteMember() method is working`);
        this.userService.deleteMember(member)
            .subscribe(
                status => {
                    if (status) {
                        this.allianceMembers.splice(this.allianceMembers.indexOf(member), 1);
                    }
                },
                error => console.log(error)
            );
    }
}