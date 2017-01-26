import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent }  from './app.component';
import {AllianceComponent} from "./alliance/alliance.component";
import {HeaderComponent} from "./header/header.component";

import {AllianceForm} from "./alliance/add-alliance.component";
import {EditAllianceComponent} from "./alliance/edit-alliance.component";
import {ConfirmComponent} from "./modal_window/modal";
import {RoutesModule} from "./app.routers";
import {AllianceService} from "./services/alliance/alliance-service";
import {TimerComponent} from "./timer/timer.component";
import {PlayerComponent} from "./player/player.component";
import {HelpComponent} from "./help/ask-help.component";
import {AllHelps} from "./help/all-helps.component";
import {HelpService} from "./services/helpNotification/help.service";
import {StompService} from "./services/helpNotification/stomp.service";
import {PlayerHeader} from "./header/player-header.component";

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        FormsModule,
        ReactiveFormsModule,
        RoutesModule,
    ],
    declarations: [
        AppComponent,
        AllianceComponent,
        HeaderComponent,
        AllianceForm,
        EditAllianceComponent,
        ConfirmComponent,
        TimerComponent,
        PlayerComponent,
        PlayerHeader,
        HelpComponent,
        AllHelps
    ],
    providers: [
        AllianceService,
        HelpService,
        StompService
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }