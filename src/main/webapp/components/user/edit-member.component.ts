import {Component, Input, Output, EventEmitter} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "./user";

/**
 * Created by rmochetc on 14.01.2017.
 */

@Component({
    selector: 'edit-member',
    templateUrl: 'components/user/edit-member-form.html',
    
})

export class EditMemberComponent {

    memberForm: FormGroup;
    @Input() editedMember: User;
    @Output() editMemberForm:EventEmitter<any> = new EventEmitter<any>();

    USER_LOGIN = /^[a-z1-9]{3,9}$/;
    EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
    LOGIN_ERROR = "Enter from 3 to 10 letters";
    EMAIL_ERROR = "Enter correct email, please!";

    constructor(private fb:FormBuilder) {
        console.log(`EditMemberComponent.constructor() is working`);

    }

    ngOnInit() {
        console.log(`EditMemberComponent.ngOnInit() is working`);

        this.memberForm = this.fb.group({
            'uuid': [this.editedMember.uuid],
            'login': [this.editedMember.login, Validators.compose([Validators.required, Validators.pattern(this.USER_LOGIN)])],
            'email': [this.editedMember.email, Validators.compose([Validators.required, Validators.pattern(this.EMAIL_REGEXP)])],
            'alliance': [this.editedMember.alliance]
        });
    }

    updateMember(value:any) {
        console.log(`EditMemberComponent.updateMember() is working. Member value is: ${JSON.stringify(value)}`);

        let member = new User(value.login, value.email, value.uuid, value.alliance);
        this.editMemberForm.emit(member);
        this.memberForm.reset();
    }

    cancelEditing() {
        console.log(`EditMemberComponent.cancelEditing() is working`);

        this.editMemberForm.emit(null);
    }
}
