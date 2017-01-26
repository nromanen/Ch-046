/**
 * Created by okunetc on 16.01.2017.
 */
import {Component} from "@angular/core";
import {StompService} from "../help/stomp.service";
@Component(
    {
        selector:'player-head',
        template:`<nav>
<div class="nav-wrapper">
<a href="#!" class="brand-logo">Logo</a>
<ul class="right hide-on-med-and-down">
<li><a href="#" >Add village</a></li>
<li><a href="#">All villages</a></li>
<li><a routerLink="/help" routerLinkActive="active">Ask help</a></li>
<li><a routerLink="/allHelps" routerLinkActive="active">Attacks</a></li>
<li><a href="logout" >Log out</a></li>
</ul>
</div>
</nav>
<div id="note" *ngIf="showNotif" routerLink="/allHelps" routerLinkActive="active" style="

    position: absolute;
    width: 30%;
    height: 60px;
    z-index: 101;
    top: 70px;
    font-size: 18px;

    right: 10px;
    color: #fff;
    background: #fd5c68;
    line-height: 2.5;
    -webkit-box-shadow: 0 0 5px black;
    -moz-box-shadow:    0 0 5px black;
    box-shadow:         0 0 5px black;
    padding: 10px;
">
    One of alliance member needs help!
    <div  class="right" style="cursor: pointer;" (click) = "close()">
    x
    </div>
</div>
`
    }
)
export class PlayerHeader{

    // EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
    // ALLIANCE_NAME = /^[a-z]{3,9}$/;
    VILLAGE = /^[A-Za-z1-9.]{3,9}$/;
    DATE = /^[A-Za-z1-9. :]{3,20}$/;
    ENEMY = "Enter correct email, please!";
    NAME_ERROR = "Enter from 3 to 10 letters";
    LOGIN_ERROR = "Enter from 3 to 10 letters";

    public serverResponse: string;

    showNotif: boolean = false;



    constructor(private stompService: StompService){
    }


    public ngOnInit(): void {
        this.stompService.connect('ws://localhost:8080/travian/stompTest');
        this.stompService.getObservable().subscribe(payload => {
            this.serverResponse = payload.outputField;
            this.showNotification();
        });
    }

    showNotification(){
        this.showNotif = true;
    }

    close(){
        this.showNotif = false;
    }


}