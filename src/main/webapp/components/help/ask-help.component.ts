/**
 * Created by rmochetc on 22.01.2017.
 */

import {HelpService} from "./help.service";
import {Component, Output, EventEmitter} from "@angular/core";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {Attack} from "./attack";
import {Player} from "../player/player";
import {Village} from "../village/village";


@Component({
    selector: 'ask-help',
    templateUrl: 'components/help/askHelp.html'
})

export class HelpComponent{
    player: Player;
    villages : Array<Village>;

    constructor(private helpService:HelpService, private fb: FormBuilder){
        this.helpForm = this.fb.group({
            'villageName' : ['', Validators.compose([Validators.required])],
            'enemy': ['',Validators.compose([Validators.required, Validators.pattern(this.VILLAGE)])],
            'timeAttack' : ['',Validators.compose([Validators.required, Validators.pattern(this.VILLAGE)])]
        });

        // var socket = new SockJS('http://localhost:8080/portfolio');
        // this.stompClient = Stomp.over(socket);
        // this.stompClient.connect("guest", "guest", function(frame) {
        //     console.log('Connected: ' + frame);
        //     this.stompClient.subscribe('http://localhost:8080/topic/greeting', function(greeting) {
        //         console.log("from from", greeting);
        //     });
        // }, function (err) {
        //     console.log('err', err);
        // });

    }

    ngOnInit() {
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

    helpForm : FormGroup;
    // EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
    // ALLIANCE_NAME = /^[a-z]{3,9}$/;
    VILLAGE = /^[A-Za-z1-9.]{3,9}$/;
    ENEMY = "Enter correct email, please!";
    NAME_ERROR = "Enter from 3 to 10 letters";


    LOGIN_ERROR = "Enter from 3 to 10 letters";


    submitForm(value: any){
        console.log("Complex form: " + value);
        console.log(value.allianceName);
        let newAttack = new Attack(value.allianceName, value.leaderLogin, value.leaderEmail);
        console.log(newAttack);
        // this.notify.emit(newAttack);
        // this.complexForm.reset();
    }
}

