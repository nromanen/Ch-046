
import {Component} from "@angular/core";
import {Alliance} from "./alliance";
import {AllianceService} from "../services/alliance-service";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";

@Component({
    selector: 'add-alliance',
    templateUrl: 'components/alliance/allianceForm.html',
})

export class AllianceForm{


    complexForm : FormGroup;

    EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
    ALLIANCE_NAME = /^[a-z]{3,9}$/;
    USER_LOGIN = /^[a-z1-9]{3,9}$/;
    EMAIL_ERROR = "Enter correct email, please!";
    NAME_ERROR = "Enter from 3 to 10 letters";
    LOGIN_ERROR = "Enter from 3 to 10 letters";
    constructor(private fb: FormBuilder, private _allianceService: AllianceService){
        this.complexForm = fb.group({
            'allianceName' : [null,  Validators.compose([Validators.required, Validators.pattern(this.ALLIANCE_NAME)])],
            'leaderLogin': [null,  Validators.compose([Validators.required, Validators.pattern(this.USER_LOGIN)])],
            'leaderEmail' : [null,  Validators.compose([Validators.required, Validators.pattern(this.EMAIL_REGEXP)])]

    });
    }

    // constructor(fb: FormBuilder, private _allianceService: AllianceService){
    //     this.complexForm = fb.group({
    //         'firstName' : [null, Validators.required],
    //         'lastName': [null,  Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(10)])],
    //         'gender' : [null, Validators.required],
    //         'hiking' : [false],
    //         'running' : [false],
    //         'swimming' : [false]
    //     });
    //     console.log(this.complexForm);
    // }


    submitForm(value: any){
        console.log("Complex form: " + value);
        console.log(value.allianceName);
        let newAlliance = new Alliance(value.allianceName, value.leaderLogin, value.leaderEmail);
        this._allianceService.addAlliance(newAlliance);
        this.complexForm.setValue({
            allianceName : null,
            leaderLogin : null,
            leaderEmail : null
        });
        for (let name in this.complexForm.controls) {
            this.complexForm.controls[name].setErrors(null);
        }

    }


    // submitForm1(form: any): void{
    //     let name: string = form.allianceName;
    //     let login: string = form.leaderLogin;
    //     let email: string = form.leaderEmail;
    //     console.log(name);
    //     console.log(login);
    //     console.log(email);
    //
    //     console.log("AddAlliance in the form");
    //     let newAlliance = new Alliance(name, login, email);
    //     this._allianceService.addAlliance(newAlliance);
    //
    //     for (let name in form.controls) {
    //         form.controls[name].updateValue('');
    //         //form.controls[name].setErrors(null);
    //     }
    // }
}