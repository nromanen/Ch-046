import {Component, Output, EventEmitter} from "@angular/core";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {User} from "./user";

@Component({
    selector: 'add-member',
    templateUrl: 'components/user/add-member-form.html',
    styleUrls: ['components/user/add-member-form.css']
})

export class MemberForm {

    memberForm: FormGroup;
    @Output() addMemberForm:EventEmitter<any> = new EventEmitter<any>();

    USER_LOGIN = /^[a-z1-9]{3,9}$/;
    EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?\.[a-z0-9]{2,}$/i;
    LOGIN_ERROR = "Login should be from 3 to 10 letters";
    EMAIL_ERROR = "Enter correct email, please!";

    constructor(private fb:FormBuilder) {
        console.log(`MemberForm.constructor() is working`);
        this.memberForm = fb.group({
            'uuid': [''],
            'login': ['', Validators.compose([Validators.required, Validators.pattern(this.USER_LOGIN)])],
            'email': ['', Validators.compose([Validators.required, Validators.pattern(this.EMAIL_REGEXP)])],
            'alliance': [''],
            'isLeader':['']
        });
    }

    addMember(value:any) {
        console.log(`MemberForm.addMember() method is working`);
        console.log(`Form value is: ${JSON.stringify(value)}`);

        let member = new User(value.login, value.email, value.uuid, value.alliance, value.isLeader);
        this.addMemberForm.emit(member);
        this.memberForm.reset();
    }
}