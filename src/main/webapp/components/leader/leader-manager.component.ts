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
    errorMessage: string = null;
    successMessage: string = null;

    selectedMember:User = null;
    deletedMember:User = null;
    confirmMsg:string = null;


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
        member.alliance = "valhala"; // todo change to dynamic set alliance name
        this.userService.addMember(member)
            .subscribe(
                user => {this.users.push(user);
                this.successMessage = "User added successfully";
                this.errorMessage = null;
                },
                error => {console.log(error);
                    this.errorMessage = <any>error;
                    this.successMessage = null;
                }
            );
    }

    updateMember(member:User) {
        console.log(`LeaderManagerComponent.updateMember() method is working. Member value: ${JSON.stringify(member)}`);
        if (member !== null) {
            this.userService.updateMember(member)
                .subscribe(
                    user => {
                        console.log(`LeaderManagerComponent.updateMember() user value: ${JSON.stringify(user)}`);
                        console.log(`User from array: ${JSON.stringify(this.users[this.users.indexOf(this.selectedMember)])}`)
                        this.users[this.users.indexOf(this.selectedMember)] = user;
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
                            this.deletedMember = null;
                        }
                    },
                    error => console.log(error)
                );
        } else {
            this.deletedMember = null;
        }
    }
}