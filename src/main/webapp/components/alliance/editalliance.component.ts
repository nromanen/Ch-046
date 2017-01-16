import {Component, Input, Output, EventEmitter} from "@angular/core";
import {Alliance} from "./alliance";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AllianceService} from "../services/alliance-service";
/**
 * Created by rmochetc on 14.01.2017.
 */

@Component({
    selector: 'edit-alliance',
    templateUrl: 'components/alliance/editAlliance.html',
    // providers: [AllianceService],
})

export class EditAllianceComponent {

    @Input() editedAlliance: Alliance;
    @Output() notify: EventEmitter<Alliance> = new EventEmitter<Alliance>();
    editForm : FormGroup;


    EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
    ALLIANCE_NAME = /^[a-z]{3,9}$/;
    USER_LOGIN = /^[a-z1-9]{3,9}$/;
    EMAIL_ERROR = "Enter correct email, please!";
    NAME_ERROR = "Enter from 3 to 10 letters";
    LOGIN_ERROR = "Enter from 3 to 10 letters";
    constructor(private fb: FormBuilder, private _allianceService: AllianceService){

    }

    ngOnInit(){
        this.editForm = this.fb.group({
            'allianceName' : [this.editedAlliance.name,  Validators.compose([Validators.required, Validators.pattern(this.ALLIANCE_NAME)])],
            'leaderLogin': [this.editedAlliance.leaderLogin,  Validators.compose([Validators.required, Validators.pattern(this.USER_LOGIN)])],
            'leaderEmail' : [this.editedAlliance.leaderEmail,  Validators.compose([Validators.required, Validators.pattern(this.EMAIL_REGEXP)])]

        });
    }

    updateAlliance(value: any){
        console.log("Edit form: " + value);
        let updatedAlliance = new Alliance(value.allianceName, value.leaderLogin, value.leaderEmail);
        updatedAlliance.uuid = this.editedAlliance.uuid;
        console.log(updatedAlliance);
        this._allianceService.updateAlliance(this.editedAlliance, updatedAlliance);
        //
        // this.editForm.setValue({
        //     allianceName : null,
        //     leaderLogin : null,
        //     leaderEmail : null
        // });
        // for (let name in this.editForm.controls) {
        //     this.editForm.controls[name].setErrors(null);
        // }
        this.notify.emit(null);
    }

    cancelEditing(){
        this.notify.emit(null);
    }



}
