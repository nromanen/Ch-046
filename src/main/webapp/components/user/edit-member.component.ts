import {Component, Input, Output, EventEmitter} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "./user";

/**
 * Created by rmochetc on 14.01.2017.
 */

@Component({
    selector: 'edit-member',
    templateUrl: 'components/user/edit-member-form.html',
    styleUrls: ['components/user/edit-member-form.css']
})

export class EditMemberComponent {

    memberForm: FormGroup;
    @Input() editedMember: User;
    @Input() isCurrentPlayer: boolean;
    @Output() editMemberForm:EventEmitter<User> = new EventEmitter<User>();

    USER_LOGIN = /^[a-z1-9]{3,9}$/;
    EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?\.[a-z0-9]{2,}$/i;
    LOGIN_ERROR = "Enter from 3 to 10 letters";
    EMAIL_ERROR = "Enter correct email, please!";

    constructor(private fb:FormBuilder) {
        console.log(`EditMemberComponent.constructor() is working`);

    }

    ngOnInit() {
        console.log(`EditMemberComponent.ngOnInit() is working`);

        this.memberForm = this.fb.group({
            'login': [this.editedMember.login, Validators.compose([Validators.required, Validators.pattern(this.USER_LOGIN)])],
            'email': [this.editedMember.email, Validators.compose([Validators.required, Validators.pattern(this.EMAIL_REGEXP)])],
            'isLeader':[this.editedMember.isLeader]
        });
    }

    updateMember(value:any) {
        console.log(`EditMemberComponent.updateMember() is working. Member value is: ${JSON.stringify(value)}`);

        if (this.hasMemberDataChange(value)) {
            let member:User = new User(value.login, value.email, this.editedMember.uuid, this.editedMember.alliance, value.isLeader);
            this.editMemberForm.emit(member);
        } else {
            this.editMemberForm.emit(null);
        }
        this.memberForm.reset();
    }

    hasMemberDataChange(value:any):boolean {
        return value.login != this.editedMember.login
            || value.email != this.editedMember.email
            || value.isLeader != this.editedMember.isLeader;
    }

    cancelEditing() {
        console.log(`EditMemberComponent.cancelEditing() is working`);

        this.editMemberForm.emit(null);
    }
}
