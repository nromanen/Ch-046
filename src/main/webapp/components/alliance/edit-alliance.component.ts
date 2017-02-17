import {Component, Input, Output, EventEmitter} from "@angular/core";
import {Alliance} from "./alliance";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
    selector: 'edit-alliance',
    templateUrl: 'components/alliance/editAlliance.html',
    styleUrls: ['components/alliance/allianceForm.css']
})

export class EditAllianceComponent {

    EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
    ALLIANCE_NAME = /^[a-z]{3,9}$/;
    USER_LOGIN = /^[a-z1-9]{3,9}$/;

    editForm : FormGroup;

    @Input() editedAlliance: Alliance;
    @Output() notify: EventEmitter<Alliance> = new EventEmitter<Alliance>();

    constructor(private formBuilder: FormBuilder){
    }

    ngOnInit(){
        this.editForm = this.formBuilder.group({
            'allianceName' : [this.editedAlliance.name,  Validators.compose([Validators.required, Validators.pattern(this.ALLIANCE_NAME)])],
            'leaderLogin': [this.editedAlliance.leaderLogin,  Validators.compose([Validators.required, Validators.pattern(this.USER_LOGIN)])],
            'leaderEmail' : [this.editedAlliance.leaderEmail,  Validators.compose([Validators.required, Validators.pattern(this.EMAIL_REGEXP)])]

        });
    }

    updateAlliance(value: any){
        console.log( value);
        let updatedAlliance = new Alliance(value.allianceName, value.leaderLogin, value.leaderEmail);
        updatedAlliance.allianceUuid = this.editedAlliance.allianceUuid;
        updatedAlliance.leaderUuid = this.editedAlliance.leaderUuid;
        this.notify.emit(updatedAlliance);
    }

    cancelEditing(){
        this.notify.emit(null);
    }
}
