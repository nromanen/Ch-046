/**
 * Created by rmochetc on 22.01.2017.
 */

import {Component, Output, EventEmitter, OnInit} from "@angular/core";
import {FormGroup, FormBuilder, Validators, FormControl} from "@angular/forms";
import {Attack} from "./attack";
import {Player} from "../player/player";
import {Village} from "../village/village";
import {HelpService} from "../services/helpNotification/help.service";
import {StompService} from "../services/helpNotification/stomp.service";
import {UserService} from "../services/user.service";
import {User} from "../user/user";

@Component({
    selector: 'ask-help',
    templateUrl: 'components/help/askHelp.html',
    styleUrls: ['components/help/help.css']
})

export class HelpComponent implements OnInit{

    player: Player;
    villages : Village[];
    errorMessage: string = null;
    helpForm : FormGroup;
    leader:User;

       constructor(private helpService:HelpService, private formBuilder: FormBuilder, private stompService: StompService, private userService: UserService){
        this.helpForm = this.formBuilder.group({
            'villageName' : ['', Validators.compose([Validators.required])],
            'enemy': ['', Validators.compose([Validators.required])],
            'date' : ['',Validators.compose([Validators.required])]
        });
           this.leader = new User();
    }

    public ngOnInit(): void {
        this.getPlayer();
        this.getLeader();
    }

    getPlayer(){
        this.helpService.getById()
            .subscribe(
                resp=>{
                    this.player = resp;
                    this.villages = this.player.villages;
                }
            );
    }

    getLeader():void {
        console.log(`LeaderMainComponent getLeader() method is working`);

        this.userService.getLeader()
            .subscribe(
                leader => {
                    this.leader = leader;
                    console.log(`LeaderMainComponent getLeader() leader value: ${JSON.stringify(this.leader)}`);
                },
                error => console.log(error)
            );
    }

    submitForm(value: any){

        let newAttack = new Attack(value.villageName, value.enemy, value.date);
        this.send(newAttack);
        this.helpForm.controls['villageName'].setValue("");
        this.helpForm.controls['enemy'].setValue("");
        this.helpForm.controls['date'].setValue("");
    }

    public send(attack : Attack): void {
        this.helpService.addAttack(attack)
            .subscribe(
                resp => {
                    this.errorMessage = null;
                    this.stompService.send();
                },
                error =>  {
                    this.errorMessage = <any>error._body;
                }
            );
    }

    closeError(){
        this.errorMessage = null;
    }
}

