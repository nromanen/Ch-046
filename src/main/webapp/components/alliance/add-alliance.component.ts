import {Component, Output, EventEmitter} from "@angular/core";
import {Alliance} from "./alliance";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";

@Component({
    selector: 'add-alliance',
    templateUrl: 'components/alliance/allianceForm.html',
    styleUrls: ['components/alliance/allianceForm.css']
})

export class AllianceForm{

    @Output() notify: EventEmitter<Alliance> = new EventEmitter<Alliance>();

    EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
    ALLIANCE_NAME = /^[a-z]{3,9}$/;
    USER_LOGIN = /^[a-z1-9]{3,9}$/;

    complexForm : FormGroup;

    constructor(private fb: FormBuilder){
        this.complexForm = fb.group({
            'allianceName' : [null,  Validators.compose([Validators.required, Validators.pattern(this.ALLIANCE_NAME)])],
            'leaderLogin': [null,  Validators.compose([Validators.required, Validators.pattern(this.USER_LOGIN)])],
            'leaderEmail' : [null,  Validators.compose([Validators.required, Validators.pattern(this.EMAIL_REGEXP)])]
    });
    }
    submitForm(value: any){
        console.log("Complex form: " + value);
        console.log(value.allianceName);
        let newAlliance = new Alliance(value.allianceName, value.leaderLogin, value.leaderEmail);
        this.notify.emit(newAlliance);
        this.complexForm.reset();
    }
}