import {Component} from 'angular2/core';
import {User} from '../user/user';
import {UserService} from "../../services/user/user-service";

@Component({
    selector: "leader-manage",
    templateUrl: "components/leader/leader.html", // todo add logic when click delete button
    providers: [UserService],
})

export class LeaderManageComponent {
    leader: User;
    users: User[];

    constructor(public userService: UserService) {
    }
    
    getAllianceUsers(): User[] {
        this.users = [
            new User("user1", "111", "sdf@ds.com", "al1"),
            new User("user2", "222", "sdf@ds.com", "al2")
        ];
        return this.users;
        // return this.userService.getUsersByAlliance();
    }
}