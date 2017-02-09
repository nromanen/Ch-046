import {Component, OnInit} from '@angular/core';

import {User} from '../user/user';
import {UserService} from "../services/user.service";


@Component({
    selector: "leader-manager",
    templateUrl: "components/leader/leader-manager.html",
    styleUrls: ["components/leader/leader-manager.css"]
})

export class LeaderManagerComponent implements OnInit {
    leader:User;
    users:User[];
    errorMessage: string;
    successMessage: string;

    selectedMember:User;
    deletedMember:User;
    confirmMsg:string;


    constructor(private userService:UserService) {
        console.log(`LeaderManagerComponent constructor is working`);

        this.leader = new User();
        this.users = [];
        this.errorMessage = null;
        this.successMessage = null;
        this.selectedMember= null;
        this.deletedMember= null;
        this.confirmMsg= null;
    }

    ngOnInit():void {
        console.log(`LeaderManagerComponent.ngOnInit() method is working`);
        this.getLeader();
        this.getUsersByAlliance();
    }

    getLeader() {
        console.log(`LeaderManagerComponent getLeader() method is working`);

        this.userService.getLeader()
            .subscribe(
                leader => {
                    this.leader = leader;
                    console.log(`LeaderManagerComponent getLeader() leader value: ${JSON.stringify(this.leader)}`);
                },
                error => console.log(error)
            );
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

    selectMember(member:User) {
        console.log(`usersComponent.selectMember() method is working.`);

        this.selectedMember = member;
    }

    closeSuccess(){
        this.successMessage = null;
    }

    closeError(){
        this.errorMessage = null;
    }

    addMember(member:User) {
        console.log(`LeaderManagerComponent.addMember() method is working`);
        member.alliance = this.leader.alliance;
        this.userService.addMember(member)
            .subscribe(
                user => {this.users.push(user);
                    this.errorMessage = null;
                    this.successMessage = "User added successfully";
                },
                error => {console.log(error);
                    this.successMessage = null;
                    this.errorMessage = <any>error;
                }
            );
    }

    updateMember(member:User) {
        console.info(`LeaderManagerComponent.updateMember() method is working. Member value: ${JSON.stringify(member)}`);
        if (member !== null) {
            this.userService.updateMember(member)
                .subscribe(
                    user => {
                        console.log(`LeaderManagerComponent.updateMember() user value: ${JSON.stringify(user)}`);
                        console.log(`User from array: ${JSON.stringify(this.users[this.users.indexOf(this.selectedMember)])}`)
                        this.users[this.users.indexOf(this.selectedMember)] = user;
                        this.selectedMember = null;
                        this.errorMessage = null;
                        this.successMessage = `User updated successfully`;
                    },
                    error => {
                        console.log(error);
                        this.selectedMember = null;
                        this.successMessage = null;
                        this.errorMessage = <any>error;
                    }
                );
        } else {
            this.selectedMember = member;
        }
    }

    deleteMember(member: User) {
        this.deletedMember = member;
        this.confirmMsg = `Are you going to delete alliance member: ${member.login}?`
    }
    
    onConfirmDelete(confirmation:boolean) {
        console.log(`LeaderManagerComponent.deleteMember() method is working`);
        if (confirmation) {
            this.userService.deleteMember(this.deletedMember)
                .subscribe(
                    status => {
                        if (status) {
                            this.users.splice(this.users.indexOf(this.deletedMember), 1);
                            this.errorMessage = null;
                            this.successMessage = `User ${this.deletedMember.login} deleted successfully`;
                            this.deletedMember = null;
                        }
                    },
                    error => {
                        console.log(error);
                        this.deletedMember = null;
                        this.successMessage = null;
                        this.errorMessage = <any>error;
                    }
                );
        } else {
            this.deletedMember = null;
        }
    }
}