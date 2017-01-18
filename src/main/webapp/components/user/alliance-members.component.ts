/**
 * Created by vyach on 16.01.2017.
 */

import {Component, Input, Output, EventEmitter} from '@angular/core';
import {User} from '../user/user';

@Component({
    selector: "alliance-members",
    templateUrl: "components/user/alliance-members.html",
})

export class AllianceMembersComponent {
    @Input() allianceMembers:User[];
    @Output() actionUpdate: EventEmitter<User> = new EventEmitter<User>();
    @Output() actionDelete: EventEmitter<User> = new EventEmitter<User>();
    
    selectedMember:User;
    
    selectMember(member: User) {
        console.log(`AllianceMembersComponent.selectMember() method is working.`);

        this.selectedMember = member;
    }
    
    updateMember(editedMember: User) {
        console.log(`AllianceMembersComponent.updateMember() method is working. Edited member is: ${JSON.stringify(editedMember)}`);

        if (editedMember === null) {
            this.selectedMember = null;
        } else {
            this.actionUpdate.emit(editedMember);
            this.selectedMember = null;
        }
    }
    
    deleteMember(member: User) {
        console.log(`AllianceMembersComponent.deleteMember() method is working. Member is: ${JSON.stringify(member)}`);

        this.actionDelete.emit(member);
    }
}
