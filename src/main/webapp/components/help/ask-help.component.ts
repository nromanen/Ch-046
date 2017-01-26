/**
 * Created by rmochetc on 22.01.2017.
 */

import {HelpService} from "./help.service";
import {Component, Output, EventEmitter, OnInit} from "@angular/core";
import {FormGroup, FormBuilder, Validators, FormControl} from "@angular/forms";
import {Attack} from "./attack";
import {Player} from "../player/player";
import {Village} from "../village/village";
import {StompService} from "./stomp.service";

@Component({
    selector: 'ask-help',
    templateUrl: 'components/help/askHelp.html'
})

export class HelpComponent implements OnInit{

    // EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
    // ALLIANCE_NAME = /^[a-z]{3,9}$/;
    VILLAGE = /^[A-Za-z1-9.]{3,9}$/;
    DATE = /^[1-9/-0]{3,20}/;
    ENEMY = "Enter correct email, please!";
    NAME_ERROR = "Enter from 3 to 10 letters";
    LOGIN_ERROR = "Enter from 3 to 10 letters";

    player: Player;
    villages : Village[];
    successMessage: string = null;
    errorMessage: string = null;
    helpForm : FormGroup;


    constructor(private helpService:HelpService, private formBuilder: FormBuilder, private stompService: StompService){
        this.helpForm = this.formBuilder.group({
            'villageName' : ['', Validators.compose([Validators.required])],
            'enemy': ['',Validators.compose([Validators.required, Validators.pattern(this.VILLAGE)])],
            'timeAttack' : ['',Validators.compose([Validators.required, Validators.pattern(this.DATE)])]
        });

    }

    public ngOnInit(): void {
        this.getPlayer();
    }

    getPlayer(){
        this.helpService.getById()
            .subscribe(
                resp=>{
                    this.player = resp;
                    this.villages = this.player.villages;
                    console.log(this.player);
                    console.log(this.villages);
                }
            );


    }

    submitForm(value: any){
        console.log("Complex form: " + value);
        let newAttack = new Attack(value.villageName, value.enemy, value.timeAttack);
        this.send(newAttack);
        this.helpForm.controls['villageName'].setValue("");
        this.helpForm.controls['enemy'].setValue("");
        this.helpForm.controls['timeAttack'].setValue("");
    }

    public send(attack : Attack): void {

        this.helpService.addAttack(attack)
            .subscribe(
                resp => {
                    this.successMessage = "Ask help added successfully";
                    this.errorMessage = null;
                    this.stompService.send(this.player.login);
                },
                error =>  {
                    this.errorMessage = <any>error;
                    this.successMessage = null;
                }
            );
    }

    closeSuccess(){
        this.successMessage = null;
    }

    closeError(){
        this.errorMessage = null;
    }
}

